package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.RequirementProperties;
import net.thumbtack.school.hiring.data.models.RequirementsList;
import net.thumbtack.school.hiring.data.models.requests.base.AbstractExistingPersonDirectedDtoRequest;

import java.util.Map;
import java.util.Set;

public class ChangeVacancyDtoRequest extends AbstractExistingPersonDirectedDtoRequest {
    private Integer vacancyNumber;
    private String newVacancyName;
    private Integer newPayment;
    private Map<String, RequirementProperties> newOrChangedRequirements;
    private Set<String> requirementsForDeletingNames;

    public Integer getVacancyNumber() {
        return vacancyNumber;
    }

    public void setVacancyNumber(Integer vacancyNumber) {
        this.vacancyNumber = vacancyNumber;
    }

    public String getNewVacancyName() {
        return newVacancyName;
    }

    public void setNewVacancyName(String newVacancyName) {
        this.newVacancyName = newVacancyName;
    }

    public Integer getNewPayment() {
        return newPayment;
    }

    public void setNewPayment(Integer newPayment) {
        this.newPayment = newPayment;
    }

    public RequirementsList getNewOrChangedRequirements() {
        if(newOrChangedRequirements != null)
            return RequirementsList.fromMap(newOrChangedRequirements);
        else
            return null;
    }

    public void setNewOrChangedRequirements(RequirementsList newOrChangedRequirements) {
        if(newOrChangedRequirements != null)
            this.newOrChangedRequirements = newOrChangedRequirements.toMap();
        else
            this.newOrChangedRequirements = null;
    }

    public Set<String> getRequirementsForDeletingNames() {
        return requirementsForDeletingNames;
    }

    public void setRequirementsForDeletingNames(Set<String> requirementsForDeletingNames) {
        this.requirementsForDeletingNames = requirementsForDeletingNames;
    }
}
