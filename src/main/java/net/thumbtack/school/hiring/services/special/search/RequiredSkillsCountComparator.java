package net.thumbtack.school.hiring.services.special.search;

import net.thumbtack.school.hiring.data.models.stored.SkillsList;
import net.thumbtack.school.hiring.data.models.stored.Vacancy;

import java.util.Comparator;

public class RequiredSkillsCountComparator implements Comparator<Vacancy> {
    private SkillsList skills;

    public RequiredSkillsCountComparator(SkillsList skills) {
        this.skills = skills;
    }

    @Override
    public int compare(Vacancy vacancy1, Vacancy vacancy2) {
        int skillsCount1 = 0;
        int skillsCount2 = 0;
        for(String skillName: skills.getSkillsNamesSet()) {
            int skillLevel = skills.getSkillLvl(skillName);
            if(vacancy1.requirementExists(skillName) && skillLevel >= vacancy1.getRequirementLvl(skillName))
                skillsCount1 ++;
            if(vacancy2.requirementExists(skillName) && skillLevel >= vacancy2.getRequirementLvl(skillName))
                skillsCount2 ++;
        }
        return Integer.compare(skillsCount2, skillsCount1);
    }
}
