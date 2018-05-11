package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.stored.Employee;
import net.thumbtack.school.hiring.data.models.stored.Employer;
import net.thumbtack.school.hiring.data.models.stored.RequirementsList;
import net.thumbtack.school.hiring.data.models.stored.Vacancy;

public class SpecialModelsFactory {
    private static int uniqueLoginModifier = 0;

    public static Employee getValidEmployee() {
        ++uniqueLoginModifier;
        return new Employee(
                "firstName","lastName", "patronymic","login(modelsFactory)" + uniqueLoginModifier,
                "email@example.com", "password");
    }

    public static Employer getValidEmployer() {
        ++uniqueLoginModifier;
        return new Employer(
                "firstName", "lastName", "patronymic", "login(modelsFactory)" + uniqueLoginModifier,
                "email@example.com", "password", "companyName", "address"
        );
    }

    public static Vacancy getValidVacancy() {
        return new Vacancy("vacancyName", 1, new RequirementsList());
    }
}
