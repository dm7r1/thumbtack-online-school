package net.thumbtack.school.hiring.integration.searchOperations;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.stored.RequirementProperties;
import net.thumbtack.school.hiring.data.models.stored.RequirementsList;
import net.thumbtack.school.hiring.data.models.stored.SkillsList;
import net.thumbtack.school.hiring.data.models.requests.DtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.requests.SearchVacanciesBySkillsDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.SetVacancyActiveDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.SpecialDtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.RightVacanciesListDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.specialModels.ShortVacancyInfoDto;
import net.thumbtack.school.hiring.integration.Server;
import net.thumbtack.school.hiring.integration.searchOperations.specialModels.EnvironmentForVacationsSearchTests;
import net.thumbtack.school.hiring.services.special.search.SearchOptions;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestVacanciesSearch {
    private EnvironmentForVacationsSearchTests makeEnvironmentForTests() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID employerToken = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        UUID employeeToken = gson.fromJson(server.registerEmployee(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployeeDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        SkillsList skills = new SkillsList();
        skills.addSkill("skillA", 3);
        skills.addSkill("skillB", 4);
        server.changeSkills(gson.toJson(DtoRequestsFactory.makeChangeSkillsDtoRequest(employeeToken, skills, null)));

        RequirementsList requirements = new RequirementsList();
        requirements.addRequirement("skillA", new RequirementProperties(3, true));
        requirements.addRequirement("skillB", new RequirementProperties(3, true));
        server.addVacancy(gson.toJson(DtoRequestsFactory.makeAddVacancyDtoRequest(employerToken, "vacancy1", 1, requirements)));
        requirements = new RequirementsList();
        requirements.addRequirement("skillA", new RequirementProperties(2, true));
        requirements.addRequirement("skillB", new RequirementProperties(3, true));
        requirements.addRequirement("skillC", new RequirementProperties(5, false));
        server.addVacancy(gson.toJson(DtoRequestsFactory.makeAddVacancyDtoRequest(employerToken, "vacancy2", 2, requirements)));
        requirements = new RequirementsList();
        requirements.addRequirement("skillA", new RequirementProperties(5, true));
        requirements.addRequirement("skillB", new RequirementProperties(1, false));
        server.addVacancy(gson.toJson(DtoRequestsFactory.makeAddVacancyDtoRequest(employerToken, "vacancy3", 3, requirements)));
        requirements = new RequirementsList();
        requirements.addRequirement("skillA", new RequirementProperties(3, true));
        requirements.addRequirement("skillB", new RequirementProperties(1, false));
        requirements.addRequirement("skillC", new RequirementProperties(5, true));
        server.addVacancy(gson.toJson(DtoRequestsFactory.makeAddVacancyDtoRequest(employerToken, "vacancy4", 4, requirements)));
        requirements = new RequirementsList();
        requirements.addRequirement("skillC", new RequirementProperties(5, true));
        server.addVacancy(gson.toJson(DtoRequestsFactory.makeAddVacancyDtoRequest(employerToken, "vacancy5", 5, requirements)));
        requirements = new RequirementsList();
        requirements.addRequirement("skillA", new RequirementProperties(1, false));
        server.addVacancy(gson.toJson(DtoRequestsFactory.makeAddVacancyDtoRequest(employerToken, "vacancy6", 5, requirements)));
        server.setVacancyActive(gson.toJson(DtoRequestsFactory.makeSetVacancyActiveDtoRequest(employerToken, 5, false)));

        return new EnvironmentForVacationsSearchTests(server, employeeToken);
    }

    private Set<String> vacanciesNamesSetFromVacanciesInfoList(List<ShortVacancyInfoDto> vacanciesInfo) {
        Set<String> vacanciesNames = new HashSet<>();
        for(ShortVacancyInfoDto vacancyInfo: vacanciesInfo) {
            vacanciesNames.add(vacancyInfo.getVacancyName());
        }
        return vacanciesNames;
    }

    private void testSearchByReceivedEmployeesLogins(SearchOptions searchOption, Set<String> expectedVacanciesNames) {
        EnvironmentForVacationsSearchTests environmentForTests = makeEnvironmentForTests();
        Server server = environmentForTests.getServer();
        Gson gson = new Gson();
        UUID employeeToken = environmentForTests.getEmployeeToken();

        String responseJson = server.searchVacanciesBySkills(gson.toJson(
                DtoRequestsFactory.makeSearchVacanciesDtoRequest(employeeToken, searchOption.ordinal())
        ));
        List<ShortVacancyInfoDto> rightVacanciesNames = gson.fromJson(responseJson, RightVacanciesListDtoResponse.class).getRightVacancies();

        assertEquals(expectedVacanciesNames.size(), rightVacanciesNames.size());
        assertTrue(vacanciesNamesSetFromVacanciesInfoList(rightVacanciesNames).containsAll(expectedVacanciesNames));

        server.stopServer("");
    }

    @Test
    public void searchEmployeesWithAllSkillsWithEnoughSkills () {
        Set<String> expectedVacancies = new HashSet<>();
        expectedVacancies.add("vacancy1");

        testSearchByReceivedEmployeesLogins(SearchOptions.ALL_SKILLS_WITH_ENOUGH_LEVEL, expectedVacancies);
    }

    @Test
    public void searchEmployeesWithNecessarySkillsWithEnoughLevel () {
        Set<String> expectedVacancies = new HashSet<>();
        expectedVacancies.add("vacancy1");
        expectedVacancies.add("vacancy2");

        testSearchByReceivedEmployeesLogins(SearchOptions.NECESSARY_SKILLS_WITH_ENOUGH_LEVEL, expectedVacancies);
    }

    @Test
    public void searchEmployeesWithAllSkillsWithAnyLevel () {
        Set<String> expectedVacancies = new HashSet<>();
        expectedVacancies.add("vacancy1");
        expectedVacancies.add("vacancy3");

        testSearchByReceivedEmployeesLogins(SearchOptions.ALL_SKILLS_WITH_ANY_LEVEL, expectedVacancies);
    }

    @Test
    public void searchEmployeesWithOneSkillWithEnoughLevel () {
        EnvironmentForVacationsSearchTests environmentForTests = makeEnvironmentForTests();
        Server server = environmentForTests.getServer();
        Gson gson = new Gson();
        UUID employeeToken = environmentForTests.getEmployeeToken();

        String responseJson = server.searchVacanciesBySkills(gson.toJson(
                DtoRequestsFactory.makeSearchVacanciesDtoRequest(employeeToken, SearchOptions.ONE_SKILL_WITH_ENOUGH_LEVEL.ordinal())
        ));
        List<ShortVacancyInfoDto> rightVacanciesNames = gson.fromJson(responseJson, RightVacanciesListDtoResponse.class).getRightVacancies();
        HashSet<String> expectedVacanciesNames = new HashSet<>();
        expectedVacanciesNames.add("vacancy1");
        expectedVacanciesNames.add("vacancy2");
        expectedVacanciesNames.add("vacancy3");
        expectedVacanciesNames.add("vacancy4");

        assertEquals(expectedVacanciesNames.size(), rightVacanciesNames.size());
        assertTrue(vacanciesNamesSetFromVacanciesInfoList(rightVacanciesNames).containsAll(expectedVacanciesNames));

        /*
            Right order - (1 2 4), (3)
            vacancy1 - 2 matched skills
            vacancy2 - 2 matched skills
            vacancy4 - 2 matched skills
            vacancy3 - 1 matched skill
        */
        assertEquals("vacancy3", rightVacanciesNames.get(3).getVacancyName());


        server.stopServer("");
    }

    @Test
    public void invalidSearchRequests() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployee(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployeeDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        SearchVacanciesBySkillsDtoRequest requestWithNonexistentToken = DtoRequestsFactory.makeSearchVacanciesDtoRequest(
                UUID.randomUUID(), SearchOptions.ALL_SKILLS_WITH_ENOUGH_LEVEL.ordinal());
        SearchVacanciesBySkillsDtoRequest requestWithInvalidSearchOption = DtoRequestsFactory.makeSearchVacanciesDtoRequest(
                token, 4);
        SearchVacanciesBySkillsDtoRequest requestWithNullSearchOption = DtoRequestsFactory.makeSearchVacanciesDtoRequest(
                token, null);
        SearchVacanciesBySkillsDtoRequest invalidRequests[] = {
                requestWithNonexistentToken, requestWithInvalidSearchOption, requestWithNullSearchOption};
        for(SearchVacanciesBySkillsDtoRequest invalidRequest: invalidRequests) {
            String responseJson = server.searchVacanciesBySkills(gson.toJson(invalidRequest));
            assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());
        }

        server.stopServer("");
    }

    @Test
    public void invalidSetVacancyActive() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        server.addVacancy(gson.toJson(SpecialDtoRequestsFactory.makeValidAddVacancyDtoRequest(token)));
        SetVacancyActiveDtoRequest requestWithNonexistentToken = DtoRequestsFactory.makeSetVacancyActiveDtoRequest(
                UUID.randomUUID(), 0, false
        );
        SetVacancyActiveDtoRequest requestWithNullToken = DtoRequestsFactory.makeSetVacancyActiveDtoRequest(
                null, 0, false
        );
        SetVacancyActiveDtoRequest requestWithNonexistentVacancyNumber = DtoRequestsFactory.makeSetVacancyActiveDtoRequest(
                token, 1, false
        );
        SetVacancyActiveDtoRequest requestWithNullVacancyNumber = DtoRequestsFactory.makeSetVacancyActiveDtoRequest(
                token, null, false
        );
        SetVacancyActiveDtoRequest requestWithNullActive = DtoRequestsFactory.makeSetVacancyActiveDtoRequest(
                token, 0, null
        );
        SetVacancyActiveDtoRequest invalidRequests[] = {
                requestWithNonexistentToken, requestWithNonexistentVacancyNumber, requestWithNullVacancyNumber, requestWithNullToken, requestWithNullActive};
        for(SetVacancyActiveDtoRequest invalidRequest: invalidRequests) {
            String responseJson = server.setVacancyActive(gson.toJson(invalidRequest));
            assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());
        }

        server.stopServer("");
    }
}
