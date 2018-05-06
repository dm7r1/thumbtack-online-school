package net.thumbtack.school.hiring.data.models;

public class Vacancy {
    private String vacancyName;
    private int payment;
    private RequirementsList requirements;

    public Vacancy(String vacancyName, int payment, RequirementsList requirements) {
        this.vacancyName = vacancyName;
        this.payment = payment;
        this.requirements = requirements;
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
}
