package net.thumbtack.school.hiring.services;

import net.thumbtack.school.hiring.data.DataStorage;
import net.thumbtack.school.hiring.data.dao.SkillsDao;
import net.thumbtack.school.hiring.data.daoimpl.SkillsDaoImpl;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.stored.SkillsList;
import net.thumbtack.school.hiring.data.models.requests.ChangeSkillsDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.GetSkillsDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.checkers.ModelsExistenceCheckerImpl;
import net.thumbtack.school.hiring.data.models.requests.validators.ChangeSkillsRequestsValidator;
import net.thumbtack.school.hiring.data.models.requests.validators.ModelsExistenceValidator;
import net.thumbtack.school.hiring.data.models.responses.DtoResponse;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SkillsListDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessEmptyDtoResponse;

public class SkillsService {
    private SkillsDao skillsDao;

    private ModelsExistenceValidator modelsExistenceValidator;
    private ChangeSkillsRequestsValidator changeSkillsRequestsValidator;

    public SkillsService(DataStorage dataStorage) {
        skillsDao = new SkillsDaoImpl(dataStorage);

        this.modelsExistenceValidator = new ModelsExistenceValidator(new ModelsExistenceCheckerImpl(dataStorage));
        this.changeSkillsRequestsValidator = new ChangeSkillsRequestsValidator();
    }

    public DtoResponse changeSkills(ChangeSkillsDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployeeUUID(request.getToken());
            changeSkillsRequestsValidator.validateNewSkillsInfo(request.getNewOrChangedSkills());
            modelsExistenceValidator.validateSkillsForDeleting(request.getToken(), request.getSkillsForDeletingNames());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        if(request.getSkillsForDeletingNames() != null)
            skillsDao.deleteEmployeeSkills(request.getToken(), request.getSkillsForDeletingNames());

        if(request.getNewOrChangedSkills() != null)
            skillsDao.addEmployeeSkills(request.getToken(), request.getNewOrChangedSkills());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse getSkills(GetSkillsDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployeeUUID(request.getToken());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        SkillsList employeeSkills = skillsDao.getEmployeeSkills(request.getToken());

        return SkillsListDtoResponse.makeInstance(employeeSkills);
    }
}
