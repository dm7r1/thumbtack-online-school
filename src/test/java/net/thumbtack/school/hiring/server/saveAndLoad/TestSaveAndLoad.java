package net.thumbtack.school.hiring.server.saveAndLoad;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.requests.DtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.requests.SpecialDtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;
import net.thumbtack.school.hiring.data.models.stored.RequirementProperties;
import net.thumbtack.school.hiring.data.models.stored.RequirementsList;
import net.thumbtack.school.hiring.data.models.stored.SkillsList;
import net.thumbtack.school.hiring.server.Server;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestSaveAndLoad {
    @Test
    public void saveAndLoad() {
        Server server0 = new Server();
        server0.startServer("");
        Gson gson = new Gson();

        UUID employerToken = gson.fromJson(server0.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        UUID employeeToken = gson.fromJson(server0.registerEmployee(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployeeDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        RequirementsList requirements = new RequirementsList();
        requirements.addRequirement("req1", new RequirementProperties(1, false));
        requirements.addRequirement("req2", new RequirementProperties(3, true));
        server0.addVacancy(gson.toJson(DtoRequestsFactory.makeAddVacancyDtoRequest(employerToken, "vacancy1", 1, requirements)));
        SkillsList skills = new SkillsList();
        skills.addSkill("skill1", 1);
        skills.addSkill("skill2", 5);
        server0.changeSkills(gson.toJson(DtoRequestsFactory.makeChangeSkillsDtoRequest(employeeToken, skills, null)));

        server0.stopServer("database_test");

        //
        //
        //

        Server server1 = new Server();
        server1.startServer("database_test");

        assertEquals(server0.getDataBase(), server1.getDataBase());

        server1.stopServer("");
    }
}
