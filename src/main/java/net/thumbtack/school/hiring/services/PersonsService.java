package net.thumbtack.school.hiring.services;

import net.thumbtack.school.hiring.data.DataStorage;
import net.thumbtack.school.hiring.data.dao.EmployeeDao;
import net.thumbtack.school.hiring.data.dao.EmployerDao;
import net.thumbtack.school.hiring.data.daoimpl.EmployeeDaoImpl;
import net.thumbtack.school.hiring.data.daoimpl.EmployerDaoImpl;
import net.thumbtack.school.hiring.data.models.stored.Employee;
import net.thumbtack.school.hiring.data.models.stored.Employer;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.requests.*;
import net.thumbtack.school.hiring.data.models.requests.checkers.ModelsExistenceCheckerImpl;
import net.thumbtack.school.hiring.data.models.requests.validators.ModelsExistenceValidator;
import net.thumbtack.school.hiring.data.models.requests.validators.PersonInfoRequestsValidator;
import net.thumbtack.school.hiring.data.models.responses.DtoResponse;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessEmptyDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;

import java.util.UUID;

public class PersonsService {
    private EmployeeDao employeeDao;
    private EmployerDao employerDao;

    private ModelsExistenceValidator modelsExistenceValidator;
    private PersonInfoRequestsValidator personInfoRequestsValidator;

    public PersonsService(DataStorage dataStorage) {
        employeeDao = new EmployeeDaoImpl(dataStorage);
        employerDao = new EmployerDaoImpl(dataStorage);
        this.modelsExistenceValidator = new ModelsExistenceValidator(new ModelsExistenceCheckerImpl(dataStorage));
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
            modelsExistenceValidator.validateLoginUniqueness(request.getLogin());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        UUID token = employeeDao.insertEmployee(new Employee(
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
            modelsExistenceValidator.validateLoginUniqueness(request.getLogin());
        } catch (InvalidRequestException exception) {
           return ErrorDtoResponse.fromException(exception);
        }

        UUID token = employerDao.insertEmployer(new Employer(
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
            modelsExistenceValidator.validateEmployerUUID(request.getToken());
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
        Employer employer = employerDao.getEmployerByUUID(request.getToken());

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
            modelsExistenceValidator.validateEmployeeUUID(request.getToken());
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
        Employee employee = employeeDao.getEmployeeByUUID(request.getToken());

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
            modelsExistenceValidator.validateEmployeeUUID(request.getToken());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }
        employeeDao.deleteEmployeeByUUID(request.getToken());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse deleteEmployer(DeleteEmployerDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployerUUID(request.getToken());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }
        employerDao.deleteEmployerByUUID(request.getToken());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse setEmployeeActive(SetEmployeeActiveDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployeeUUID(request.getToken());
            personInfoRequestsValidator.validateSetEmployeeActiveInfo(request.getActive());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        employeeDao.setEmployeeActive(request.getToken(), request.getActive());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }
}
