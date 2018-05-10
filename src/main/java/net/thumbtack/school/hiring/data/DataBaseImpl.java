package net.thumbtack.school.hiring.data;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.stored.*;
import net.thumbtack.school.hiring.services.special.search.EmployeeValuer;

import java.io.*;
import java.util.*;

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
        getEmployerByUUID(employerUUID).addVacancy(vacancy);
        dataStorage.getDefinedSkills().addAll(vacancy.getRequirements().getRequirementsNamesSet());
    }

    public void addEmployeeSkills(UUID employeeUUID, SkillsList skills) {
        getEmployeeByUUID(employeeUUID).addSkills(skills);
        dataStorage.getDefinedSkills().addAll(skills.getSkillsNamesSet());
    }

    public void addVacancyRequirements(UUID employerUUID, int vacancyNumber, RequirementsList requirements) {
        getVacancy(employerUUID, vacancyNumber).addRequirements(requirements);
        dataStorage.getDefinedSkills().addAll(requirements.getRequirementsNamesSet());
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

    public Employer getEmployerByUUID(UUID uuid) {
        return dataStorage.getEmployers().get(uuid);
    }

    public Vacancy getVacancy(UUID uuid, int vacancyNumber) {
        return getEmployerByUUID(uuid).getVacancy(vacancyNumber);
    }

    public List<Vacancy> getEmployerVacancies(UUID uuid) {
        return getEmployerByUUID(uuid).getVacancies();
    }

    public SkillsList getEmployeeSkills(UUID uuid) {
        return getEmployeeByUUID(uuid).getSkills();
    }

    public Set<String> getDefinedSkillsNames() {
        return dataStorage.getDefinedSkills();
    }

    public List<Employee> getEmployeesByVacancyRequirements(UUID employerUUID, int vacancyNumber, EmployeeValuer employeeValuer) {
        List<Employee> rightEmployees = new LinkedList<>();
        RequirementsList requirements = getVacancy(employerUUID, vacancyNumber).getRequirements();
        for(Employee employee: dataStorage.getEmployees().values())
            if(employee.isActive())
                if(employeeValuer.isEmployeeRight(employee.getSkills(), requirements))
                    rightEmployees.add(employee);
        return rightEmployees;
    }

    public List<Vacancy> getVacanciesByEmployeeSkills(UUID employeeUUID, EmployeeValuer employeeValuer) {
        List<Vacancy> rightVacancies = new LinkedList<>();
        SkillsList employeeSkills = getEmployeeSkills(employeeUUID);
        for(Employer employer: dataStorage.getEmployers().values()) {
            for (Vacancy vacancy : employer.getVacancies())
                if(vacancy.isActive())
                    if (employeeValuer.isEmployeeRight(employeeSkills, vacancy.getRequirements()))
                        rightVacancies.add(vacancy);
        }
        return rightVacancies;
    }

    public boolean employeeExists(UUID uuid) {
        return dataStorage.getEmployees().containsKey(uuid);
    }

    public boolean employerExists(UUID uuid) {
        return dataStorage.getEmployers().containsKey(uuid);
    }

    public boolean loginIsBusy(String login) {
        for(Employee employee: dataStorage.getEmployees().values())
            if(employee.getLogin().equals(login))
                return true;
        for(Employer employer: dataStorage.getEmployers().values())
            if(employer.getLogin().equals(login))
                return true;
        return false;
    }

    public boolean loginBelongsEmployee(String login) {
        for(Employee employee: dataStorage.getEmployees().values()) {
            if (employee.getLogin().equals(login))
                return true;
        }
        return false;
    }

    public void deleteEmployeeByUUID(UUID uuid) {
        dataStorage.getEmployees().remove(uuid);
    }

    public void deleteEmployerByUUID(UUID uuid) {
        dataStorage.getEmployers().remove(uuid);
    }

    public void deleteVacancy(UUID uuid, int vacancyNumber) {
        getEmployerByUUID(uuid).removeVacancy(vacancyNumber);
    }

    public void deleteEmployeeSkills(UUID employeeUUID, Set<String> skillsNames) {
        getEmployeeByUUID(employeeUUID).removeSkills(skillsNames);
    }

    public void setVacancyActive(UUID employerUUID, int vacancyNumber, boolean active) {
        getVacancy(employerUUID, vacancyNumber).setActive(active);
    }

    public void setEmployeeActive(UUID employeeUUID, boolean active) {
        getEmployeeByUUID(employeeUUID).setActive(active);
    }

    public void setEmployeeActive(String login, boolean active) {
        getEmployeeByLogin(login).setActive(active);
    }

    public void save(String filename) throws IOException {
        String data = new Gson().toJson(this.dataStorage);
        FileWriter fileWriter = new FileWriter(new File(filename));
        fileWriter.write(data);
        fileWriter.close();
    }

    public void load(String filename) throws IOException{
        File file = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        fileInputStream.read(data);
        dataStorage = new Gson().fromJson(new String(data), DataStorage.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataBaseImpl dataBase = (DataBaseImpl) o;
        return Objects.equals(dataStorage, dataBase.dataStorage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dataStorage);
    }
}
