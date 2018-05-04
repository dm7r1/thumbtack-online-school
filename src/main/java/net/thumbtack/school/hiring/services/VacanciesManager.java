package net.thumbtack.school.hiring.services;

import net.thumbtack.school.hiring.data.DataBase;
import net.thumbtack.school.hiring.data.models.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.Vacancy;
import net.thumbtack.school.hiring.data.models.requests.AddVacancyDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.utils.PersonExistenceCheckerImpl;
import net.thumbtack.school.hiring.data.models.requests.utils.PersonExistenceValidator;
import net.thumbtack.school.hiring.data.models.requests.utils.VacancyInfoRequestsValidator;
import net.thumbtack.school.hiring.data.models.responses.DtoResponse;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessEmptyDtoResponse;

public class VacanciesManager {
    private DataBase dataBase;

    public VacanciesManager(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public DtoResponse addVacancy(AddVacancyDtoRequest request) {
        try {
            new PersonExistenceValidator(new PersonExistenceCheckerImpl(dataBase)).validateEmployerUUID(request.getUuid());
            new VacancyInfoRequestsValidator().validateAddVacancyInfo(
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
}
