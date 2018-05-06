package net.thumbtack.school.hiring.data.models.requests.utils.validators;

import net.thumbtack.school.hiring.data.exceptions.InvalidRequestErrors;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import net.thumbtack.school.hiring.data.models.SkillsList;
import net.thumbtack.school.hiring.data.models.requests.utils.checkers.SkillInfoChecker;

public class ChangeSkillsRequestsValidator {
    public void validateNewSkillsInfo(SkillsList skills) throws InvalidRequestException {
        if(skills != null)
            for(String skillName: skills.getSkillsNamesSet()) {
                if(skillName == null || !SkillInfoChecker.isValidSkillName(skillName))
                    throw new InvalidRequestException(InvalidRequestErrors.INVALID_SKILLS);
                Integer lvl = skills.getSkillLvl(skillName);
                if(lvl == null || !SkillInfoChecker.isValidSkillLvl(lvl))
                    throw new InvalidRequestException(InvalidRequestErrors.INVALID_SKILLS);
            }
    }
}
