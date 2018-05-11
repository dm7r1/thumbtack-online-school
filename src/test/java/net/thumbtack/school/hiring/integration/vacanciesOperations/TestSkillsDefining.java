package net.thumbtack.school.hiring.integration.vacanciesOperations;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.stored.RequirementProperties;
import net.thumbtack.school.hiring.data.models.stored.RequirementsList;
import net.thumbtack.school.hiring.data.models.stored.SkillsList;
import net.thumbtack.school.hiring.data.models.requests.DtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.requests.SpecialDtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.responses.DefinedSkillsDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;
import net.thumbtack.school.hiring.integration.Server;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class TestSkillsDefining {
    @Test
    public void skillsDefining() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();


        UUID employeeToken = gson.fromJson(server.registerEmployee(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployeeDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        UUID employerToken = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();


        SkillsList skills = new SkillsList();
        skills.addSkill("php", 1);
        skills.addSkill("sql", 1);
        server.changeSkills(gson.toJson(DtoRequestsFactory.makeChangeSkillsDtoRequest(employeeToken, skills, null)));

        RequirementsList requirements = new RequirementsList();
        requirements.addRequirement("javascript", new RequirementProperties(1, false));
        requirements.addRequirement("html", new RequirementProperties(1, false));
        requirements.addRequirement("css", new RequirementProperties(1, false));
        server.addVacancy(gson.toJson(DtoRequestsFactory.makeAddVacancyDtoRequest(employerToken, "vacancy1", 1, requirements)));

        RequirementsList newRequirements = new RequirementsList();
        newRequirements.addRequirement("vue.js", new RequirementProperties(1, false));
        server.changeVacancy(gson.toJson(DtoRequestsFactory.makeChangeVacancyDtoRequest(employerToken, 0,null, null,
                newRequirements, null)));

        Set<String> expectedDefinedSkills = new HashSet<>();
        expectedDefinedSkills.addAll(skills.getSkillsNamesSet());
        expectedDefinedSkills.addAll(requirements.getRequirementsNamesSet());
        expectedDefinedSkills.addAll(newRequirements.getRequirementsNamesSet());

        Set<String> definedSkillsEmployeeVersion = gson.fromJson(server.getDefinedSkills(
                gson.toJson(DtoRequestsFactory.makeGetDefinedSkillsDtoRequest(employeeToken))), DefinedSkillsDtoResponse.class).getDefinedSkills();
        Set<String> definedSkillsEmployerVersion = gson.fromJson(server.getDefinedSkills(
                gson.toJson(DtoRequestsFactory.makeGetDefinedSkillsDtoRequest(employerToken))), DefinedSkillsDtoResponse.class).getDefinedSkills();
        assertEquals(expectedDefinedSkills, definedSkillsEmployeeVersion);
        assertEquals(expectedDefinedSkills, definedSkillsEmployerVersion);

        server.stopServer("");
    }

    @Test
    public void requestWithNonexistentToken() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        String responseJson = server.getDefinedSkills(gson.toJson(DtoRequestsFactory.makeGetDefinedSkillsDtoRequest(UUID.randomUUID())));
        assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());

        server.stopServer("");
    }
}
