package net.thumbtack.school.hiring.services.special.search;

import net.thumbtack.school.hiring.data.models.stored.RequirementsList;
import net.thumbtack.school.hiring.data.models.stored.SkillsList;

public interface EmployeeValuer {
    boolean isEmployeeRight(SkillsList employeeSkills, RequirementsList vacancyRequirements);
}
