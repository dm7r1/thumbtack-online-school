package net.thumbtack.school.hiring.data.models.responses;

import net.thumbtack.school.hiring.data.models.Vacancy;
import net.thumbtack.school.hiring.data.models.responses.specialModels.ShortVacancyInfoDto;

import java.util.LinkedList;
import java.util.List;

public class RightVacanciesListDtoResponse implements DtoResponse {
    private List<ShortVacancyInfoDto> rightVacancies;

    public static RightVacanciesListDtoResponse makeInstance(List<Vacancy> rightVacancies) {
        RightVacanciesListDtoResponse response = new RightVacanciesListDtoResponse();
        response.setRightVacancies(rightVacancies);
        return response;
    }

    public void setRightVacancies(List<Vacancy> rightVacancies) {
        this.rightVacancies = new LinkedList<>();
        addRightVacancies(rightVacancies);
    }

    public void addRightVacancies(List<Vacancy> rightVacancies) {
        for(Vacancy vacancy: rightVacancies) {
            addRightVacancy(vacancy);
        }
    }

    public void addRightVacancy(Vacancy vacancy) {
        rightVacancies.add(ShortVacancyInfoDto.fromVacancy(vacancy));
    }

    public List<ShortVacancyInfoDto> getRightVacancies() {
        return rightVacancies;
    }
}
