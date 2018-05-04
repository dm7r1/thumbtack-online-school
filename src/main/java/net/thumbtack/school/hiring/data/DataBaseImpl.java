package net.thumbtack.school.hiring.data;

import net.thumbtack.school.hiring.data.models.Employee;
import net.thumbtack.school.hiring.data.models.Employer;
import net.thumbtack.school.hiring.data.models.Vacancy;

import java.util.ArrayList;
import java.util.UUID;

public class DataBaseImpl implements DataBase {
    private DataStorage dataStorage;

    public DataBaseImpl() {
        dataStorage = new DataStorage();
    }

    public UUID insertEmployee(Employee employee) {
        UUID uuid = UUID.randomUUID();
        dataStorage.getEmployees().put(uuid, employee);
        return uuid;
    }

    public UUID insertEmployer(Employer employer)
    {
        UUID uuid = UUID.randomUUID();
        dataStorage.getEmployers().put(uuid, employer);
        dataStorage.getVacations().put(uuid, new ArrayList<>());
        return uuid;
    }

    public void insertVacancy(UUID employerUUID, Vacancy vacancy) {
        dataStorage.getVacations().get(employerUUID).add(vacancy);
    }

    public Employee getEmployeeByUUID(UUID uuid) {
        return dataStorage.getEmployees().get(uuid);
    }

    public Employer getEmployerByUUID(UUID uuid) {
        return dataStorage.getEmployers().get(uuid);
    }

    @Override
    public boolean employeeExists(UUID uuid) {
        return dataStorage.getEmployees().containsKey(uuid);
    }

    @Override
    public boolean employerExists(UUID uuid) {
        return dataStorage.getEmployers().containsKey(uuid);
    }

    @Override
    public void deleteEmployeeByUUID(UUID uuid) {
        dataStorage.getEmployees().remove(uuid);
    }

    @Override
    public void deleteEmployerByUUID(UUID uuid) {
        dataStorage.getEmployers().remove(uuid);
    }
}
