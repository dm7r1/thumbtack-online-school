package net.thumbtack.school.hiring.data.models.stored;

import java.util.Objects;

public class Vacancy {
    private String vacancyName;
    private int payment;
    private RequirementsList requirements;
    private boolean active;

    public Vacancy(String vacancyName, int payment, RequirementsList requirements) {
        this.vacancyName = vacancyName;
        this.payment = payment;
        this.requirements = requirements;
        this.active = true;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public int getPayment() {
        return payment;
    }

    public RequirementsList getRequirements() {
        return requirements;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public void setRequirements(RequirementsList requirements) {
        this.requirements = requirements;
    }

    public void addRequirements(RequirementsList newRequirements) {
        this.requirements.addRequirements(newRequirements);
    }

    public void deleteRequirement(String requirementName) {
        requirements.deleteRequirement(requirementName);
    }

    public boolean requirementExists(String requirementName) {
        return requirements.existsRequirement(requirementName);
    }

    public int getRequirementLvl(String requirementName) {
        return requirements.getRequirementLvl(requirementName);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return payment == vacancy.payment &&
                active == vacancy.active &&
                Objects.equals(vacancyName, vacancy.vacancyName) &&
                Objects.equals(requirements, vacancy.requirements);
    }

    @Override
    public int hashCode() {

        return Objects.hash(vacancyName, payment, requirements, active);
    }
}
