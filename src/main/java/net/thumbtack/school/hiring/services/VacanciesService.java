package net.thumbtack.school.hiring.services;

import net.thumbtack.school.hiring.data.DataStorage;
import net.thumbtack.school.hiring.data.dao.VacancyDao;
import net.thumbtack.school.hiring.data.daoimpl.VacancyDaoImpl;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.stored.Vacancy;
import net.thumbtack.school.hiring.data.models.requests.*;
import net.thumbtack.school.hiring.data.models.requests.checkers.ModelsExistenceCheckerImpl;
import net.thumbtack.school.hiring.data.models.requests.validators.ModelsExistenceValidator;
import net.thumbtack.school.hiring.data.models.requests.validators.VacancyInfoRequestsValidator;
import net.thumbtack.school.hiring.data.models.responses.DtoResponse;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessEmptyDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.VacanciesListDtoResponse;

public class VacanciesService {
    private VacancyDao vacancyDao;

    private ModelsExistenceValidator modelsExistenceValidator;
    private VacancyInfoRequestsValidator vacancyInfoRequestsValidator;

    public VacanciesService(DataStorage dataStorage) {
        vacancyDao = new VacancyDaoImpl(dataStorage);

        this.modelsExistenceValidator = new ModelsExistenceValidator(new ModelsExistenceCheckerImpl(dataStorage));
        this.vacancyInfoRequestsValidator = new VacancyInfoRequestsValidator();
    }

    public DtoResponse addVacancy(AddVacancyDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getToken());
            vacancyInfoRequestsValidator.validateAddVacancyInfo(
                    request.getVacancyName(),
                    request.getPayment(),
                    request.getRequirements()
            );
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        vacancyDao.insertVacancy(request.getToken(), new Vacancy(request.getVacancyName(), request.getPayment(), request.getRequirements()));

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse deleteVacancy(DeleteVacancyDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getToken());
            modelsExistenceValidator.validateEmployerUuidVacancyNumberPair(request.getToken(), request.getVacancyNumber());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        vacancyDao.deleteVacancy(request.getToken(), request.getVacancyNumber());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse getVacancies(GetVacanciesDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getToken());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        return VacanciesListDtoResponse.makeInstance(vacancyDao.getEmployerVacancies(request.getToken()));
    }

    public DtoResponse changeVacancy(ChangeVacancyDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getToken());
            modelsExistenceValidator.validateEmployerUuidVacancyNumberPair(request.getToken(), request.getVacancyNumber());
            modelsExistenceValidator.validateVacancyRequirementsForDeleting(request.getToken(), request.getVacancyNumber(), request.getRequirementsForDeletingNames());
            vacancyInfoRequestsValidator.validateChangeVacancyNewInfo(request.getNewVacancyName(), request.getNewPayment(), request.getNewOrChangedRequirements());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        Vacancy vacancy = vacancyDao.getVacancy(request.getToken(), request.getVacancyNumber());
        if(request.getNewVacancyName() != null)
            vacancy.setVacancyName(request.getNewVacancyName());
        if(request.getNewPayment() != null)
            vacancy.setPayment(request.getNewPayment());

        if(request.getRequirementsForDeletingNames() != null)
            for(String requirementName: request.getRequirementsForDeletingNames()) {
                vacancy.deleteRequirement(requirementName);
            }

        if(request.getNewOrChangedRequirements() != null)
            vacancyDao.addVacancyRequirements(request.getToken(), request.getVacancyNumber(), request.getNewOrChangedRequirements());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse setVacancyActive(SetVacancyActiveDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getToken());
            modelsExistenceValidator.validateEmployerUuidVacancyNumberPair(request.getToken(), request.getVacancyNumber());
            vacancyInfoRequestsValidator.validateSetVacancyActiveInfo(request.getActive());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        vacancyDao.setVacancyActive(request.getToken(), request.getVacancyNumber(), request.getActive());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }
}