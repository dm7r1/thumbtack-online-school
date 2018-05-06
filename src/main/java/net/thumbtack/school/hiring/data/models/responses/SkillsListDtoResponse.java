package net.thumbtack.school.hiring.data.models.responses;

import net.thumbtack.school.hiring.data.models.SkillsList;

import java.util.Map;

public class SkillsListDtoResponse implements DtoResponse {
    private Map<String, Integer> skills;

    public static SkillsListDtoResponse makeInstance(SkillsList skills) {
        SkillsListDtoResponse response = new SkillsListDtoResponse();
        response.skills = skills.toMap();

        return response;
    }

    public SkillsList getSkills() {
        return SkillsList.fromMap(skills);
    }
}
