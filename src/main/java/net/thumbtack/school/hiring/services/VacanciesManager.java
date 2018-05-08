package net.thumbtack.school.hiring.services;

import net.thumbtack.school.hiring.data.DataBase;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.Vacancy;
import net.thumbtack.school.hiring.data.models.requests.AddVacancyDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.ChangeVacancyDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.DeleteVacancyDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.GetVacanciesDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.utils.checkers.ModelsExistenceCheckerImpl;
import net.thumbtack.school.hiring.data.models.requests.utils.validators.ModelsExistenceValidator;
import net.thumbtack.school.hiring.data.models.requests.utils.validators.VacancyInfoRequestsValidator;
import net.thumbtack.school.hiring.data.models.responses.DtoResponse;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessEmptyDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.VacanciesListDtoResponse;

public class VacanciesManager {
    private DataBase dataBase;
    private ModelsExistenceValidator modelsExistenceValidator;
    private VacancyInfoRequestsValidator vacancyInfoRequestsValidator;

    public VacanciesManager(DataBase dataBase) {
        this.dataBase = dataBase;
        this.modelsExistenceValidator = new ModelsExistenceValidator(new ModelsExistenceCheckerImpl(dataBase));
        this.vacancyInfoRequestsValidator = new VacancyInfoRequestsValidator();
    }

    public DtoResponse addVacancy(AddVacancyDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getUuid());
            vacancyInfoRequestsValidator.validateAddVacancyInfo(
                    request.getVacancyName(),
                    request.getPayment(),
                    request.getRequirements()
            );
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        dataBase.insertVacancy(request.getUuid(), new Vacancy(request.getVacancyName(), request.getPayment(), request.getRequirements()));

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse deleteVacancy(DeleteVacancyDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getUuid());
            modelsExistenceValidator.validateEmployerUuidVacancyNumberPair(request.getUuid(), request.getVacancyNumber());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        dataBase.deleteVacancy(request.getUuid(), request.getVacancyNumber());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse getVacancies(GetVacanciesDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getUuid());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        return VacanciesListDtoResponse.makeInstance(dataBase.getEmployerVacancies(request.getUuid()));
    }

    public DtoResponse changeVacancy(ChangeVacancyDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getUuid());
            modelsExistenceValidator.validateEmployerUuidVacancyNumberPair(request.getUuid(), request.getVacancyNumber());
            modelsExistenceValidator.validateVacancyRequirementsForDeleting(request.getUuid(), request.getVacancyNumber(), request.getRequirementsForDeletingNames());
            vacancyInfoRequestsValidator.validateChangeVacancyNewInfo(request.getNewVacancyName(), request.getNewPayment(), request.getNewOrChangedRequirements());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        Vacancy vacancy = dataBase.getVacancy(request.getUuid(), request.getVacancyNumber());
        if(request.getNewVacancyName() != null)
            vacancy.setVacancyName(request.getNewVacancyName());
        if(request.getNewPayment() != null)
            vacancy.setPayment(request.getNewPayment());

        if(request.getRequirementsForDeletingNames() != null)
            for(String requirementName: request.getRequirementsForDeletingNames()) {
                vacancy.deleteRequirement(requirementName);
            }

        if(request.getNewOrChangedRequirements() != null)
            dataBase.addVacancyRequirements(request.getUuid(), request.getVacancyNumber(), request.getNewOrChangedRequirements());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }
}
