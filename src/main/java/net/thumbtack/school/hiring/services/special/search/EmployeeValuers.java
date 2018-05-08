package net.thumbtack.school.hiring.services.special.search;

import net.thumbtack.school.hiring.data.models.RequirementsList;
import net.thumbtack.school.hiring.data.models.SkillsList;

public class EmployeeValuers {
    public static final EmployeeValuer valuerAllSkillsWithEnoughLevel = (SkillsList employeeSkills, RequirementsList vacancyRequirements) -> {
        for(String requirementName: vacancyRequirements.getRequirementsNamesSet())
            if (!employeeSkills.skillExists(requirementName) || employeeSkills.getSkillLvl(requirementName) < vacancyRequirements.getRequirementLvl(requirementName))
                return false;
        return true;
    };

    public static final EmployeeValuer valuerNecessarySkillsWithEnoughLevel = (SkillsList employeeSkills, RequirementsList vacancyRequirements) -> {
        for(String requirementName: vacancyRequirements.getRequirementsNamesSet()) {
            if(vacancyRequirements.isRequirementNecessary(requirementName))
                if(!employeeSkills.skillExists(requirementName) || employeeSkills.getSkillLvl(requirementName) < vacancyRequirements.getRequirementLvl(requirementName))
                    return false;
            }
            return true;
    };

    public static final EmployeeValuer valuerAllSkillsWithAnyLevel = (SkillsList employeeSkills, RequirementsList vacancyRequirements) -> {
        for(String requirementName: vacancyRequirements.getRequirementsNamesSet())
            if(!employeeSkills.skillExists(requirementName))
                return false;
        return true;
    };

    public static final EmployeeValuer valuerOneSkillWithEnoughLevel = (SkillsList employeeSkills, RequirementsList vacancyRequirements) -> {
        for(String requirementName: vacancyRequirements.getRequirementsNamesSet())
            if(vacancyRequirements.isRequirementNecessary(requirementName) && employeeSkills.skillExists(requirementName)
                    && employeeSkills.getSkillLvl(requirementName) >= vacancyRequirements.getRequirementLvl(requirementName))
                return true;
        return false;
    };
}
