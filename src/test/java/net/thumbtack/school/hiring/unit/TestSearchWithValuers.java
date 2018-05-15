package net.thumbtack.school.hiring.unit;

import net.thumbtack.school.hiring.data.DataStorage;
import net.thumbtack.school.hiring.data.dao.EmployeeDao;
import net.thumbtack.school.hiring.data.dao.VacancyDao;
import net.thumbtack.school.hiring.data.daoimpl.EmployeeDaoImpl;
import net.thumbtack.school.hiring.data.daoimpl.VacancyDaoImpl;
import net.thumbtack.school.hiring.data.models.requests.SpecialModelsFactory;
import net.thumbtack.school.hiring.data.models.stored.*;
import net.thumbtack.school.hiring.services.special.search.EmployeeValuer;
import net.thumbtack.school.hiring.unit.specialModels.EmployeeDaoEmployerUuidAndEmployeesList;
import net.thumbtack.school.hiring.unit.specialModels.VacancyDaoEmployeeUuidAndVacanciesList;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TestSearchWithValuers {
    private static EmployeeValuer valuerAlwaysFalse = (SkillsList employeeSkills, RequirementsList vacancyRequirements) -> false;
    private static EmployeeValuer valuerAlwaysTrue = (SkillsList employeeSkills, RequirementsList vacancyRequirements) -> true;

    private EmployeeDaoEmployerUuidAndEmployeesList getEmployeeDaoEmployerUuidAndEmployeesListForTest() {
        EmployeeDaoEmployerUuidAndEmployeesList employeeDaoEmployerUuidAndEmployeesList = new EmployeeDaoEmployerUuidAndEmployeesList();

        DataStorage dataStorage = DataStorage.getEmptyInstance();
        EmployeeDao employeeDao = new EmployeeDaoImpl(dataStorage);

        UUID employerUuid = UUID.randomUUID();
        Employer employer = SpecialModelsFactory.getValidEmployer();

        dataStorage.getEmployers().put(employerUuid, employer);

        int employeesCount = 5;
        Map<UUID, Employee> employees = new HashMap<>();
        for(int i = 0; i < employeesCount; ++i) {
            UUID employeeUuid = UUID.randomUUID();
            Employee employee = SpecialModelsFactory.getValidEmployee();
            dataStorage.getEmployees().put(employeeUuid, employee);
            employees.put(employeeUuid, employee);
        }

        employer.addVacancy(SpecialModelsFactory.getValidVacancy());

        employeeDaoEmployerUuidAndEmployeesList.setEmployeeDao(employeeDao);
        employeeDaoEmployerUuidAndEmployeesList.setEmployerUuid(employerUuid);
        employeeDaoEmployerUuidAndEmployeesList.setEmployeesList(new ArrayList<>(employees.values()));

        return employeeDaoEmployerUuidAndEmployeesList;
    }

    @Test
    public void testGetEmployeesByVacancyRequirements_ValuerAlwaysFalse() {
        EmployeeDaoEmployerUuidAndEmployeesList employeeDaoEmployerUuidAndEmployeesList = getEmployeeDaoEmployerUuidAndEmployeesListForTest();

        List<Employee> gottenEmployees = employeeDaoEmployerUuidAndEmployeesList.getEmployeeDao().getEmployeesByVacancyRequirements(
                employeeDaoEmployerUuidAndEmployeesList.getEmployerUuid(), 0, valuerAlwaysFalse);
        assertEquals(0, gottenEmployees.size());
    }

    @Test
    public void testGetEmployeesByVacancyRequirements_valuerAlwaysTrueAllEmployeesAreActive() {
        EmployeeDaoEmployerUuidAndEmployeesList employeeDaoEmployerUuidAndEmployeesList = getEmployeeDaoEmployerUuidAndEmployeesListForTest();

        List<Employee> gottenEmployees = employeeDaoEmployerUuidAndEmployeesList.getEmployeeDao().getEmployeesByVacancyRequirements(
                employeeDaoEmployerUuidAndEmployeesList.getEmployerUuid(), 0, valuerAlwaysTrue);
        Set<Employee> gottenEmployeesSet = new HashSet<>(gottenEmployees);
        Set<Employee> expectedEmployeesSet = new HashSet<>(employeeDaoEmployerUuidAndEmployeesList.getEmployeesList());
        assertEquals(expectedEmployeesSet, gottenEmployeesSet);
    }

    @Test
    public void testGetEmployeesByVacancyRequirements_valuerAlwaysTrueNotAllEmployeesAreActive() {
        EmployeeDaoEmployerUuidAndEmployeesList employeeDaoEmployerUuidAndEmployeesList = getEmployeeDaoEmployerUuidAndEmployeesListForTest();

        Employee inactiveEmployee = employeeDaoEmployerUuidAndEmployeesList.getEmployeesList().get(0);
        inactiveEmployee.setActive(false);
        List<Employee> gottenEmployees = employeeDaoEmployerUuidAndEmployeesList.getEmployeeDao().getEmployeesByVacancyRequirements(
                employeeDaoEmployerUuidAndEmployeesList.getEmployerUuid(), 0, valuerAlwaysTrue);
        Set<Employee> gottenEmployeesSet = new HashSet<>(gottenEmployees);

        Set<Employee> expectedEmployeesSet = new HashSet<>(employeeDaoEmployerUuidAndEmployeesList.getEmployeesList());
        expectedEmployeesSet.remove(inactiveEmployee);

        assertEquals(expectedEmployeesSet, gottenEmployeesSet);
    }

    private VacancyDaoEmployeeUuidAndVacanciesList getVacancyDaoEmployeeUuidAndVacanciesListForTest() {
        VacancyDaoEmployeeUuidAndVacanciesList vacancyDaoEmployeeUuidAndVacanciesList = new VacancyDaoEmployeeUuidAndVacanciesList();

        DataStorage dataStorage = DataStorage.getEmptyInstance();
        VacancyDao vacancyDao = new VacancyDaoImpl(dataStorage);

        UUID employeeUuid = UUID.randomUUID();
        Employee employee = SpecialModelsFactory.getValidEmployee();

        dataStorage.getEmployees().put(employeeUuid, employee);

        Employer employer = SpecialModelsFactory.getValidEmployer();
        dataStorage.getEmployers().put(UUID.randomUUID(), employer);
        int vacanciesCount = 5;
        List<Vacancy> vacancies = new LinkedList<>();
        for(int i = 0; i < vacanciesCount; ++i) {
            Vacancy vacancy = SpecialModelsFactory.getValidVacancy();

            employer.addVacancy(vacancy);
            vacancies.add(vacancy);
        }

        vacancyDaoEmployeeUuidAndVacanciesList.setVacancyDao(vacancyDao);
        vacancyDaoEmployeeUuidAndVacanciesList.setEmployeeUuid(employeeUuid);
        vacancyDaoEmployeeUuidAndVacanciesList.setVacanciesList(vacancies);

        return vacancyDaoEmployeeUuidAndVacanciesList;
    }

    @Test
    public void testGetVacanciesByEmployeeSkills_valuerAlwaysFalse() {
        VacancyDaoEmployeeUuidAndVacanciesList vacancyDaoEmployeeUuidAndVacanciesList = getVacancyDaoEmployeeUuidAndVacanciesListForTest();

        List<Vacancy> gottenVacancies = vacancyDaoEmployeeUuidAndVacanciesList.getVacancyDao().getVacanciesByEmployeeSkills(
                vacancyDaoEmployeeUuidAndVacanciesList.getEmployeeUuid(), valuerAlwaysFalse);
        assertEquals(0, gottenVacancies.size());
    }

    @Test
    public void testGetVacanciesByEmployeeSkills_valuerAlwaysTrueAllVacanciesAreActive() {
        VacancyDaoEmployeeUuidAndVacanciesList vacancyDaoEmployeeUuidAndVacanciesList = getVacancyDaoEmployeeUuidAndVacanciesListForTest();

        List<Vacancy> gottenVacancies = vacancyDaoEmployeeUuidAndVacanciesList.getVacancyDao().getVacanciesByEmployeeSkills(
                vacancyDaoEmployeeUuidAndVacanciesList.getEmployeeUuid(), valuerAlwaysTrue);
        Set<Vacancy> gottenVacanciesSet = new HashSet<>(gottenVacancies);
        Set<Vacancy> expectedVacanciesSet = new HashSet<>(vacancyDaoEmployeeUuidAndVacanciesList.getVacanciesList());
        assertEquals(expectedVacanciesSet, gottenVacanciesSet);
    }

    @Test
    public void testGetVacanciesByEmployeeSkills_valuerAlwaysTrueNotAllVacanciesAreActive() {
        VacancyDaoEmployeeUuidAndVacanciesList vacancyDaoEmployeeUuidAndVacanciesList = getVacancyDaoEmployeeUuidAndVacanciesListForTest();

        Vacancy inactiveVacancy = vacancyDaoEmployeeUuidAndVacanciesList.getVacanciesList().get(0);
        inactiveVacancy.setActive(false);
        List<Vacancy> gottenVacancies = vacancyDaoEmployeeUuidAndVacanciesList.getVacancyDao().getVacanciesByEmployeeSkills(
                vacancyDaoEmployeeUuidAndVacanciesList.getEmployeeUuid(), valuerAlwaysTrue);
        Set<Vacancy> gottenVacanciesSet = new HashSet<>(gottenVacancies);

        Set<Vacancy> expectedVacanciesSet = new HashSet<>(vacancyDaoEmployeeUuidAndVacanciesList.getVacanciesList());
        expectedVacanciesSet.remove(inactiveVacancy);

        assertEquals(expectedVacanciesSet, gottenVacanciesSet);
    }
}
