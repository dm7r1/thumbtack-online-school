package net.thumbtack.school.hiring.data.models.requests.base;

public class AbstractChangePersonInfoDtoRequest extends AbstractExistingPersonDirectedDtoRequest {
    private String newFirstName, newLastName, newPatronymic, newEmail, newPassword;

    public String getNewFirstName() {
        return newFirstName;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public String getNewPatronymic() {
        return newPatronymic;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    public void setNewLastName(String newLastName) {
        this.newLastName = newLastName;
    }

    public void setNewPatronymic(String newPatronymic) {
        this.newPatronymic = newPatronymic;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
