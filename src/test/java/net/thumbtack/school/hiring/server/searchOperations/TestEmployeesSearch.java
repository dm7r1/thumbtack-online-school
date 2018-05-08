package net.thumbtack.school.hiring.server.searchOperations;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.RequirementProperties;
import net.thumbtack.school.hiring.data.models.RequirementsList;
import net.thumbtack.school.hiring.data.models.SkillsList;
import net.thumbtack.school.hiring.data.models.requests.DtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.requests.SearchEmployeesByVacancyDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.SpecialDtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.RightEmployeesListDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.specialModels.ShortEmployeeInfoDto;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.server.searchOperations.specialModels.EnvironmentForEmployeesSearchTests;
import net.thumbtack.school.hiring.services.special.search.SearchOptions;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestEmployeesSearch {
    public EnvironmentForEmployeesSearchTests makeEnvironmentForTests() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID employerToken = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        RequirementsList requirements = new RequirementsList();
        requirements.addRequirement("skillA", new RequirementProperties(3, true));
        requirements.addRequirement("skillB", new RequirementProperties(4, false));
        requirements.addRequirement("skillC", new RequirementProperties(5, true));
        server.addVacancy(gson.toJson(DtoRequestsFactory.makeAddVacancyDtoRequest(employerToken, "vacancy1", 1, requirements)));

        SkillsList skills = new SkillsList();
        skills.addSkill("skillA", 3);
        skills.addSkill("skillB", 5);
        skills.addSkill("skillC", 5);
        UUID employeeToken = gson.fromJson(server.registerEmployee(gson.toJson(
                DtoRequestsFactory.makeRegisterEmployeeDtoRequest("firstName", "lastName", "patronymic", "login1", "email@example.com", "password")
        )), SuccessfulRegisteredDtoResponse.class).getToken();
        server.changeSkills(gson.toJson(DtoRequestsFactory.makeChangeSkillsDtoRequest(employeeToken, skills, null)));
        skills = new SkillsList();
        skills.addSkill("skillA", 4);
        skills.addSkill("skillB", 4);
        skills.addSkill("skillC", 5);
        employeeToken = gson.fromJson(server.registerEmployee(gson.toJson(
                DtoRequestsFactory.makeRegisterEmployeeDtoRequest("firstName", "lastName", "patronymic", "login2", "email@example.com", "password")
        )), SuccessfulRegisteredDtoResponse.class).getToken();
        server.changeSkills(gson.toJson(DtoRequestsFactory.makeChangeSkillsDtoRequest(employeeToken, skills, null)));
        skills = new SkillsList();
        skills.addSkill("skillA", 1);
        skills.addSkill("skillB", 1);
        skills.addSkill("skillC", 1);
        employeeToken = gson.fromJson(server.registerEmployee(gson.toJson(
                DtoRequestsFactory.makeRegisterEmployeeDtoRequest("firstName", "lastName", "patronymic", "login3", "email@example.com", "password")
        )), SuccessfulRegisteredDtoResponse.class).getToken();
        server.changeSkills(gson.toJson(DtoRequestsFactory.makeChangeSkillsDtoRequest(employeeToken, skills, null)));
        skills = new SkillsList();
        skills.addSkill("skillA", 3);
        skills.addSkill("skillB", 1);
        skills.addSkill("skillC", 5);
        employeeToken = gson.fromJson(server.registerEmployee(gson.toJson(
                DtoRequestsFactory.makeRegisterEmployeeDtoRequest("firstName", "lastName", "patronymic", "login4", "email@example.com", "password")
        )), SuccessfulRegisteredDtoResponse.class).getToken();
        server.changeSkills(gson.toJson(DtoRequestsFactory.makeChangeSkillsDtoRequest(employeeToken, skills, null)));
        skills = new SkillsList();
        skills.addSkill("skillC", 5);
        employeeToken = gson.fromJson(server.registerEmployee(gson.toJson(
                DtoRequestsFactory.makeRegisterEmployeeDtoRequest("firstName", "lastName", "patronymic", "login5", "email@example.com", "password")
        )), SuccessfulRegisteredDtoResponse.class).getToken();
        server.changeSkills(gson.toJson(DtoRequestsFactory.makeChangeSkillsDtoRequest(employeeToken, skills, null)));

        return new EnvironmentForEmployeesSearchTests(server, employerToken, 0);
    }

    public Set<String> loginsSetFromEmployeesInfoList(List<ShortEmployeeInfoDto> employeesInfo) {
        Set<String> logins = new HashSet<>();
        for(ShortEmployeeInfoDto employeeInfo: employeesInfo) {
            logins.add(employeeInfo.getLogin());
        }
        return logins;
    }

    public void testSearchByReceivedEmployeesLogins(SearchOptions searchOption, Set<String> expectedLogins) {
        EnvironmentForEmployeesSearchTests environmentForTests = makeEnvironmentForTests();
        Server server = environmentForTests.getServer();
        Gson gson = new Gson();
        UUID employerToken = environmentForTests.getEmployerToken();
        int vacancyNumber = environmentForTests.getVacancyNumber();

        String responseJson = server.searchEmployeesByVacancy(gson.toJson(
                DtoRequestsFactory.makeSearchEmployeesDtoRequest(employerToken, vacancyNumber, searchOption.ordinal())
        ));
        List<ShortEmployeeInfoDto> rightEmployees = gson.fromJson(responseJson, RightEmployeesListDtoResponse.class).getRightEmployees();

        assertEquals(expectedLogins.size(), rightEmployees.size());
        assertTrue(loginsSetFromEmployeesInfoList(rightEmployees).containsAll(expectedLogins));

        server.stopServer("");
    }

    @Test
    public void searchEmployeesWithAllSkillsWithEnoughSkills () {
        Set<String> expectedLogins = new HashSet<>();
        expectedLogins.add("login1");
        expectedLogins.add("login2");

        testSearchByReceivedEmployeesLogins(SearchOptions.ALL_SKILLS_WITH_ENOUGH_LEVEL, expectedLogins);
    }

    @Test
    public void searchEmployeesWithNecessarySkillsWithEnoughLevel () {
        Set<String> expectedLogins = new HashSet<>();
        expectedLogins.add("login1");
        expectedLogins.add("login2");
        expectedLogins.add("login4");

        testSearchByReceivedEmployeesLogins(SearchOptions.NECESSARY_SKILLS_WITH_ENOUGH_LEVEL, expectedLogins);
    }

    @Test
    public void searchEmployeesWithAllSkillsWithAnyLevel () {
        Set<String> expectedLogins = new HashSet<>();
        expectedLogins.add("login1");
        expectedLogins.add("login2");
        expectedLogins.add("login3");
        expectedLogins.add("login4");

        testSearchByReceivedEmployeesLogins(SearchOptions.ALL_SKILLS_WITH_ANY_LEVEL, expectedLogins);
    }

    @Test
    public void searchEmployeesWithOneNecessarySkillWithEnoughLevel () {
        Set<String> expectedLogins = new HashSet<>();
        expectedLogins.add("login1");
        expectedLogins.add("login2");
        expectedLogins.add("login4");
        expectedLogins.add("login5");

        testSearchByReceivedEmployeesLogins(SearchOptions.ONE_REQUIRED_SKILL_WITH_ENOUGH_LEVEL, expectedLogins);
    }

    @Test
    public void invalidRequests() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        server.addVacancy(gson.toJson(DtoRequestsFactory.makeAddVacancyDtoRequest(token, "vacancy1", 1, new RequirementsList())));
        SearchEmployeesByVacancyDtoRequest requestWithNonexistentToken = DtoRequestsFactory.makeSearchEmployeesDtoRequest(
                UUID.randomUUID(), 0, SearchOptions.ALL_SKILLS_WITH_ENOUGH_LEVEL.ordinal());
        SearchEmployeesByVacancyDtoRequest requestWithNonexistentVacancy = DtoRequestsFactory.makeSearchEmployeesDtoRequest(
                token, 1, SearchOptions.ALL_SKILLS_WITH_ENOUGH_LEVEL.ordinal());
        SearchEmployeesByVacancyDtoRequest requestWithNullVacancyNumber = DtoRequestsFactory.makeSearchEmployeesDtoRequest(
                token, null, SearchOptions.ALL_SKILLS_WITH_ENOUGH_LEVEL.ordinal());
        SearchEmployeesByVacancyDtoRequest requestWithInvalidSearchOption = DtoRequestsFactory.makeSearchEmployeesDtoRequest(
                token, 0, 4);
        SearchEmployeesByVacancyDtoRequest requestWithNullSearchOption = DtoRequestsFactory.makeSearchEmployeesDtoRequest(
                token, 0, null);
        SearchEmployeesByVacancyDtoRequest invalidRequests[] = {
                requestWithNonexistentToken, requestWithNonexistentVacancy, requestWithNullVacancyNumber,
                requestWithInvalidSearchOption, requestWithNullSearchOption};
        for(SearchEmployeesByVacancyDtoRequest invalidRequest: invalidRequests) {
            String responseJson = server.searchEmployeesByVacancy(gson.toJson(invalidRequest));
            assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());
        }

        server.stopServer("");
    }
}
