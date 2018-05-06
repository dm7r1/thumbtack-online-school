package net.thumbtack.school.hiring.data;

import net.thumbtack.school.hiring.data.models.Employee;
import net.thumbtack.school.hiring.data.models.Employer;
import net.thumbtack.school.hiring.data.models.SkillsList;
import net.thumbtack.school.hiring.data.models.Vacancy;

import java.util.ArrayList;
import java.util.List;
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
        return uuid;
    }

    public void insertVacancy(UUID employerUUID, Vacancy vacancy) {
        dataStorage.getEmployers().get(employerUUID).addVacancy(vacancy);
    }

    public Employee getEmployeeByUUID(UUID uuid) {
        return dataStorage.getEmployees().get(uuid);
    }

    public Employer getEmployerByUUID(UUID uuid) {
        return dataStorage.getEmployers().get(uuid);
    }

    public Vacancy getVacancy(UUID uuid, int vacancyNumber) {
        return dataStorage.getEmployers().get(uuid).getVacancies().get(vacancyNumber);
    }

    public List<Vacancy> getEmployerVacancies(UUID uuid) {
        return dataStorage.getEmployers().get(uuid).getVacancies();
    }

    public SkillsList getEmployeeSkills(UUID uuid) {
        return dataStorage.getEmployees().get(uuid).getSkills();
    }

    public boolean employeeExists(UUID uuid) {
        return dataStorage.getEmployees().containsKey(uuid);
    }

    public boolean employerExists(UUID uuid) {
        return dataStorage.getEmployers().containsKey(uuid);
    }

    public void deleteEmployeeByUUID(UUID uuid) {
        dataStorage.getEmployees().remove(uuid);
    }

    public void deleteEmployerByUUID(UUID uuid) {
        dataStorage.getEmployers().remove(uuid);
    }

    public void deleteVacancy(UUID uuid, int vacancyNumber) {
        dataStorage.getEmployers().get(uuid).getVacancies().remove(vacancyNumber);
    }
}
