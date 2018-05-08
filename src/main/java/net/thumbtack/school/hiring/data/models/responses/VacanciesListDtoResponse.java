package net.thumbtack.school.hiring.data.models.responses;

import net.thumbtack.school.hiring.data.models.Vacancy;
import net.thumbtack.school.hiring.data.models.responses.specialModels.VacancyDto;

import java.util.ArrayList;
import java.util.List;

public class VacanciesListDtoResponse implements DtoResponse {
    private List<VacancyDto> vacancies;

    public static VacanciesListDtoResponse makeInstance(List<Vacancy> vacancies) {
        VacanciesListDtoResponse response = new VacanciesListDtoResponse();
        response.vacancies = new ArrayList<>(vacancies.size());
        for(Vacancy vacancy: vacancies) {
            response.vacancies.add(VacancyDto.fromVacancy(vacancy));
        }

        return response;
    }

    public List<Vacancy> getVacancies() {
        List<Vacancy> vacancies = new ArrayList<>(this.vacancies.size());
        for(VacancyDto vacancyDto: this.vacancies) {
            vacancies.add(vacancyDto.toVacancy());
        }

        return vacancies;
    }
}
