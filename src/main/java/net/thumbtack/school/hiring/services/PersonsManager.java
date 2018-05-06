package net.thumbtack.school.hiring.services;

import net.thumbtack.school.hiring.data.DataBase;
import net.thumbtack.school.hiring.data.models.Employee;
import net.thumbtack.school.hiring.data.models.Employer;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.requests.*;
import net.thumbtack.school.hiring.data.models.requests.utils.checkers.ModelsExistenceCheckerImpl;
import net.thumbtack.school.hiring.data.models.requests.utils.validators.ModelsExistenceValidator;
import net.thumbtack.school.hiring.data.models.requests.utils.validators.PersonInfoRequestsValidator;
import net.thumbtack.school.hiring.data.models.responses.DtoResponse;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessEmptyDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;

import java.util.UUID;

public class PersonsManager {
    private DataBase dataBase;
    private ModelsExistenceValidator modelsExistenceValidator;
    private PersonInfoRequestsValidator personInfoRequestsValidator;

    public PersonsManager(DataBase dataBase) {
        this.dataBase = dataBase;
        this.modelsExistenceValidator = new ModelsExistenceValidator(new ModelsExistenceCheckerImpl(dataBase));
        this.personInfoRequestsValidator = new PersonInfoRequestsValidator();
    }

    public DtoResponse registerEmployee(RegisterEmployeeDtoRequest request) {
        try {
            personInfoRequestsValidator.validateEmployeeRegistrationInfo(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getPatronymic(),
                    request.getLogin(),
                    request.getEmail(),
                    request.getPassword()
            );
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        UUID token = dataBase.insertEmployee(new Employee(
                request.getFirstName(),
                request.getLastName(),
                request.getPatronymic(),
                request.getLogin(),
                request.getEmail(),
                request.getPassword()
        ));
        return SuccessfulRegisteredDtoResponse.makeNewInstance(token);
    }

    public DtoResponse registerEmployer(RegisterEmployerDtoRequest request) {
        try {
            personInfoRequestsValidator.validateEmployerRegistrationInfo(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getPatronymic(),
                    request.getLogin(),
                    request.getEmail(),
                    request.getPassword(),
                    request.getCompanyName(),
                    request.getAddress()
            );
        } catch (InvalidRequestException exception) {
           return ErrorDtoResponse.fromException(exception);
        }

        UUID token = dataBase.insertEmployer(new Employer(
                request.getFirstName(),
                request.getLastName(),
                request.getPatronymic(),
                request.getLogin(),
                request.getEmail(),
                request.getPassword(),
                request.getCompanyName(),
                request.getAddress()
        ));
        return SuccessfulRegisteredDtoResponse.makeNewInstance(token);
    }

    public DtoResponse changeEmployerInfo(ChangeEmployerInfoDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getUuid());
            personInfoRequestsValidator.validateEmployerChangingInfo(
                    request.getNewFirstName(),
                    request.getNewLastName(),
                    request.getNewPatronymic(),
                    request.getNewEmail(),
                    request.getNewPassword(),
                    request.getNewCompanyName(),
                    request.getNewAddress()
            );
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }
        Employer employer = dataBase.getEmployerByUUID(request.getUuid());

        if(request.getNewFirstName() != null)
            employer.setFirstName(request.getNewFirstName());
        if(request.getNewLastName() != null)
            employer.setLastName(request.getNewLastName());
        if(request.getNewPatronymic() != null)
            employer.setPatronymic(request.getNewPatronymic());
        if(request.getNewEmail() != null)
            employer.setEmail(request.getNewEmail());
        if(request.getNewPassword() != null)
            employer.setPassword(request.getNewPassword());
        if(request.getNewCompanyName() != null)
            employer.setCompanyName(request.getNewCompanyName());
        if(request.getNewAddress() != null)
            employer.setAddress(request.getNewAddress());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse changeEmployeeInfo(ChangeEmployeeInfoDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployeeUUID(request.getUuid());
            new PersonInfoRequestsValidator().validateEmployeeChangingInfo(
                    request.getNewFirstName(),
                    request.getNewLastName(),
                    request.getNewPatronymic(),
                    request.getNewEmail(),
                    request.getNewPassword()
            );
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }
        Employee employee = dataBase.getEmployeeByUUID(request.getUuid());

        if(request.getNewFirstName() != null)
            employee.setFirstName(request.getNewFirstName());
        if(request.getNewLastName() != null)
            employee.setLastName(request.getNewLastName());
        if(request.getNewPatronymic() != null)
            employee.setPatronymic(request.getNewPatronymic());
        if(request.getNewEmail() != null)
            employee.setEmail(request.getNewEmail());
        if(request.getNewPassword() != null)
            employee.setPassword(request.getNewPassword());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse deleteEmployee(DeleteEmployeeDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployeeUUID(request.getUuid());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }
        dataBase.deleteEmployeeByUUID(request.getUuid());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse deleteEmployer(DeleteEmployerDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getUuid());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }
        dataBase.deleteEmployerByUUID(request.getUuid());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }
}