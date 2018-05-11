package net.thumbtack.school.hiring.unit;

import net.thumbtack.school.hiring.data.DataStorage;
import net.thumbtack.school.hiring.data.dao.EmployeeDao;
import net.thumbtack.school.hiring.data.dao.VacancyDao;
import net.thumbtack.school.hiring.data.daoimpl.EmployeeDaoImpl;
import net.thumbtack.school.hiring.data.daoimpl.VacancyDaoImpl;
import net.thumbtack.school.hiring.data.models.requests.SpecialModelsFactory;
import net.thumbtack.school.hiring.data.models.stored.*;
import net.thumbtack.school.hiring.services.special.search.EmployeeValuer;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TestSearchWithValuers {
    @Test
    public void testGetEmployeesByVacancyRequirements() {
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

        EmployeeValuer valuerAlwaysFalse = (SkillsList employeeSkills, RequirementsList vacancyRequirements) -> false;
        EmployeeValuer valuerAlwaysTrue = (SkillsList employeeSkills, RequirementsList vacancyRequirements) -> true;

        List<Employee> gottenEmployees = employeeDao.getEmployeesByVacancyRequirements(employerUuid, 0, valuerAlwaysFalse);
        assertEquals(0, gottenEmployees.size());

        gottenEmployees = employeeDao.getEmployeesByVacancyRequirements(employerUuid, 0, valuerAlwaysTrue);
        Set<Employee> gottenEmployeesSet = new HashSet<>(gottenEmployees);
        Set<Employee> expectedEmployeesSet = new HashSet<>(employees.values());
        assertEquals(expectedEmployeesSet, gottenEmployeesSet);

        Employee inactiveEmployee = new LinkedList<>(employees.values()).get(0);
        inactiveEmployee.setActive(false);
        gottenEmployees = employeeDao.getEmployeesByVacancyRequirements(employerUuid, 0, valuerAlwaysTrue);
        gottenEmployeesSet = new HashSet<>(gottenEmployees);

        expectedEmployeesSet = new HashSet<>(employees.values());
        expectedEmployeesSet.remove(inactiveEmployee);

        assertEquals(expectedEmployeesSet, gottenEmployeesSet);
    }

    @Test
    public void testGetVacanciesByEmployeeSkills() {
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

        EmployeeValuer valuerAlwaysFalse = (SkillsList employeeSkills, RequirementsList vacancyRequirements) -> false;
        EmployeeValuer valuerAlwaysTrue = (SkillsList employeeSkills, RequirementsList vacancyRequirements) -> true;

        List<Vacancy> gottenVacancies = vacancyDao.getVacanciesByEmployeeSkills(employeeUuid, valuerAlwaysFalse);
        assertEquals(0, gottenVacancies.size());

        gottenVacancies = vacancyDao.getVacanciesByEmployeeSkills(employeeUuid, valuerAlwaysTrue);
        Set<Vacancy> gottenVacanciesSet = new HashSet<>(gottenVacancies);
        Set<Vacancy> expectedVacanciesSet = new HashSet<>(vacancies);
        assertEquals(expectedVacanciesSet, gottenVacanciesSet);

        Vacancy inactiveVacancy = vacancies.get(0);
        inactiveVacancy.setActive(false);
        gottenVacancies = vacancyDao.getVacanciesByEmployeeSkills(employeeUuid, valuerAlwaysTrue);
        gottenVacanciesSet = new HashSet<>(gottenVacancies);

        expectedVacanciesSet = new HashSet<>(vacancies);
        expectedVacanciesSet.remove(inactiveVacancy);

        assertEquals(expectedVacanciesSet, gottenVacanciesSet);
    }
}
