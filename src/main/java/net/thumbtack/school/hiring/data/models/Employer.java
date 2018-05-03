package net.thumbtack.school.hiring.data.models;

import java.util.ArrayList;
import java.util.List;

public class Employer extends AbstractPerson {
    private String companyName, address;
    private List<Vacation> vacations;

    public Employer(String firstName, String lastName, String patronymic, String login, String email, String password,
                    String companyName, String address) {
        super(firstName, lastName, patronymic, login, email, password);
        setCompanyName(companyName);
        setAddress(address);
        vacations = new ArrayList<>();
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Vacation> getVacations() {
        return vacations;
    }
}
