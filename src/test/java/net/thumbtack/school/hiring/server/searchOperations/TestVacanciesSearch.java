package net.thumbtack.school.hiring.server.searchOperations;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.RequirementProperties;
import net.thumbtack.school.hiring.data.models.RequirementsList;
import net.thumbtack.school.hiring.data.models.SkillsList;
import net.thumbtack.school.hiring.data.models.requests.DtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.requests.SearchVacanciesBySkillsDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.SpecialDtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.RightVacanciesListDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.specialModels.ShortVacancyInfoDto;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.server.searchOperations.specialModels.EnvironmentForEmployeesSearchTests;
import net.thumbtack.school.hiring.server.searchOperations.specialModels.EnvironmentForVacationsSearchTests;
import net.thumbtack.school.hiring.services.special.search.SearchOptions;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.*;

public class TestVacanciesSearch {
    public EnvironmentForVacationsSearchTests makeEnvironmentForTests() {
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

        return new EnvironmentForVacationsSearchTests(server, employeeToken);
    }

    public Set<String> vacanciesNamesSetFromVacanciesInfoList(List<ShortVacancyInfoDto> vacanciesInfo) {
        Set<String> vacanciesNames = new HashSet<>();
        for(ShortVacancyInfoDto vacancyInfo: vacanciesInfo) {
            vacanciesNames.add(vacancyInfo.getVacancyName());
        }
        return vacanciesNames;
    }

    public void testSearchByReceivedEmployeesLogins(SearchOptions searchOption, Set<String> expectedVacanciesNames) {
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
    public void searchEmployeesWithOneNecessarySkillWithEnoughLevel () {
        Set<String> expectedVacancies = new HashSet<>();
        expectedVacancies.add("vacancy1");
        expectedVacancies.add("vacancy2");
        expectedVacancies.add("vacancy4");

        testSearchByReceivedEmployeesLogins(SearchOptions.ONE_REQUIRED_SKILL_WITH_ENOUGH_LEVEL, expectedVacancies);
    }

    @Test
    public void invalidRequests() {
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
}
