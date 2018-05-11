package net.thumbtack.school.hiring.data.dao;

import net.thumbtack.school.hiring.data.models.stored.Employee;
import net.thumbtack.school.hiring.services.special.search.EmployeeValuer;

import java.util.List;
import java.util.UUID;

public interface EmployeeDao {
    UUID insertEmployee(Employee employee);

    Employee getEmployeeByUUID(UUID uuid);

    boolean employeeExists(UUID uuid);

    void setEmployeeActive(UUID employeeUUID, boolean active);

    void setEmployeeActive(String login, boolean active);

    List<Employee> getEmployeesByVacancyRequirements(UUID employerUUID, int vacancyNumber, EmployeeValuer employeeValuer);

    boolean loginBelongsEmployee(String login);

    void deleteEmployeeByUUID(UUID uuid);
}
