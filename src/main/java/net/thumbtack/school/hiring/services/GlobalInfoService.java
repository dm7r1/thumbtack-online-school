package net.thumbtack.school.hiring.services;

import net.thumbtack.school.hiring.data.DataStorage;
import net.thumbtack.school.hiring.data.dao.GlobalInfoDao;
import net.thumbtack.school.hiring.data.daoimpl.GlobalInfoDaoImpl;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.requests.GetDefinedSkillsDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.checkers.ModelsExistenceCheckerImpl;
import net.thumbtack.school.hiring.data.models.requests.validators.ModelsExistenceValidator;
import net.thumbtack.school.hiring.data.models.responses.DefinedSkillsDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.DtoResponse;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;

public class GlobalInfoService {
    GlobalInfoDao globalInfoDao;
    ModelsExistenceValidator modelsExistenceValidator;

    public GlobalInfoService(DataStorage dataStorage) {
        globalInfoDao = new GlobalInfoDaoImpl(dataStorage);
        modelsExistenceValidator = new ModelsExistenceValidator(new ModelsExistenceCheckerImpl(dataStorage));
    }

    public DtoResponse getDefinedSkills(GetDefinedSkillsDtoRequest request) {
        try {
            modelsExistenceValidator.validateAnyPersonUUID(request.getToken());
        } catch (InvalidRequestException exception) {
            return ErrorDtoResponse.fromException(exception);
        }

        return DefinedSkillsDtoResponse.makeInstance(globalInfoDao.getDefinedSkillsNames());
    }
}
