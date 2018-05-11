package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.stored.RequirementProperties;
import net.thumbtack.school.hiring.data.models.stored.RequirementsList;

import java.util.UUID;

public class SpecialDtoRequestsFactory {
    private static int uniqueLoginModifier = 0;

    public static RegisterEmployeeDtoRequest makeValidRegisterEmployeeDtoRequest() {
        ++uniqueLoginModifier;
        return DtoRequestsFactory.makeRegisterEmployeeDtoRequest(
                "First", "Last", "Patronymic", "login(requestsFactory)" + Integer.toString(uniqueLoginModifier), "email@example.com", "password"
        );
    }

    public static RegisterEmployerDtoRequest makeValidRegisterEmployerDtoRequest() {
        ++uniqueLoginModifier;
        return DtoRequestsFactory.makeRegisterEmployerDtoRequest(
                "First", "Last", "Patronymic", "login(requestsFactory)" + Integer.toString(uniqueLoginModifier), "email@example.com", "password",
                "companyName", "address"
        );
    }

    public static AddVacancyDtoRequest makeValidAddVacancyDtoRequest(UUID token) {
        RequirementsList requirements = new RequirementsList();
        requirements.addRequirement("skillName", new RequirementProperties(1, false));
        return DtoRequestsFactory.makeAddVacancyDtoRequest(token, "vacancyName", 1, requirements);
    }
}
