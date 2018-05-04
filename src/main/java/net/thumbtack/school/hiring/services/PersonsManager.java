package net.thumbtack.school.hiring.services;

import net.thumbtack.school.hiring.data.DataBase;
import net.thumbtack.school.hiring.data.models.Employee;
import net.thumbtack.school.hiring.data.models.Employer;
import net.thumbtack.school.hiring.data.models.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.requests.*;
import net.thumbtack.school.hiring.data.models.requests.utils.PersonExistenceCheckerImpl;
import net.thumbtack.school.hiring.data.models.requests.utils.PersonExistenceValidator;
import net.thumbtack.school.hiring.data.models.requests.utils.PersonInfoRequestsValidator;
import net.thumbtack.school.hiring.data.models.responses.DtoResponse;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessEmptyDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;

import java.util.UUID;

public class PersonsManager {
    private DataBase dataBase;

    public PersonsManager(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public DtoResponse registerEmployee(RegisterEmployeeDtoRequest request) {
        try {
            new PersonInfoRequestsValidator().validateEmployeeRegistrationInfo(
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
            new PersonInfoRequestsValidator().validateEmployerRegistrationInfo(
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
            new PersonExistenceValidator(new PersonExistenceCheckerImpl(dataBase)).validateEmployerUUID(request.getUuid());
            new PersonInfoRequestsValidator().validateEmployerChangingInfo(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getPatronymic(),
                    request.getEmail(),
                    request.getPassword(),
                    request.getCompanyName(),
                    request.getAddress()
            );
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }
        Employer employer = dataBase.getEmployerByUUID(request.getUuid());

        if(request.getFirstName() != null)
            employer.setFirstName(request.getFirstName());
        if(request.getLastName() != null)
            employer.setLastName(request.getLastName());
        if(request.getPatronymic() != null)
            employer.setPatronymic(request.getPatronymic());
        if(request.getEmail() != null)
            employer.setEmail(request.getEmail());
        if(request.getPassword() != null)
            employer.setPassword(request.getPassword());
        if(request.getCompanyName() != null)
            employer.setCompanyName(request.getCompanyName());
        if(request.getAddress() != null)
            employer.setAddress(request.getAddress());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse changeEmployeeInfo(ChangeEmployeeInfoDtoRequest request) {
        try {
            new PersonExistenceValidator(new PersonExistenceCheckerImpl(dataBase)).validateEmployeeUUID(request.getUuid());
            new PersonInfoRequestsValidator().validateEmployeeChangingInfo(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getPatronymic(),
                    request.getEmail(),
                    request.getPassword()
            );
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }
        Employee employee = dataBase.getEmployeeByUUID(request.getUuid());

        if(request.getFirstName() != null)
            employee.setFirstName(request.getFirstName());
        if(request.getLastName() != null)
            employee.setLastName(request.getLastName());
        if(request.getPatronymic() != null)
            employee.setPatronymic(request.getPatronymic());
        if(request.getEmail() != null)
            employee.setEmail(request.getEmail());
        if(request.getPassword() != null)
            employee.setPassword(request.getPassword());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse deleteEmployee(DeleteEmployeeDtoRequest request) {
        try {
            new PersonExistenceValidator(new PersonExistenceCheckerImpl(dataBase)).validateEmployeeUUID(request.getUuid());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }
        dataBase.deleteEmployeeByUUID(request.getUuid());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse deleteEmployer(DeleteEmployerDtoRequest request) {
        try {
            new PersonExistenceValidator(new PersonExistenceCheckerImpl(dataBase)).validateEmployerUUID(request.getUuid());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }
        dataBase.deleteEmployerByUUID(request.getUuid());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }
}
