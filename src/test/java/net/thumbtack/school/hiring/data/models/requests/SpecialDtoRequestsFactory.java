package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.Requirement;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SpecialDtoRequestsFactory {
    public static RegisterEmployeeDtoRequest makeValidRegisterEmployeeDtoRequest() {
        return DtoRequestsFactory.makeRegisterEmployeeDtoRequest(
                "First", "Last", "Patronymic", "login", "email@example.com", "password"
        );
    }

    public static RegisterEmployerDtoRequest makeValidRegisterEmployerDtoRequest() {
        return DtoRequestsFactory.makeRegisterEmployerDtoRequest(
                "First", "Last", "Patronymic", "login", "email@example.com", "password",
                "companyName", "address"
        );
    }

    public static AddVacancyDtoRequest makeValidAddVacancyDtoRequest(UUID token) {
        Map<String, Requirement> requirements = new HashMap<>();
        requirements.put("skillName", new Requirement(1, false));
        return DtoRequestsFactory.makeAddVacancyDtoRequest(token, "vacancyName", 1, requirements);
    }
}
