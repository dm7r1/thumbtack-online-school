package net.thumbtack.school.hiring.data.dao;

import net.thumbtack.school.hiring.data.models.stored.Employer;

import java.util.UUID;

public interface EmployerDao {
    UUID insertEmployer(Employer employer);

    Employer getEmployerByUUID(UUID uuid);

    boolean employerExists(UUID uuid);

    boolean loginBelongsEmployer(String login);

    void deleteEmployerByUUID(UUID uuid);
}
