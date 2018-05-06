package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.SkillsList;
import net.thumbtack.school.hiring.data.models.requests.base.AbstractExistingPersonDirectedDtoRequest;

import java.util.List;
import java.util.Map;

public class ChangeSkillsDtoRequest extends AbstractExistingPersonDirectedDtoRequest {
    private List<String> skillsForDeletingNames;
    private Map<String, Integer> newOrChangedSkills;

    public List<String> getSkillsForDeletingNames() {
        return skillsForDeletingNames;
    }

    public void setSkillsForDeletingNames(List<String> skillsForDeletingNames) {
        this.skillsForDeletingNames = skillsForDeletingNames;
    }

    public SkillsList getNewOrChangedSkills() {
        if(newOrChangedSkills == null)
            return null;
        else
            return SkillsList.fromMap(newOrChangedSkills);
    }

    public void setNewOrChangedSkills(SkillsList newOrChangedSkills) {
        if(newOrChangedSkills == null)
            this.newOrChangedSkills = null;
        else
            this.newOrChangedSkills = newOrChangedSkills.toMap();
    }
}
