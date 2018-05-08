package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.SkillsList;
import net.thumbtack.school.hiring.data.models.requests.base.AbstractExistingPersonDirectedDtoRequest;

import java.util.Map;
import java.util.Set;

public class ChangeSkillsDtoRequest extends AbstractExistingPersonDirectedDtoRequest {
    private Set<String> skillsForDeletingNames;
    private Map<String, Integer> newOrChangedSkills;

    public Set<String> getSkillsForDeletingNames() {
        return skillsForDeletingNames;
    }

    public void setSkillsForDeletingNames(Set<String> skillsForDeletingNames) {
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
