package net.thumbtack.school.hiring.data.models.requests.utils.validators;

import net.thumbtack.school.hiring.data.exceptions.InvalidRequestErrors;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.RequirementsList;
import net.thumbtack.school.hiring.data.models.requests.utils.checkers.SkillInfoChecker;

public class VacancyInfoRequestsValidator {
    public void validateAddVacancyInfo(String vacancyName, Integer payment, RequirementsList requirements) throws InvalidRequestException {
        if(vacancyName == null || !isValidVacancyName(vacancyName))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_VACANCY_NAME);
        if(payment == null || !isValidPayment(payment))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_PAYMENT);
        validateVacancyRequirementsInfo(requirements);
    }

    public void validateChangeVacancyNewInfo(String newVacancyName, Integer newPayment, RequirementsList requirements) throws InvalidRequestException {
        if(newVacancyName != null)
            if(!isValidVacancyName(newVacancyName))
                throw new InvalidRequestException(InvalidRequestErrors.INVALID_VACANCY_NAME);
        if(newPayment != null)
            if(!isValidPayment(newPayment))
                throw new InvalidRequestException(InvalidRequestErrors.INVALID_PAYMENT);
        if(requirements != null)
            validateVacancyRequirementsInfo(requirements);
    }

    public void validateVacancyRequirementsInfo(RequirementsList requirements) throws InvalidRequestException {
        if(!isValidRequirements(requirements))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_REQUIREMENTS);
    }

    public boolean isValidVacancyName(String vacancyName) {
        return vacancyName.length() != 0;
    }

    public boolean isValidPayment(Integer payment) {
        return payment > 0;
    }

    public boolean isValidRequirements(RequirementsList requirements) {
        for(String requirementName: requirements.getRequirementsNamesSet()) {
            if(requirementName == null || !SkillInfoChecker.isValidSkillName(requirementName))
                return false;
            int lvl = requirements.getRequirementProperties(requirementName).getLvl();
            if(!SkillInfoChecker.isValidSkillLvl(lvl))
                return false;
        }
        return true;
    }
}
