package net.thumbtack.school.hiring.unit.specialModels;

import net.thumbtack.school.hiring.data.dao.EmployeeDao;
import net.thumbtack.school.hiring.data.models.stored.Employee;

import java.util.List;
import java.util.UUID;

public class EmployeeDaoEmployerUuidAndEmployeesList {
    private EmployeeDao employeeDao;
    private UUID employerUuid;
    private List<Employee> employeesList;

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public UUID getEmployerUuid() {
        return employerUuid;
    }

    public void setEmployerUuid(UUID employerUuid) {
        this.employerUuid = employerUuid;
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }
}
