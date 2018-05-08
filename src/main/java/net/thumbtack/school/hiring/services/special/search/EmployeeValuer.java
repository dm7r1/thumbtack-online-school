package net.thumbtack.school.hiring.services.special.search;

import net.thumbtack.school.hiring.data.models.RequirementsList;
import net.thumbtack.school.hiring.data.models.SkillsList;

public interface EmployeeValuer {
    boolean isEmployeeRight(SkillsList employeeSkills, RequirementsList vacancyRequirements);
}
