package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.RequirementProperties;
import net.thumbtack.school.hiring.data.models.RequirementsList;
import net.thumbtack.school.hiring.data.models.requests.base.AbstractExistingPersonDirectedDtoRequest;

import java.util.Map;


public class AddVacancyDtoRequest extends AbstractExistingPersonDirectedDtoRequest {
    private String vacancyName;
    private Integer payment;
    private Map<String, RequirementProperties> requirements;

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public RequirementsList getRequirements() {
        return RequirementsList.fromMap(requirements);
    }

    public void setRequirements(RequirementsList requirements) {
        this.requirements = requirements.toMap();
    }
}
