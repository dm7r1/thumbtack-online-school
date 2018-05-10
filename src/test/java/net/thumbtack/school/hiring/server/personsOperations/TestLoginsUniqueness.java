package net.thumbtack.school.hiring.server.personsOperations;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.requests.DtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.server.Server;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestLoginsUniqueness {
    @Test
    public void testEmployeeLoginUniqueness() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        server.registerEmployee(gson.toJson(DtoRequestsFactory.makeRegisterEmployeeDtoRequest(
                "firstName1", "lastName1", "patronymic1", "login1", "email1@example.com", "password")));
        String responseJson = server.registerEmployee(gson.toJson(DtoRequestsFactory.makeRegisterEmployeeDtoRequest(
                "firstName2", "lastName2", "patronymic2", "login1", "email2@example.com", "password")));

        assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());

        server.registerEmployer(gson.toJson(DtoRequestsFactory.makeRegisterEmployerDtoRequest(
                "firstName3", "lastName3", "patronymic3", "login3", "email3@example.com", "password",
                "companyName3", "address3")));
        responseJson = server.registerEmployee(gson.toJson(DtoRequestsFactory.makeRegisterEmployeeDtoRequest(
                "firstName4", "lastName4", "patronymic4", "login3", "email4@example.com", "password")));

        assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());

        server.stopServer("");
    }
    @Test
    public void testEmployerLoginUniqueness() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        server.registerEmployer(gson.toJson(DtoRequestsFactory.makeRegisterEmployerDtoRequest(
                "firstName1", "lastName1", "patronymic1", "login1", "email1@example.com", "password",
                "companyName1", "address1")));
        String responseJson = server.registerEmployer(gson.toJson(DtoRequestsFactory.makeRegisterEmployerDtoRequest(
                "firstName2", "lastName2", "patronymic2", "login1", "email2@example.com", "password",
         "companyName1", "address1")));

        assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());


        server.registerEmployee(gson.toJson(DtoRequestsFactory.makeRegisterEmployeeDtoRequest(
                "firstName3", "lastName3", "patronymic3", "login3", "email3@example.com", "password")));
        responseJson = server.registerEmployer(gson.toJson(DtoRequestsFactory.makeRegisterEmployerDtoRequest(
                "firstName4", "lastName4", "patronymic4", "login3", "email4@example.com", "password",
                "companyName4", "address4")));

        assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());

        server.stopServer("");
    }
}
