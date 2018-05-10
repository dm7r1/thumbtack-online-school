package net.thumbtack.school.hiring.server.vacanciesOperations;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.stored.SkillsList;
import net.thumbtack.school.hiring.data.models.requests.ChangeSkillsDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.DtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.requests.SpecialDtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SkillsListDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;
import net.thumbtack.school.hiring.server.Server;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class TestSkills {
    @Test
    public void changeAndGetSkills() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployee(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployeeDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();

        SkillsList skillsForAdding = new SkillsList();
        skillsForAdding.addSkill("thisSkillWillBeDeleted", 3);
        skillsForAdding.addSkill("thisSkillWillBeChanged", 2);
        skillsForAdding.addSkill("skillName", 5);
        ChangeSkillsDtoRequest changeSkillsDtoRequest = DtoRequestsFactory.makeChangeSkillsDtoRequest(token, skillsForAdding, null);
        server.changeSkills(gson.toJson(changeSkillsDtoRequest));

        SkillsList receivedSkills = gson.fromJson(server.getSkills(
                gson.toJson(DtoRequestsFactory.makeGetSkillsDtoRequest(token))), SkillsListDtoResponse.class).getSkills();

        assertEquals(skillsForAdding.size(), receivedSkills.size());
        for(String skillName: skillsForAdding.getSkillsNamesSet()) {
            assertEquals(skillsForAdding.getSkillLvl(skillName), receivedSkills.getSkillLvl(skillName));
        }

        SkillsList skillsForAddingOrChanging = new SkillsList();
        skillsForAddingOrChanging.addSkill("thisSkillWillBeChanged", 1);
        skillsForAddingOrChanging.addSkill("newSkill", 5);

        Set<String> skillsForDeleting = new HashSet<>();
        skillsForDeleting.add("thisSkillWillBeDeleted");
        changeSkillsDtoRequest = DtoRequestsFactory.makeChangeSkillsDtoRequest(token, skillsForAddingOrChanging, skillsForDeleting);
        server.changeSkills(gson.toJson(changeSkillsDtoRequest));

        receivedSkills = gson.fromJson(server.getSkills(
                gson.toJson(DtoRequestsFactory.makeGetSkillsDtoRequest(token))), SkillsListDtoResponse.class).getSkills();

        SkillsList expectedSkills = skillsForAdding;
        for(String skillName: skillsForDeleting)
            expectedSkills.removeSkill(skillName);
        expectedSkills.addSkills(skillsForAddingOrChanging);

        assertEquals(expectedSkills.size(), receivedSkills.size());
        for(String skillName: expectedSkills.getSkillsNamesSet()) {
            assertEquals(expectedSkills.getSkillLvl(skillName), receivedSkills.getSkillLvl(skillName));
        }

        server.stopServer("");
    }

    @Test
    public void getNonexistentEmployeeSkills() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        String responseJson = server.getSkills(gson.toJson(
                DtoRequestsFactory.makeGetSkillsDtoRequest(UUID.randomUUID())));

        assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());

        server.stopServer("");
    }

    @Test
    public void addInvalidSkills() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployee(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployeeDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();

        SkillsList skillsWithInvalidNames = new SkillsList();
        skillsWithInvalidNames.addSkill("", 1);
        ChangeSkillsDtoRequest requestWithInvalidSkillsNames = DtoRequestsFactory.makeChangeSkillsDtoRequest(token, skillsWithInvalidNames, null);

        SkillsList skillsWithInvalidLvls = new SkillsList();
        skillsWithInvalidLvls.addSkill("skill", 0);
        ChangeSkillsDtoRequest requestWithInvalidSkillsLvls = DtoRequestsFactory.makeChangeSkillsDtoRequest(token, skillsWithInvalidLvls, null);

        ChangeSkillsDtoRequest invalidRequests[] = {requestWithInvalidSkillsNames, requestWithInvalidSkillsLvls};
        for(ChangeSkillsDtoRequest invalidRequest: invalidRequests) {
            assertNotNull(gson.fromJson(server.changeSkills(gson.toJson(invalidRequest)), ErrorDtoResponse.class).getError());
        }

        String responseJson = server.getSkills(gson.toJson(
                DtoRequestsFactory.makeGetSkillsDtoRequest(UUID.randomUUID())));

        assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());

        server.stopServer("");
    }

    @Test
    public void deleteNonexistentSkills() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployee(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployeeDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();

        Set<String> skillsForDeleting = new HashSet<>();
        skillsForDeleting.add("nonexistent skill");
        String responseJson = server.changeSkills(gson.toJson(DtoRequestsFactory.makeChangeSkillsDtoRequest(token, null, skillsForDeleting)));
        assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());

        server.stopServer("");
    }

    @Test
    public void changeNonexistentEmployeeSkills() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        String responseJson = server.changeSkills(gson.toJson(DtoRequestsFactory.makeChangeSkillsDtoRequest(UUID.randomUUID(), null, null)));
        assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());

        server.stopServer("");
    }
}
