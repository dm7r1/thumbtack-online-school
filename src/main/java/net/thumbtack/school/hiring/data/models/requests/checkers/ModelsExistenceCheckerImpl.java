package net.thumbtack.school.hiring.data.models.requests.checkers;

import net.thumbtack.school.hiring.data.DataBase;

import java.util.UUID;

public class ModelsExistenceCheckerImpl implements ModelsExistenceChecker {
    private DataBase dataBase;
    public ModelsExistenceCheckerImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public boolean employeeExists(UUID uuid) {
        return this.dataBase.employeeExists(uuid);
    }

    @Override
    public boolean employerExists(UUID uuid) {
        return this.dataBase.employerExists(uuid);
    }

    @Override
    public boolean vacancyExists(UUID employerUuid, int vacancyNumber) {
        return vacancyNumber >= 0 && vacancyNumber < dataBase.getEmployerVacancies(employerUuid).size();
    }

    @Override
    public boolean vacancyRequirementPairExists(UUID employerUuid, int vacancyNumber, String requirementName) {
        return vacancyExists(employerUuid, vacancyNumber) && (dataBase.getVacancy(employerUuid, vacancyNumber).getRequirements().existsRequirement(requirementName));
    }

    @Override
    public boolean skillExists(UUID employeeUuid, String skillName) {
        return dataBase.getEmployeeByUUID(employeeUuid).getSkills().skillExists(skillName);
    }

    @Override
    public boolean loginIsBusy(String login) {
        return dataBase.loginIsBusy(login);
    }

    @Override
    public boolean loginBelongsEmployee(String login) {
        return dataBase.loginBelongsEmployee(login);
    }
}
