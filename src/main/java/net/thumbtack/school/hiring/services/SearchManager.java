package net.thumbtack.school.hiring.services;

import net.thumbtack.school.hiring.data.DataBase;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.stored.Employee;
import net.thumbtack.school.hiring.data.models.stored.Vacancy;
import net.thumbtack.school.hiring.data.models.requests.HireEmployeeDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.SearchEmployeesByVacancyDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.SearchVacanciesBySkillsDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.checkers.ModelsExistenceCheckerImpl;
import net.thumbtack.school.hiring.data.models.requests.validators.ModelsExistenceValidator;
import net.thumbtack.school.hiring.data.models.requests.validators.SearchRequestsValidator;
import net.thumbtack.school.hiring.data.models.responses.*;
import net.thumbtack.school.hiring.services.special.search.RequiredSkillsCountComparator;
import net.thumbtack.school.hiring.services.special.search.SearchOptions;

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
            modelsExistenceValidator.validateEmployeeUUID(request.getToken());
            searchRequestsValidator.validateSearchRequest(request.getSearchOptionCode());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        List<Vacancy> rightEmployees = dataBase.getVacanciesByEmployeeSkills(request.getToken(), request.getSearchOption().getValuer());

        if(request.getSearchOption() == SearchOptions.ONE_SKILL_WITH_ENOUGH_LEVEL) {
            RequiredSkillsCountComparator comparator = new RequiredSkillsCountComparator(dataBase.getEmployeeSkills(request.getToken()));
            rightEmployees.sort(comparator);
        }

        return RightVacanciesListDtoResponse.makeInstance(rightEmployees);
    }

    public DtoResponse searchEmployeesByVacancy(SearchEmployeesByVacancyDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getToken());
            modelsExistenceValidator.validateEmployerUuidVacancyNumberPair(request.getToken(), request.getVacancyNumber());
            searchRequestsValidator.validateSearchRequest(request.getSearchOptionCode());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        List<Employee> rightEmployees = dataBase.getEmployeesByVacancyRequirements(request.getToken(), request.getVacancyNumber(), request.getSearchOption().getValuer());

        return RightEmployeesListDtoResponse.makeInstance(rightEmployees);
    }

    public DtoResponse hireEmployee(HireEmployeeDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getToken());
            modelsExistenceValidator.validateEmployerUuidVacancyNumberPair(request.getToken(), request.getVacancyNumber());
            modelsExistenceValidator.validateEmployeeLogin(request.getLogin());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        dataBase.setVacancyActive(request.getToken(), request.getVacancyNumber(), false);
        dataBase.setEmployeeActive(request.getLogin(), false);

        return SuccessEmptyDtoResponse.makeNewInstance();
    }
}
