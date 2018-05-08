package net.thumbtack.school.hiring.data.models;

import java.util.ArrayList;
import java.util.List;

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
}
