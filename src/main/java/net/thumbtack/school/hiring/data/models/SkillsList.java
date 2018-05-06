package net.thumbtack.school.hiring.data.models;

import java.util.HashMap;
import java.util.Map;
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

    public void deleteSkill(String skillName) {
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
}