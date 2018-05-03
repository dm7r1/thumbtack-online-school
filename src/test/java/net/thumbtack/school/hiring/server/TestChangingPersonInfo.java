package net.thumbtack.school.hiring.server;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.requests.ChangeEmployeeInfoDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.ChangeEmployerInfoDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.DtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessEmptyDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;
import org.junit.Test;

import java.util.UUID;

import static net.thumbtack.school.hiring.data.models.requests.DtoRequestsFactory.makeChangeEmployeeInfoDtoRequest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestChangingPersonInfo {
    @Test
    public void changeEmployeeInfo() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        String responseJson = server.registerEmployee(gson.toJson(DtoRequestsFactory.makeRegisterEmployeeDtoRequest(
            "First", "Last", "Patronymic", "login", "email@example.com", "password"
        )));
        UUID token = gson.fromJson(responseJson, SuccessfulRegisteredDtoResponse.class).getToken();
        responseJson = server.changeEmployeeInfo(gson.toJson(makeChangeEmployeeInfoDtoRequest(
                "new_firstName", null, null, null, null, "new_password"
        )));
        assertEquals("success", gson.fromJson(responseJson, SuccessEmptyDtoResponse.class).getResult());

        server.stopServer("");
    }

    @Test
    public void changeEmployerInfo() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        String responseJson = server.registerEmployer(gson.toJson(DtoRequestsFactory.makeRegisterEmployerDtoRequest(
            "First", "Last", "Patronymic", "login", "email@example.com", "password", "companyName", "address"
        )));
        UUID token = gson.fromJson(responseJson, SuccessfulRegisteredDtoResponse.class).getToken();
        responseJson = server.changeEmployerInfo(gson.toJson(DtoRequestsFactory.makeChangeEmployerInfoDtoRequest(
                null, null, null, null, null, null, "new_companyName", "new_address"
        )));
        assertEquals("success", gson.fromJson(responseJson, SuccessEmptyDtoResponse.class).getResult());

        server.stopServer("");
    }

    @Test
    public void changeEmployeeInfoToInvalid() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        String responseJson = server.registerEmployee(gson.toJson(DtoRequestsFactory.makeRegisterEmployeeDtoRequest(
            "First", "Last", "Patronymic", "login", "email@example.com", "password"
        )));
        UUID token = gson.fromJson(responseJson, SuccessfulRegisteredDtoResponse.class).getToken();
        ChangeEmployeeInfoDtoRequest invalidRequests[] = {
                DtoRequestsFactory.makeChangeEmployeeInfoDtoRequest("", null, null, null, null, null),
                DtoRequestsFactory.makeChangeEmployeeInfoDtoRequest(null,null, null, "l", null, null),
                DtoRequestsFactory.makeChangeEmployeeInfoDtoRequest(null,null, null, null, "email", null),
                DtoRequestsFactory.makeChangeEmployeeInfoDtoRequest(null,null, null, null, null, "p"),
        };
        for(ChangeEmployeeInfoDtoRequest invalidRequest: invalidRequests) {
            responseJson = server.changeEmployeeInfo(gson.toJson(invalidRequest));
            assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());
        }

        server.stopServer("");
    }

    @Test
    public void changeEmployerInfoToInvalid() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        String responseJson = server.registerEmployer(gson.toJson(DtoRequestsFactory.makeRegisterEmployerDtoRequest(
            "First", "Last", "Patronymic", "login", "email@example.com", "password", "companyName", "address"
        )));
        UUID token = gson.fromJson(responseJson, SuccessfulRegisteredDtoResponse.class).getToken();
        ChangeEmployerInfoDtoRequest invalidRequests[] = {
                DtoRequestsFactory.makeChangeEmployerInfoDtoRequest(null, null, null, null, null, null,
                        "", null),
                DtoRequestsFactory.makeChangeEmployerInfoDtoRequest(null,null, null, "l", null, null,
                        null, null ),
                DtoRequestsFactory.makeChangeEmployerInfoDtoRequest(null,null, null, null, "email", null,
                        null, null),
                DtoRequestsFactory.makeChangeEmployerInfoDtoRequest(null,null, null, null, null, "p",
                        null, null),
        };
        for(ChangeEmployerInfoDtoRequest invalidRequest: invalidRequests) {
            responseJson = server.changeEmployerInfo(gson.toJson(invalidRequest));
            assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());
        }

        server.stopServer("");
    }
}
