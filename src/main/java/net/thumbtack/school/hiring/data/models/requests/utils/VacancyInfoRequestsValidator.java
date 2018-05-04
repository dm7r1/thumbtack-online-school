package net.thumbtack.school.hiring.data.models.requests.utils;

import net.thumbtack.school.hiring.data.models.InvalidRequestErrors;
import net.thumbtack.school.hiring.data.models.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.Requirement;

import java.util.Map;

public class VacancyInfoRequestsValidator {
    public void validateAddVacancyInfo(String vacancyName, int payment, Map<String, Requirement> requirements) throws InvalidRequestException {
        if(vacancyName == null || !isValidVacancyName(vacancyName))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_VACANCY_NAME);
        if(!isValidPayment(payment))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_PAYMENT);
        if(!isValidRequirements(requirements))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_REQUIREMENTS);
    }

    public boolean isValidVacancyName(String vacancyName) {
        return vacancyName.length() != 0;
    }

    public boolean isValidPayment(int payment) {
        return payment > 0;
    }

    public boolean isValidRequirements(Map<String, Requirement> requirements) {
        for(String requirementName: requirements.keySet()) {
            if(requirementName == null || requirementName.length() == 0)
                return false;
            int lvl = requirements.get(requirementName).getLvl();
            if(!(lvl >= 1 && lvl <= 5))
                return false;
        }
        return true;
    }
}
