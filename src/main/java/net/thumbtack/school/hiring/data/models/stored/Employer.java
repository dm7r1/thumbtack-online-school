package net.thumbtack.school.hiring.data.models.stored;

import net.thumbtack.school.hiring.data.models.stored.base.AbstractPerson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employer extends AbstractPerson {
    private String companyName, address;
    private List<Vacancy> vacancies;

    public Employer(String firstName, String lastName, String patronymic, String login, String email, String password,
                    String companyName, String address) {
        super(firstName, lastName, patronymic, login, email, password);
        setCompanyName(companyName);
        setAddress(address);
        vacancies = new ArrayList<>();
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void addVacancy(Vacancy vacancy) {
        vacancies.add(vacancy);
    }

    public void removeVacancy(int vacancyNumber) {
        vacancies.remove(vacancyNumber);
    }

    public Vacancy getVacancy(int vacancyNumber) {
        return vacancies.get(vacancyNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employer employer = (Employer) o;
        return Objects.equals(companyName, employer.companyName) &&
                Objects.equals(address, employer.address) &&
                Objects.equals(vacancies, employer.vacancies);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), companyName, address, vacancies);
    }
}
