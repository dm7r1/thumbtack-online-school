package net.thumbtack.school.hiring.data.dao;

import net.thumbtack.school.hiring.data.models.stored.RequirementsList;
import net.thumbtack.school.hiring.data.models.stored.Vacancy;
import net.thumbtack.school.hiring.services.special.search.EmployeeValuer;

import java.util.List;
import java.util.UUID;

public interface VacancyDao {
    void insertVacancy(UUID employerUUID, Vacancy vacancy);

    void setVacancyActive(UUID employerUUID, int vacancyNumber, boolean active);

    Vacancy getVacancy(UUID uuid, int vacancyNumber);

    List<Vacancy> getEmployerVacancies(UUID uuid);

    void addVacancyRequirements(UUID employerUUID, int vacancyNumber, RequirementsList requirements);

    List<Vacancy> getVacanciesByEmployeeSkills(UUID employeeUUID, EmployeeValuer employeeValuer);

    void deleteVacancy(UUID uuid, int vacancyNumber);
}
