package net.thumbtack.school.hiring.data;

import net.thumbtack.school.hiring.data.models.Employee;
import net.thumbtack.school.hiring.data.models.Employer;
import net.thumbtack.school.hiring.data.models.Vacancy;
import java.util.UUID;


public interface DataBase {
    UUID insertEmployee(Employee employee);

    UUID insertEmployer(Employer employer);

    void insertVacancy(UUID employerUUID, Vacancy vacancy);

    Employee getEmployeeByUUID(UUID uuid);

    Employer getEmployerByUUID(UUID uuid);

    boolean employerExists(UUID uuid);

    boolean employeeExists(UUID uuid);

    void deleteEmployeeByUUID(UUID uuid);

    void deleteEmployerByUUID(UUID uuid);
}
