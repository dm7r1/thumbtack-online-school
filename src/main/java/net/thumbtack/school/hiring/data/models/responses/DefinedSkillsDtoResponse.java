package net.thumbtack.school.hiring.data.models.responses;

import java.util.Set;

public class DefinedSkillsDtoResponse implements DtoResponse {
    private Set<String> definedSkills;

    public static DefinedSkillsDtoResponse makeInstance(Set<String> definedSkills) {
        DefinedSkillsDtoResponse response = new DefinedSkillsDtoResponse();
        response.definedSkills = definedSkills;

        return response;
    }

    public Set<String> getDefinedSkills() {
        return definedSkills;
    }
}
