package net.thumbtack.school.hiring.data.models.requests.utils;

import net.thumbtack.school.hiring.data.models.InvalidRequestErrors;
import net.thumbtack.school.hiring.data.models.InvalidRequestException;

import java.util.UUID;

public class PersonExistenceValidator {
    private PersonExistenceChecker personExistenceChecker;

    public PersonExistenceValidator(PersonExistenceChecker personExistenceChecker) {
        this.personExistenceChecker = personExistenceChecker;
    }

    public void validateEmployeeUUID(UUID uuid) throws InvalidRequestException {
        if(uuid == null)
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_TOKEN);
        if(!personExistenceChecker.employeeExists(uuid)) {
            throw new InvalidRequestException(InvalidRequestErrors.NONEXISTENT_TOKEN);
        }
    }

    public void validateEmployerUUID(UUID uuid) throws InvalidRequestException {
        if(uuid == null)
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_TOKEN);
        if(!personExistenceChecker.employerExists(uuid)) {
            throw new InvalidRequestException(InvalidRequestErrors.NONEXISTENT_TOKEN);
        }
    }
}
