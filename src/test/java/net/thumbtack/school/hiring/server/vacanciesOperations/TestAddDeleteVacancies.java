package net.thumbtack.school.hiring.server.vacanciesOperations;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.Requirement;
import net.thumbtack.school.hiring.data.models.requests.AddVacancyDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.DtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.requests.SpecialDtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessEmptyDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;
import net.thumbtack.school.hiring.server.Server;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class TestAddDeleteVacancies {
    @Test
    public void addVacancy() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        String responseJson = server.addVacancy(gson.toJson(SpecialDtoRequestsFactory.makeValidAddVacancyDtoRequest(token)));
        assertEquals("success", gson.fromJson(responseJson, SuccessEmptyDtoResponse.class).getResult());
    }

    @Test
    public void addInvalidVacancy() {

        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        AddVacancyDtoRequest requestWithInvalidVacancyName = DtoRequestsFactory.makeAddVacancyDtoRequest(
                token, "", 1, new HashMap<>()
        );
        AddVacancyDtoRequest requestWithInvalidPayment = DtoRequestsFactory.makeAddVacancyDtoRequest(
                token, "vacancyName", 0, new HashMap<>()
        );
        Map<String, Requirement> requirements = new HashMap<>();
        requirements.put("", new Requirement(1, false));
        AddVacancyDtoRequest requestWithInvalidRequirements = DtoRequestsFactory.makeAddVacancyDtoRequest(
                token, "vacancyName", 1, requirements
        );
        AddVacancyDtoRequest invalidRequests[] = {
                requestWithInvalidVacancyName, requestWithInvalidPayment, requestWithInvalidRequirements
        };
        for(AddVacancyDtoRequest invalidRequest: invalidRequests) {
            String responseJson = server.addVacancy(gson.toJson(invalidRequest));
            assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());
        }
    }
}
