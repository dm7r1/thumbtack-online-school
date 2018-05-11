package net.thumbtack.school.hiring.data.dao;

import net.thumbtack.school.hiring.data.models.stored.SkillsList;

import java.util.Set;
import java.util.UUID;

public interface SkillsDao {
    void addEmployeeSkills(UUID employeeUUID, SkillsList skills);

    SkillsList getEmployeeSkills(UUID uuid);

    void deleteEmployeeSkills(UUID employeeUUID, Set<String> skillsNames);
}
