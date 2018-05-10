package net.thumbtack.school.hiring.data.models.requests.validators;

import net.thumbtack.school.hiring.data.exceptions.InvalidRequestErrors;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.requests.checkers.ModelsExistenceChecker;

import java.util.Set;
import java.util.UUID;

public class ModelsExistenceValidator {
    private ModelsExistenceChecker modelsExistenceChecker;

    public ModelsExistenceValidator(ModelsExistenceChecker modelsExistenceChecker) {
        this.modelsExistenceChecker = modelsExistenceChecker;
    }

    public void validateAnyPersonUUID(UUID uuid) throws InvalidRequestException {
        if(uuid == null)
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_TOKEN);
        if(!(modelsExistenceChecker.employeeExists(uuid) || modelsExistenceChecker.employerExists(uuid)))
            throw new InvalidRequestException(InvalidRequestErrors.NONEXISTENT_TOKEN);
    }

    public void validateEmployeeUUID(UUID uuid) throws InvalidRequestException {
        if(uuid == null)
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_TOKEN);
        if(!modelsExistenceChecker.employeeExists(uuid))
            throw new InvalidRequestException(InvalidRequestErrors.NONEXISTENT_TOKEN);
    }

    public void validateEmployerUUID(UUID uuid) throws InvalidRequestException {
        if(uuid == null)
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_TOKEN);
        if(!modelsExistenceChecker.employerExists(uuid))
            throw new InvalidRequestException(InvalidRequestErrors.NONEXISTENT_TOKEN);
    }

    public void validateEmployerUuidVacancyNumberPair(UUID employerUuid, Integer vacancyNumber) throws InvalidRequestException {
        if(vacancyNumber == null)
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_VACANCY_NUMBER);
        if(!modelsExistenceChecker.vacancyExists(employerUuid, vacancyNumber))
            throw new InvalidRequestException(InvalidRequestErrors.NONEXISTENT_VACANCY);
    }

    public void validateVacancyRequirementsForDeleting(UUID employerUuid, Integer vacancyNumber, Set<String> requirementsNames) throws InvalidRequestException {
        if(requirementsNames != null)
            for(String requirementName: requirementsNames)
                if(!modelsExistenceChecker.vacancyRequirementPairExists(employerUuid, vacancyNumber, requirementName))
                    throw new InvalidRequestException(InvalidRequestErrors.NONEXISTENT_VACANCY_REQUIREMENT_PAIR);
    }

    public void validateSkillsForDeleting(UUID employeeUuid, Set<String> skillsName) throws InvalidRequestException {
        if(skillsName != null)
            for(String skillName: skillsName)
                if(!modelsExistenceChecker.skillExists(employeeUuid, skillName))
                    throw new InvalidRequestException(InvalidRequestErrors.NONEXISTENT_SKILL);
    }

    public void validateLoginUniqueness(String login) throws InvalidRequestException {
        if(modelsExistenceChecker.loginIsBusy(login))
            throw new InvalidRequestException(InvalidRequestErrors.ALREADY_TAKEN_LOGIN);
    }

    public void validateEmployeeLogin(String login) throws InvalidRequestException {
        if(login == null)
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_EMPLOYEE_LOGIN);
        if(!modelsExistenceChecker.loginBelongsEmployee(login))
            throw new InvalidRequestException(InvalidRequestErrors.NONEXISTENT_EMPLOYEE_LOGIN);
    }
}
