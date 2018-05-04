package net.thumbtack.school.hiring.data.models;

import java.util.Map;

public class Vacancy {
    private String vacancyName;
    private int payment;
    private Map<String, Requirement> requirements;

    public Vacancy(String vacancyName, int payment, Map<String, Requirement> requirements) {
        this.vacancyName = vacancyName;
        this.payment = payment;
        this.requirements = requirements;
    }
}
