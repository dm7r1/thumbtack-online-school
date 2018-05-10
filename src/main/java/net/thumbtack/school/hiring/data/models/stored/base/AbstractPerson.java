package net.thumbtack.school.hiring.data.models.stored.base;


import java.util.Objects;

abstract public class AbstractPerson {
    private String firstName, lastName, patronymic, login, email, password;

    protected AbstractPerson(String firstName, String lastName, String patronymic, String login, String email, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setPatronymic(patronymic);
        setLogin(login);
        setEmail(email);
        setPassword(password);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPerson that = (AbstractPerson) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(login, that.login) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, patronymic, login, email, password);
    }
}
