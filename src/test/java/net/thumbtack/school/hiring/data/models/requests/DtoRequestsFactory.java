package net.thumbtack.school.hiring.data.models.requests;


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

    public static ChangeEmployeeInfoDtoRequest makeChangeEmployeeInfoDtoRequest(String firstName, String lastName, String patronymic, String login, String email, String password) {
        ChangeEmployeeInfoDtoRequest request = new ChangeEmployeeInfoDtoRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setPatronymic(patronymic);
        request.setEmail(email);
        request.setPassword(password);

        return request;
    }

    public static ChangeEmployerInfoDtoRequest makeChangeEmployerInfoDtoRequest(String firstName, String lastName, String patronymic, String login, String email, String password,
                                               String companyName, String address) {
        ChangeEmployerInfoDtoRequest request = new ChangeEmployerInfoDtoRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setPatronymic(patronymic);
        request.setEmail(email);
        request.setPassword(password);
        request.setCompanyName(companyName);
        request.setAddress(address);

        return request;
    }
}
