package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.requests.base.AbstractExistingPersonDirectedDtoRequest;

public class HireEmployeeDtoRequest extends AbstractExistingPersonDirectedDtoRequest {
    private String login;
    public Integer vacancyNumber;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getVacancyNumber() {
        return vacancyNumber;
    }

    public void setVacancyNumber(Integer vacancyNumber) {
        this.vacancyNumber = vacancyNumber;
    }
}
