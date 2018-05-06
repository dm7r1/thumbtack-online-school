package net.thumbtack.school.hiring.data.models.requests.utils.checkers;

import java.util.UUID;

public interface ModelsExistenceChecker {
    boolean employeeExists(UUID uuid);

    boolean employerExists(UUID uuid);

    boolean vacancyExists(UUID employerUuid, int vacancyNumber);

    boolean vacancyRequirementPairExists(UUID employerUuid, int vacancyNumber, String requirementName);

    boolean skillExists(UUID employeeUuid, String skillName);
}
