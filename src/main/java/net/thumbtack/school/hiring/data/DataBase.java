package net.thumbtack.school.hiring.data;

import net.thumbtack.school.hiring.data.models.stored.*;
import net.thumbtack.school.hiring.services.special.search.EmployeeValuer;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public interface DataBase {
    UUID insertEmployee(Employee employee);

    UUID insertEmployer(Employer employer);

    void insertVacancy(UUID employerUUID, Vacancy vacancy);

    void addEmployeeSkills(UUID employeeUUID, SkillsList skills);

    void addVacancyRequirements(UUID employerUUID, int vacancyNumber, RequirementsList requirements);

    Employee getEmployeeByUUID(UUID uuid);

    Employer getEmployerByUUID(UUID uuid);

    Vacancy getVacancy(UUID uuid, int vacancyNumber);

    List<Vacancy> getEmployerVacancies(UUID uuid);

    SkillsList getEmployeeSkills(UUID uuid);

    Set<String> getDefinedSkillsNames();

    List<Employee> getEmployeesByVacancyRequirements(UUID employerUUID, int vacancyNumber, EmployeeValuer employeeValuer);

    List<Vacancy> getVacanciesByEmployeeSkills(UUID employeeUUID, EmployeeValuer employeeValuer);

    boolean employerExists(UUID uuid);

    boolean employeeExists(UUID uuid);

    boolean loginIsBusy(String login);

    boolean loginBelongsEmployee(String login);

    void deleteEmployeeByUUID(UUID uuid);

    void deleteEmployerByUUID(UUID uuid);

    void deleteVacancy(UUID uuid, int vacancyNumber);

    void deleteEmployeeSkills(UUID employeeUUID, Set<String> skillsNames);

    void setVacancyActive(UUID employerUUID, int vacancyNumber, boolean active);

    void setEmployeeActive(UUID employeeUUID, boolean active);

    void setEmployeeActive(String login, boolean active);

    void save(String filename) throws IOException;

    void load(String filename) throws IOException;
}
