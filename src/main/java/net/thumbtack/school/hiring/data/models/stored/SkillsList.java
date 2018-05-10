package net.thumbtack.school.hiring.data.models.stored;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class SkillsList {
    private Map<String, Integer> skills;

    private SkillsList(Map<String, Integer> skills) {
        this.skills = skills;
    }

    public SkillsList() {
        this(new HashMap<>());
    }

    public void addSkill(String skillName, Integer lvl) {
        skills.put(skillName, lvl);
    }

    public Integer getSkillLvl(String skillName) {
        return skills.get(skillName);
    }

    public Set<String> getSkillsNamesSet() {
        return skills.keySet();
    }

    public int size() {
        return skills.size();
    }

    public void addSkills(SkillsList skillsForAdding) {
        for(String newRequirementName: skillsForAdding.getSkillsNamesSet()) {
            addSkill(newRequirementName, skillsForAdding.getSkillLvl(newRequirementName));
        }
    }

    public void removeSkill(String skillName) {
        this.skills.remove(skillName);
    }

    public void removeSkills(Set<String> skillsNames) {
        for(String skillName: skillsNames)
            this.skills.remove(skillName);
    }

    public boolean skillExists(String skillName) {
        return skills.containsKey(skillName);
    }

    public Map<String, Integer> toMap() {
        return skills;
    }

    public static SkillsList fromMap(Map<String, Integer> skills) {
        return new SkillsList(skills);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillsList that = (SkillsList) o;
        return Objects.equals(skills, that.skills);
    }

    @Override
    public int hashCode() {

        return Objects.hash(skills);
    }
}
