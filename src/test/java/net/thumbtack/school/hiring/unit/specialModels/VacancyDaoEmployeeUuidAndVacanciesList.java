package net.thumbtack.school.hiring.unit.specialModels;

import net.thumbtack.school.hiring.data.dao.VacancyDao;
import net.thumbtack.school.hiring.data.models.stored.Vacancy;

import java.util.List;
import java.util.UUID;

public class VacancyDaoEmployeeUuidAndVacanciesList {
    private VacancyDao vacancyDao;
    private UUID employeeUuid;
    private List<Vacancy> vacanciesList;

    public VacancyDao getVacancyDao() {
        return vacancyDao;
    }

    public void setVacancyDao(VacancyDao vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    public UUID getEmployeeUuid() {
        return employeeUuid;
    }

    public void setEmployeeUuid(UUID employeeUuid) {
        this.employeeUuid = employeeUuid;
    }

    public List<Vacancy> getVacanciesList() {
        return vacanciesList;
    }

    public void setVacanciesList(List<Vacancy> vacanciesList) {
        this.vacanciesList = vacanciesList;
    }
}
