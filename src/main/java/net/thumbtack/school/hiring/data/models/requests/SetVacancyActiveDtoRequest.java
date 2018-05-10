package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.requests.base.AbstractExistingPersonDirectedDtoRequest;

public class SetVacancyActiveDtoRequest extends AbstractExistingPersonDirectedDtoRequest {
    private Integer vacancyNumber;
    private Boolean active;

    public Integer getVacancyNumber() {
        return vacancyNumber;
    }

    public void setVacancyNumber(Integer vacancyNumber) {
        this.vacancyNumber = vacancyNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
