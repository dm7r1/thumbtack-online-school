package net.thumbtack.school.hiring.data.models.requests.checkers;

import net.thumbtack.school.hiring.data.DataStorage;
import net.thumbtack.school.hiring.data.dao.EmployeeDao;
import net.thumbtack.school.hiring.data.dao.EmployerDao;
import net.thumbtack.school.hiring.data.dao.VacancyDao;
import net.thumbtack.school.hiring.data.daoimpl.EmployeeDaoImpl;
import net.thumbtack.school.hiring.data.daoimpl.EmployerDaoImpl;
import net.thumbtack.school.hiring.data.daoimpl.VacancyDaoImpl;

import java.util.UUID;

public class ModelsExistenceCheckerImpl implements ModelsExistenceChecker {
    private EmployerDao employerDao;
    private EmployeeDao employeeDao;
    private VacancyDao vacancyDao;

    public ModelsExistenceCheckerImpl(DataStorage dataStorage) {
        employeeDao = new EmployeeDaoImpl(dataStorage);
        employerDao = new EmployerDaoImpl(dataStorage);
        vacancyDao = new VacancyDaoImpl(dataStorage);
    }

    @Override
    public boolean employeeExists(UUID uuid) {
        return employeeDao.employeeExists(uuid);
    }

    @Override
    public boolean employerExists(UUID uuid) {
        return employerDao.employerExists(uuid);
    }

    @Override
    public boolean vacancyExists(UUID employerUuid, int vacancyNumber) {
        return vacancyNumber >= 0 && vacancyNumber < vacancyDao.getEmployerVacancies(employerUuid).size();
    }

    @Override
    public boolean vacancyRequirementPairExists(UUID employerUuid, int vacancyNumber, String requirementName) {
        return vacancyExists(employerUuid, vacancyNumber) && (vacancyDao.getVacancy(employerUuid, vacancyNumber).getRequirements().existsRequirement(requirementName));
    }

    @Override
    public boolean skillExists(UUID employeeUuid, String skillName) {
        return employeeDao.getEmployeeByUUID(employeeUuid).getSkills().skillExists(skillName);
    }

    @Override
    public boolean loginIsBusy(String login) {
        return employeeDao.loginBelongsEmployee(login) || employerDao.loginBelongsEmployer(login);
    }

    @Override
    public boolean loginBelongsEmployee(String login) {
        return employeeDao.loginBelongsEmployee(login);
    }
}
