package net.thumbtack.school.hiring.integration.personsOperations;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.requests.DtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.requests.SpecialDtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessEmptyDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;
import net.thumbtack.school.hiring.integration.Server;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class TestDeletePerson {
    @Test
    public void deleteEmployee() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        String responseJson = server.registerEmployee(gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployeeDtoRequest()));
        UUID token = gson.fromJson(responseJson, SuccessfulRegisteredDtoResponse.class).getToken();
        responseJson = server.deleteEmployee(gson.toJson(DtoRequestsFactory.makeDeleteEmployeeDtoRequest(token)));

        assertEquals("success", gson.fromJson(responseJson, SuccessEmptyDtoResponse.class).getResult());

        server.stopServer("");
    }

    @Test
    public void deleteEmployer() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        String responseJson = server.registerEmployer(gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest()));
        UUID token = gson.fromJson(responseJson, SuccessfulRegisteredDtoResponse.class).getToken();
        responseJson = server.deleteEmployer(gson.toJson(DtoRequestsFactory.makeDeleteEmployerDtoRequest(token)));

        assertEquals("success", gson.fromJson(responseJson, SuccessEmptyDtoResponse.class).getResult());

        server.stopServer("");
    }

    @Test
    public void deleteNonexistentEmployee() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = UUID.randomUUID();
        String responseJson = server.deleteEmployee(gson.toJson(DtoRequestsFactory.makeDeleteEmployeeDtoRequest(token)));

        assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());

        server.stopServer("");
    }

    @Test
    public void deleteNonexistentEmployer() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = UUID.randomUUID();
        String responseJson = server.deleteEmployer(gson.toJson(DtoRequestsFactory.makeDeleteEmployerDtoRequest(token)));

        assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());

        server.stopServer("");
    }
}
