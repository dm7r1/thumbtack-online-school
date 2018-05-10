package net.thumbtack.school.hiring.server.searchOperations;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.stored.RequirementProperties;
import net.thumbtack.school.hiring.data.models.stored.RequirementsList;
import net.thumbtack.school.hiring.data.models.stored.SkillsList;
import net.thumbtack.school.hiring.data.models.requests.AddVacancyDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.DtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.requests.HireEmployeeDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.SpecialDtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.RightEmployeesListDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.RightVacanciesListDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.services.special.search.SearchOptions;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

public class TestEmployeeHiring {
    @Test
    public void employeeHiring() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID employerToken = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        RequirementsList requirements = new RequirementsList();
        requirements.addRequirement("skill1", new RequirementProperties(1, false));
        AddVacancyDtoRequest addVacancyDtoRequest = DtoRequestsFactory.makeAddVacancyDtoRequest(employerToken, "vacancy1", 1, requirements);
        server.addVacancy(gson.toJson(addVacancyDtoRequest));

        UUID employeeToken = gson.fromJson(server.registerEmployee(
                gson.toJson(DtoRequestsFactory.makeRegisterEmployeeDtoRequest(
                        "firstName", "lastName", "patronymic", "employeeLogin", "email@example.com", "password"
                ))), SuccessfulRegisteredDtoResponse.class).getToken();
        SkillsList skills = new SkillsList();
        skills.addSkill("skill1", 5);
        server.changeSkills(gson.toJson(DtoRequestsFactory.makeChangeSkillsDtoRequest(employeeToken, skills, null)));

        server.hireEmployee(gson.toJson(DtoRequestsFactory.makeHireEmployeeDtoRequest(employerToken, 0, "employeeLogin")));

        int foundVacancies = gson.fromJson(server.searchVacanciesBySkills(
                gson.toJson(DtoRequestsFactory.makeSearchVacanciesDtoRequest(employeeToken, SearchOptions.ALL_SKILLS_WITH_ENOUGH_LEVEL.ordinal()))), RightVacanciesListDtoResponse.class)
                .getRightVacancies().size();
        assertEquals(0, foundVacancies);

        int foundEmployees = gson.fromJson(server.searchEmployeesByVacancy(
                gson.toJson(DtoRequestsFactory.makeSearchEmployeesDtoRequest(employerToken, 0, SearchOptions.ALL_SKILLS_WITH_ENOUGH_LEVEL.ordinal()))), RightEmployeesListDtoResponse.class)
                .getRightEmployees().size();
        assertEquals(0, foundEmployees);

        server.stopServer("");
    }

    @Test
    public void invalidRequest() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID employerToken = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        RequirementsList requirements = new RequirementsList();
        requirements.addRequirement("skill1", new RequirementProperties(1, false));
        AddVacancyDtoRequest addVacancyDtoRequest = DtoRequestsFactory.makeAddVacancyDtoRequest(employerToken, "vacancy1", 1, requirements);
        server.addVacancy(gson.toJson(addVacancyDtoRequest));

        UUID employeeToken = gson.fromJson(server.registerEmployee(
                gson.toJson(DtoRequestsFactory.makeRegisterEmployeeDtoRequest(
                        "firstName", "lastName", "patronymic", "employeeLogin", "email@example.com", "password"
                ))), SuccessfulRegisteredDtoResponse.class).getToken();
        SkillsList skills = new SkillsList();
        skills.addSkill("skill1", 5);
        server.changeSkills(gson.toJson(DtoRequestsFactory.makeChangeSkillsDtoRequest(employeeToken, skills, null)));

        HireEmployeeDtoRequest requestWithNonexistentToken = DtoRequestsFactory.makeHireEmployeeDtoRequest(UUID.randomUUID(), 0, "employeeLogin");
        HireEmployeeDtoRequest requestWithNullToken = DtoRequestsFactory.makeHireEmployeeDtoRequest(null, 0, "employeeLogin");
        HireEmployeeDtoRequest requestWithNonexistentVacancyNumber = DtoRequestsFactory.makeHireEmployeeDtoRequest(employerToken, 1, "employeeLogin");
        HireEmployeeDtoRequest requestWithNullVacancyNumber = DtoRequestsFactory.makeHireEmployeeDtoRequest(employerToken, null, "employeeLogin");
        HireEmployeeDtoRequest requestWithNonexistentLogin = DtoRequestsFactory.makeHireEmployeeDtoRequest(employerToken, 0, "nonexistentLogin");
        HireEmployeeDtoRequest requestWithNullLogin = DtoRequestsFactory.makeHireEmployeeDtoRequest(employerToken, 0, null);
        HireEmployeeDtoRequest invalidRequests[] = {
                requestWithNonexistentToken, requestWithNullToken, requestWithNonexistentVacancyNumber, requestWithNullVacancyNumber, requestWithNonexistentLogin, requestWithNullLogin};

        for(HireEmployeeDtoRequest invalidRequest: invalidRequests) {
            String responseJson = server.hireEmployee(gson.toJson(invalidRequest));
            assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());
        }

        server.stopServer("");
    }
}
