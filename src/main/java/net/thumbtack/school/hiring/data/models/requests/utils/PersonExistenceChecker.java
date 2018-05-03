package net.thumbtack.school.hiring.data.models.requests.utils;

import java.util.UUID;

public interface PersonExistenceChecker {
    boolean employeeExists(UUID uuid);

    boolean employerExists(UUID uuid);
}
