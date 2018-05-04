package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.Requirement;
import net.thumbtack.school.hiring.data.models.requests.base.AbstractExistingPersonDirectedDtoRequest;

import java.util.Map;

public class AddVacancyDtoRequest extends AbstractExistingPersonDirectedDtoRequest {
    private String vacancyName;
    private int payment;
    private Map<String, Requirement> requirements;

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public Map<String, Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(Map<String, Requirement> requirements) {
        this.requirements = requirements;
    }
}
