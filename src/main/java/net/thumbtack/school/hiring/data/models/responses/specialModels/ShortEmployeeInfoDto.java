package net.thumbtack.school.hiring.data.models.responses.specialModels;

import net.thumbtack.school.hiring.data.models.stored.Employee;

public class ShortEmployeeInfoDto {
    private String login, firstName, lastName, email;

    private ShortEmployeeInfoDto(String login, String firstName, String lastName, String email) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static ShortEmployeeInfoDto fromEmployee(Employee employee) {
        return new ShortEmployeeInfoDto(employee.getLogin(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
    }
}
