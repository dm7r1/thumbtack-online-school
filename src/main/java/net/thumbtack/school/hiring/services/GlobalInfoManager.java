package net.thumbtack.school.hiring.services;

import net.thumbtack.school.hiring.data.DataBase;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.requests.GetDefinedSkillsDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.utils.checkers.ModelsExistenceCheckerImpl;
import net.thumbtack.school.hiring.data.models.requests.utils.validators.ModelsExistenceValidator;
import net.thumbtack.school.hiring.data.models.responses.DefinedSkillsDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.DtoResponse;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;

public class GlobalInfoManager {
    DataBase dataBase;
    ModelsExistenceValidator modelsExistenceValidator;

    public GlobalInfoManager(DataBase dataBase) {
        this.dataBase = dataBase;
        modelsExistenceValidator = new ModelsExistenceValidator(new ModelsExistenceCheckerImpl(dataBase));
    }

    public DtoResponse getDefinedSkills(GetDefinedSkillsDtoRequest request) {
        try {
            modelsExistenceValidator.validateAnyPersonUUID(request.getUuid());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        return DefinedSkillsDtoResponse.makeInstance(dataBase.getDefinedSkillsNames());
    }
}
