package net.thumbtack.school.hiring.data.daoimpl;

import net.thumbtack.school.hiring.data.DataStorage;
import net.thumbtack.school.hiring.data.dao.EmployeeDao;
import net.thumbtack.school.hiring.data.models.stored.Employee;
import net.thumbtack.school.hiring.data.models.stored.RequirementsList;
import net.thumbtack.school.hiring.services.special.search.EmployeeValuer;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class EmployeeDaoImpl implements EmployeeDao {
    private DataStorage dataStorage;

    public EmployeeDaoImpl(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public UUID insertEmployee(Employee employee) {
        UUID uuid = UUID.randomUUID();
        dataStorage.getEmployees().put(uuid, employee);
        return uuid;
    }

    public Employee getEmployeeByUUID(UUID uuid) {
        return dataStorage.getEmployees().get(uuid);
    }

    public Employee getEmployeeByLogin(String login) {
        for(Employee employee: dataStorage.getEmployees().values())
            if(employee.getLogin().equals(login))
                return employee;
        return null;
    }

    public void setEmployeeActive(UUID employeeUUID, boolean active) {
        dataStorage.getEmployees().get(employeeUUID).setActive(active);
    }

    public void setEmployeeActive(String login, boolean active) {
        getEmployeeByLogin(login).setActive(active);
    }

    public boolean loginBelongsEmployee(String login) {
        for(Employee employee: dataStorage.getEmployees().values()) {
            if (employee.getLogin().equals(login))
                return true;
        }
        return false;
    }

    public boolean employeeExists(UUID uuid) {
        return dataStorage.getEmployees().containsKey(uuid);
    }

    public List<Employee> getEmployeesByVacancyRequirements(UUID employerUUID, int vacancyNumber, EmployeeValuer employeeValuer) {
        List<Employee> rightEmployees = new LinkedList<>();
        RequirementsList requirements = dataStorage.getEmployers().get(employerUUID).getVacancy(vacancyNumber).getRequirements();
        for(Employee employee: dataStorage.getEmployees().values())
            if(employee.isActive())
                if(employeeValuer.isEmployeeRight(employee.getSkills(), requirements))
                    rightEmployees.add(employee);
        return rightEmployees;
    }

    public void deleteEmployeeByUUID(UUID uuid) {
        dataStorage.getEmployees().remove(uuid);
    }
}
