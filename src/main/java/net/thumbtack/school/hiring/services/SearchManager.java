package net.thumbtack.school.hiring.services;

import net.thumbtack.school.hiring.data.DataBase;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.Employee;
import net.thumbtack.school.hiring.data.models.Vacancy;
import net.thumbtack.school.hiring.data.models.requests.SearchEmployeesByVacancyDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.SearchVacanciesBySkillsDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.utils.checkers.ModelsExistenceCheckerImpl;
import net.thumbtack.school.hiring.data.models.requests.utils.validators.ModelsExistenceValidator;
import net.thumbtack.school.hiring.data.models.requests.utils.validators.SearchRequestsValidator;
import net.thumbtack.school.hiring.data.models.responses.DtoResponse;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.RightEmployeesListDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.RightVacanciesListDtoResponse;

import java.util.List;

public class SearchManager {
    private DataBase dataBase;
    private ModelsExistenceValidator modelsExistenceValidator;
    private SearchRequestsValidator searchRequestsValidator;

    public SearchManager(DataBase dataBase) {
        this.dataBase = dataBase;
        this.modelsExistenceValidator = new ModelsExistenceValidator(new ModelsExistenceCheckerImpl(dataBase));
        this.searchRequestsValidator = new SearchRequestsValidator();
    }

    public DtoResponse searchVacanciesForEmployee(SearchVacanciesBySkillsDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployeeUUID(request.getUuid());
            searchRequestsValidator.validateSearchRequest(request.getSearchOptionCode());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        List<Vacancy> rightEmployees = dataBase.getVacanciesByEmployeeSkills(request.getUuid(), request.getSearchOption().getValuer());

        return RightVacanciesListDtoResponse.makeInstance(rightEmployees);
    }

    public DtoResponse searchEmployeesByVacancy(SearchEmployeesByVacancyDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getUuid());
            modelsExistenceValidator.validateEmployerUuidVacancyNumberPair(request.getUuid(), request.getVacancyNumber());
            searchRequestsValidator.validateSearchRequest(request.getSearchOptionCode());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        List<Employee> rightEmployees = dataBase.getEmployeesByVacancyRequirements(request.getUuid(), request.getVacancyNumber(), request.getSearchOption().getValuer());

        return RightEmployeesListDtoResponse.makeInstance(rightEmployees);
    }
}
