package net.thumbtack.school.hiring.data.daoimpl;

import net.thumbtack.school.hiring.data.DataStorage;
import net.thumbtack.school.hiring.data.dao.VacancyDao;
import net.thumbtack.school.hiring.data.models.stored.Employer;
import net.thumbtack.school.hiring.data.models.stored.RequirementsList;
import net.thumbtack.school.hiring.data.models.stored.SkillsList;
import net.thumbtack.school.hiring.data.models.stored.Vacancy;
import net.thumbtack.school.hiring.services.special.search.EmployeeValuer;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class VacancyDaoImpl implements VacancyDao {
    private DataStorage dataStorage;

    public VacancyDaoImpl(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }
    public void insertVacancy(UUID employerUUID, Vacancy vacancy) {
        dataStorage.getEmployers().get(employerUUID).addVacancy(vacancy);
        dataStorage.getDefinedSkills().addAll(vacancy.getRequirements().getRequirementsNamesSet());
    }

    public void addVacancyRequirements(UUID employerUUID, int vacancyNumber, RequirementsList requirements) {
        getVacancy(employerUUID, vacancyNumber).addRequirements(requirements);
        dataStorage.getDefinedSkills().addAll(requirements.getRequirementsNamesSet());
    }

    public Vacancy getVacancy(UUID uuid, int vacancyNumber) {
        return dataStorage.getEmployers().get(uuid).getVacancy(vacancyNumber);
    }

    public List<Vacancy> getEmployerVacancies(UUID uuid) {
        return dataStorage.getEmployers().get(uuid).getVacancies();
    }

    public List<Vacancy> getVacanciesByEmployeeSkills(UUID employeeUUID, EmployeeValuer employeeValuer) {
        List<Vacancy> rightVacancies = new LinkedList<>();
        SkillsList employeeSkills = dataStorage.getEmployees().get(employeeUUID).getSkills();
        for(Employer employer: dataStorage.getEmployers().values()) {
            for (Vacancy vacancy : employer.getVacancies())
                if(vacancy.isActive())
                    if (employeeValuer.isEmployeeRight(employeeSkills, vacancy.getRequirements()))
                        rightVacancies.add(vacancy);
        }
        return rightVacancies;
    }

    public void setVacancyActive(UUID employerUUID, int vacancyNumber, boolean active) {
        getVacancy(employerUUID, vacancyNumber).setActive(active);
    }

    public void deleteVacancy(UUID uuid, int vacancyNumber) {
        dataStorage.getEmployers().get(uuid).removeVacancy(vacancyNumber);
    }
}
