package net.thumbtack.school.hiring.data;

import net.thumbtack.school.hiring.data.models.Employee;
import net.thumbtack.school.hiring.data.models.Employer;
import net.thumbtack.school.hiring.data.models.SkillsList;
import net.thumbtack.school.hiring.data.models.Vacancy;

import java.util.List;
import java.util.UUID;


public interface DataBase {
    UUID insertEmployee(Employee employee);

    UUID insertEmployer(Employer employer);

    void insertVacancy(UUID employerUUID, Vacancy vacancy);

    Employee getEmployeeByUUID(UUID uuid);

    Employer getEmployerByUUID(UUID uuid);

    Vacancy getVacancy(UUID uuid, int vacancyNumber);

    List<Vacancy> getEmployerVacancies(UUID uuid);

    SkillsList getEmployeeSkills(UUID uuid);

    boolean employerExists(UUID uuid);

    boolean employeeExists(UUID uuid);

    void deleteEmployeeByUUID(UUID uuid);

    void deleteEmployerByUUID(UUID uuid);

    void deleteVacancy(UUID uuid, int vacancyNumber);
}
