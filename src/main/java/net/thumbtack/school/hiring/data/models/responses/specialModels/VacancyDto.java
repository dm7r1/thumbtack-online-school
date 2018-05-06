package net.thumbtack.school.hiring.data.models.responses.specialModels;

import net.thumbtack.school.hiring.data.models.RequirementProperties;
import net.thumbtack.school.hiring.data.models.RequirementsList;
import net.thumbtack.school.hiring.data.models.Vacancy;

import java.util.Map;

public class VacancyDto {
    private String vacancyName;
    private int payment;
    private Map<String, RequirementProperties> requirements;

    public static VacancyDto fromVacancy(Vacancy vacancy) {
        VacancyDto vacancyDto = new VacancyDto();
        vacancyDto.vacancyName = vacancy.getVacancyName();
        vacancyDto.payment = vacancy.getPayment();
        vacancyDto.requirements = vacancy.getRequirements().toMap();

        return vacancyDto;
    }

    public Vacancy toVacancy() {
        return new Vacancy(vacancyName, payment, RequirementsList.fromMap(requirements));
    }
}
