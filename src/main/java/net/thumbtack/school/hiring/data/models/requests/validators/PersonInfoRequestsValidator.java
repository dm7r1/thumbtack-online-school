package net.thumbtack.school.hiring.data.models.requests.validators;

import net.thumbtack.school.hiring.data.exceptions.InvalidRequestErrors;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import org.apache.commons.validator.routines.EmailValidator;


public class PersonInfoRequestsValidator {
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MIN_LOGIN_LENGTH = 4;

    private void validatePersonRegistrationInfo(String firstName, String lastName, String patronymic, String login, String email, String password) throws InvalidRequestException {
        if(firstName == null || !isValidFirstName(firstName))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_FIRST_NAME);
        if(lastName == null || !isValidLastName(lastName))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_LAST_NAME);
        if(patronymic == null || !isValidPatronymic(patronymic))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_PATRONYMIC);
        if(email == null || !isValidEmail(email))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_EMAIL);
        if(login == null || !isValidLogin(login))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_LOGIN);
        if(password == null || !isValidPassword(password))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_PASSWORD);
    }

    public void validateEmployeeRegistrationInfo(String firstName, String lastName, String patronymic, String login, String email, String password) throws InvalidRequestException {
        validatePersonRegistrationInfo(firstName, lastName, patronymic, login, email, password);
    }

    public void validateEmployerRegistrationInfo(String firstName, String lastName, String patronymic, String login, String email, String password, String companyName, String address) throws InvalidRequestException {
       validatePersonRegistrationInfo(firstName, lastName, patronymic, login, email, password);
        if(companyName == null || !isValidCompanyName(companyName))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_COMPANY_NAME);
        if(address == null || !isValidAddress(address))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_ADDRESS);
    }

    private void validatePersonChangingInfo(String firstName, String lastName, String patronymic, String email, String password) throws InvalidRequestException {
        if(firstName != null)
            if(!isValidFirstName(firstName))
                throw new InvalidRequestException(InvalidRequestErrors.INVALID_FIRST_NAME);
        if(lastName != null)
            if(!isValidLastName(lastName))
                throw new InvalidRequestException(InvalidRequestErrors.INVALID_LAST_NAME);
        if(patronymic != null)
            if(!isValidPatronymic(patronymic))
                throw new InvalidRequestException(InvalidRequestErrors.INVALID_PATRONYMIC);
        if(email != null)
            if(!isValidEmail(email))
                throw new InvalidRequestException(InvalidRequestErrors.INVALID_EMAIL);
        if(password != null)
            if(!isValidPassword(password))
                throw new InvalidRequestException(InvalidRequestErrors.INVALID_PASSWORD);
    }

    public void validateEmployeeChangingInfo(String firstName, String lastName, String patronymic, String email, String password) throws InvalidRequestException {
        validatePersonChangingInfo(firstName, lastName, patronymic, email, password);
    }

    public void validateEmployerChangingInfo(String firstName, String lastName, String patronymic, String email, String password, String companyName, String address) throws InvalidRequestException {
       validatePersonChangingInfo(firstName, lastName, patronymic, email, password);
       if(companyName != null)
            if(!isValidCompanyName(companyName))
                throw new InvalidRequestException(InvalidRequestErrors.INVALID_COMPANY_NAME);
       if(address != null)
            if(!isValidAddress(address))
                throw new InvalidRequestException(InvalidRequestErrors.INVALID_ADDRESS);
    }

    public void validateSetEmployeeActiveInfo(Boolean active) throws InvalidRequestException {
        if(active == null)
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_ACTIVE);
    }

    private boolean isValidFirstName(String firstName) {
        return firstName.length() != 0;
    }

    private boolean isValidLastName(String lastName) {
        return lastName.length() != 0;
    }

    private boolean isValidPatronymic(String patronymic) {
        return true;
    }

    private boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    private boolean isValidLogin(String login) {
        return login.length() >= MIN_LOGIN_LENGTH;
    }

    private boolean isValidPassword(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH;
    }

    private boolean isValidCompanyName(String companyName) {
        return companyName.length() != 0;
    }

    private boolean isValidAddress(String address) {
        return address.length() != 0;
    }
}
