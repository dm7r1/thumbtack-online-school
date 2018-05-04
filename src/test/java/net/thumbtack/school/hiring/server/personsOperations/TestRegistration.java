package net.thumbtack.school.hiring.server.personsOperations;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.requests.DtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.requests.RegisterEmployeeDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.RegisterEmployerDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.SpecialDtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;
import net.thumbtack.school.hiring.server.Server;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class TestRegistration {
    @Test
    public void registerEmployee() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        String responseJson = server.registerEmployee(gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployeeDtoRequest()));
        assertNotNull(gson.fromJson(responseJson, SuccessfulRegisteredDtoResponse.class).getToken());

        server.stopServer("");
    }

    @Test
    public void registerEmployer() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        String responseJson = server.registerEmployer(gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest()));
        assertNotNull(gson.fromJson(responseJson, SuccessfulRegisteredDtoResponse.class).getToken());

        server.stopServer("");
    }

    @Test
    public void registerInvalidEmployee() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        RegisterEmployeeDtoRequest invalidRequests[] = {
                DtoRequestsFactory.makeRegisterEmployeeDtoRequest(null, "Last", "Patronymic", "Login", "email@example.com", "password"),
                DtoRequestsFactory.makeRegisterEmployeeDtoRequest("", "Last", "Patronymic", "Login", "email@example.com", "password"),
                DtoRequestsFactory.makeRegisterEmployeeDtoRequest("First", "Last", "Patronymic", "L", "email@example.com", "password"),
                DtoRequestsFactory.makeRegisterEmployeeDtoRequest("First", "Last", "Patronymic", "Login", "email@example.com", "p"),
                DtoRequestsFactory.makeRegisterEmployeeDtoRequest("First", "Last", "Patronymic", "Login", "email", "password")
        };
        for(RegisterEmployeeDtoRequest invalidRequest: invalidRequests) {
            String responseJson = server.registerEmployee(gson.toJson(invalidRequest));
            assertNotNull(toString(), gson.fromJson(responseJson, ErrorDtoResponse.class).getError());
        }

        server.stopServer("");
    }

    @Test
    public void registerInvalidEmployer() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        RegisterEmployerDtoRequest invalidRequests[] = {
                DtoRequestsFactory.makeRegisterEmployerDtoRequest(null, "Last", "Patronymic", "Login", "email@example.com", "password",
                        "companyName", "address"),
                DtoRequestsFactory.makeRegisterEmployerDtoRequest("", "Last", "Patronymic", "Login", "email@example.com", "password",
                        "companyName", "address"),
                DtoRequestsFactory.makeRegisterEmployerDtoRequest("First", "Last", "Patronymic", "L", "email@example.com", "password",
                        "companyName", "address"),
                DtoRequestsFactory.makeRegisterEmployerDtoRequest("First", "Last", "Patronymic", "Login", "email@example.com", "p",
                        "companyName", "address"),
                DtoRequestsFactory.makeRegisterEmployerDtoRequest("First", "Last", "Patronymic", "Login", "email", "password",
                        "companyName", "address"),
                DtoRequestsFactory.makeRegisterEmployerDtoRequest("First", "Last", "Patronymic", "Login", "email@example.com", "password",
                        null, "address"),
                DtoRequestsFactory.makeRegisterEmployerDtoRequest("First", "Last", "Patronymic", "Login", "email@example.com", "password",
                        "", "address")
        };
        for(RegisterEmployerDtoRequest invalidRequest: invalidRequests) {
            String responseJson = server.registerEmployer(gson.toJson(invalidRequest));
            assertNotNull(toString(), gson.fromJson(responseJson, ErrorDtoResponse.class).getError());
        }

        server.stopServer("");
    }
}
