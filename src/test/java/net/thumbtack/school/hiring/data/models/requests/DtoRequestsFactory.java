package net.thumbtack.school.hiring.data.models.requests;


import net.thumbtack.school.hiring.data.models.RequirementsList;
import net.thumbtack.school.hiring.data.models.SkillsList;
import net.thumbtack.school.hiring.services.special.search.SearchOptions;

import java.util.Set;
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
        request.setNewFirstName(firstName);
        request.setNewLastName(lastName);
        request.setNewPatronymic(patronymic);
        request.setNewEmail(email);
        request.setNewPassword(password);

        return request;
    }

    public static ChangeEmployerInfoDtoRequest makeChangeEmployerInfoDtoRequest(UUID token, String firstName, String lastName, String patronymic, String email, String password,
                                               String companyName, String address) {
        ChangeEmployerInfoDtoRequest request = new ChangeEmployerInfoDtoRequest();
        request.setUuid(token);
        request.setNewFirstName(firstName);
        request.setNewLastName(lastName);
        request.setNewPatronymic(patronymic);
        request.setNewEmail(email);
        request.setNewPassword(password);
        request.setNewCompanyName(companyName);
        request.setNewAddress(address);

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

    public static AddVacancyDtoRequest makeAddVacancyDtoRequest(UUID token, String vacancyName, Integer payment, RequirementsList requirements) {
        AddVacancyDtoRequest request = new AddVacancyDtoRequest();
        request.setUuid(token);
        request.setVacancyName(vacancyName);
        request.setPayment(payment);
        request.setRequirements(requirements);

        return request;
    }

    public static DeleteVacancyDtoRequest makeDeleteVacancyDtoRequest(UUID token, Integer vacancyNumber) {
        DeleteVacancyDtoRequest request = new DeleteVacancyDtoRequest();
        request.setUuid(token);
        request.setVacancyNumber(vacancyNumber);

        return request;
    }

    public static GetVacanciesDtoRequest makeGetVacanciesDtoRequest(UUID token) {
        GetVacanciesDtoRequest request = new GetVacanciesDtoRequest();
        request.setUuid(token);

        return request;
    }

    public static ChangeVacancyDtoRequest makeChangeVacancyDtoRequest(UUID token, Integer vacancyNumber, String newVacancyName, Integer newPayment,
                                                                      RequirementsList newOrChangedRequirements, Set<String> requirementsForDeletingNames) {
        ChangeVacancyDtoRequest request = new ChangeVacancyDtoRequest();
        request.setUuid(token);
        request.setVacancyNumber(vacancyNumber);
        request.setNewVacancyName(newVacancyName);
        request.setNewPayment(newPayment);
        request.setNewOrChangedRequirements(newOrChangedRequirements);
        request.setRequirementsForDeletingNames(requirementsForDeletingNames);

        return request;
    }

    public static ChangeSkillsDtoRequest makeChangeSkillsDtoRequest(UUID token, SkillsList newOrChangedSkills, Set<String> skillsForDeletingNames) {
        ChangeSkillsDtoRequest request = new ChangeSkillsDtoRequest();
        request.setUuid(token);
        request.setNewOrChangedSkills(newOrChangedSkills);
        request.setSkillsForDeletingNames(skillsForDeletingNames);

        return request;
    }

    public static GetSkillsDtoRequest makeGetSkillsDtoRequest(UUID token) {
        GetSkillsDtoRequest request = new GetSkillsDtoRequest();
        request.setUuid(token);

        return request;
    }

    public static GetDefinedSkillsDtoRequest makeGetDefinedSkillsDtoRequest(UUID token) {
        GetDefinedSkillsDtoRequest request = new GetDefinedSkillsDtoRequest();
        request.setUuid(token);

        return request;
    }

    public static SearchEmployeesByVacancyDtoRequest makeSearchEmployeesDtoRequest(UUID token, Integer vacancyNumber, Integer searchOptionCode) {
        SearchEmployeesByVacancyDtoRequest request = new SearchEmployeesByVacancyDtoRequest();
        request.setUuid(token);
        request.setVacancyNumber(vacancyNumber);
        request.setSearchOptionCode(searchOptionCode);

        return request;
    }

    public static SearchVacanciesBySkillsDtoRequest makeSearchVacanciesDtoRequest(UUID token, Integer searchOptionCode) {
        SearchVacanciesBySkillsDtoRequest request = new SearchVacanciesBySkillsDtoRequest();
        request.setUuid(token);
        request.setSearchOptionCode(searchOptionCode);

        return request;
    }
}
