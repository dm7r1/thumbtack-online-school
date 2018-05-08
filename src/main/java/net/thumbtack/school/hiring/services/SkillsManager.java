package net.thumbtack.school.hiring.services;

import net.thumbtack.school.hiring.data.DataBase;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.SkillsList;
import net.thumbtack.school.hiring.data.models.requests.ChangeSkillsDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.GetSkillsDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.utils.checkers.ModelsExistenceCheckerImpl;
import net.thumbtack.school.hiring.data.models.requests.utils.validators.ChangeSkillsRequestsValidator;
import net.thumbtack.school.hiring.data.models.requests.utils.validators.ModelsExistenceValidator;
import net.thumbtack.school.hiring.data.models.responses.DtoResponse;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SkillsListDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessEmptyDtoResponse;

public class SkillsManager {
    private DataBase dataBase;
    private ModelsExistenceValidator modelsExistenceValidator;
    private ChangeSkillsRequestsValidator changeSkillsRequestsValidator;

    public SkillsManager(DataBase dataBase) {
        this.dataBase = dataBase;
        this.modelsExistenceValidator = new ModelsExistenceValidator(new ModelsExistenceCheckerImpl(dataBase));
        this.changeSkillsRequestsValidator = new ChangeSkillsRequestsValidator();
    }

    public DtoResponse changeSkills(ChangeSkillsDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployeeUUID(request.getUuid());
            changeSkillsRequestsValidator.validateNewSkillsInfo(request.getNewOrChangedSkills());
            modelsExistenceValidator.validateSkillsForDeleting(request.getUuid(), request.getSkillsForDeletingNames());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        if(request.getSkillsForDeletingNames() != null)
            dataBase.deleteEmployeeSkills(request.getUuid(), request.getSkillsForDeletingNames());

        if(request.getNewOrChangedSkills() != null)
            dataBase.addEmployeeSkills(request.getUuid(), request.getNewOrChangedSkills());

        return SuccessEmptyDtoResponse.makeNewInstance();
    }

    public DtoResponse getSkills(GetSkillsDtoRequest request) {
        try {
            modelsExistenceValidator.validateEmployeeUUID(request.getUuid());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        SkillsList employeeSkills = dataBase.getEmployeeSkills(request.getUuid());

        return SkillsListDtoResponse.makeInstance(employeeSkills);
    }
}
