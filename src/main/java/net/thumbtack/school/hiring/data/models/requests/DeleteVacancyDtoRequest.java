package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.requests.base.AbstractExistingPersonDirectedDtoRequest;

public class DeleteVacancyDtoRequest extends AbstractExistingPersonDirectedDtoRequest {
    private int vacancyNumber;

    public int getVacancyNumber() {
        return vacancyNumber;
    }

    public void setVacancyNumber(int vacancyNumber) {
        this.vacancyNumber = vacancyNumber;
    }
}
