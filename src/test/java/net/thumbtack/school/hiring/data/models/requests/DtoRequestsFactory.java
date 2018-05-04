package net.thumbtack.school.hiring.data.models.requests;


import net.thumbtack.school.hiring.data.models.Requirement;

import java.util.Map;
import java.util.UUID;

public class DtoRequestsFactory {
    public static RegisterEmployeeDtoRequest makeRegisterEmployeeDtoRequest(String firstName, String lastName, String patronymic, String login, String email, String password) {
        RegisterEmployeeDtoRequest request = new RegisterEmployeeDtoRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setPatronymic(patronymic);
        request.setLogin(login);
        request.setEmail(email);
        request.setPassword(password);

        return request;
    }

    public static RegisterEmployerDtoRequest makeRegisterEmployerDtoRequest(String firstName, String lastName, String patronymic, String login, String email, String password,
                                               String companyName, String address) {
        RegisterEmployerDtoRequest request = new RegisterEmployerDtoRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setPatronymic(patronymic);
        request.setLogin(login);
        request.setEmail(email);
        request.setPassword(password);
        request.setCompanyName(companyName);
        request.setAddress(address);

        return request;
    }

    public static ChangeEmployeeInfoDtoRequest makeChangeEmployeeInfoDtoRequest(UUID token, String firstName, String lastName, String patronymic, String email, String password) {
        ChangeEmployeeInfoDtoRequest request = new ChangeEmployeeInfoDtoRequest();
        request.setUuid(token);
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setPatronymic(patronymic);
        request.setEmail(email);
        request.setPassword(password);

        return request;
    }

    public static ChangeEmployerInfoDtoRequest makeChangeEmployerInfoDtoRequest(UUID token, String firstName, String lastName, String patronymic, String email, String password,
                                               String companyName, String address) {
        ChangeEmployerInfoDtoRequest request = new ChangeEmployerInfoDtoRequest();
        request.setUuid(token);
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setPatronymic(patronymic);
        request.setEmail(email);
        request.setPassword(password);
        request.setCompanyName(companyName);
        request.setAddress(address);

        return request;
    }

    public static DeleteEmployeeDtoRequest makeDeleteEmployeeDtoRequest(UUID token) {
        DeleteEmployeeDtoRequest request = new DeleteEmployeeDtoRequest();
        request.setUuid(token);

        return request;
    }

    public static DeleteEmployerDtoRequest makeDeleteEmployerDtoRequest(UUID token) {
        DeleteEmployerDtoRequest request = new DeleteEmployerDtoRequest();
        request.setUuid(token);

        return request;
    }

    public static AddVacancyDtoRequest makeAddVacancyDtoRequest(UUID token, String vacancyName, int payment, Map<String, Requirement> requirements) {
        AddVacancyDtoRequest request = new AddVacancyDtoRequest();
        request.setUuid(token);
        request.setVacancyName(vacancyName);
        request.setPayment(payment);
        request.setRequirements(requirements);

        return request;
    }
}
