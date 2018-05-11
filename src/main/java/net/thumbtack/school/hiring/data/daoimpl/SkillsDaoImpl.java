package net.thumbtack.school.hiring.data.daoimpl;

import net.thumbtack.school.hiring.data.DataStorage;
import net.thumbtack.school.hiring.data.dao.SkillsDao;
import net.thumbtack.school.hiring.data.models.stored.SkillsList;

import java.util.Set;
import java.util.UUID;


public class SkillsDaoImpl implements SkillsDao {
    private DataStorage dataStorage;

    public SkillsDaoImpl(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public void addEmployeeSkills(UUID employeeUUID, SkillsList skills) {
        dataStorage.getEmployees().get(employeeUUID).addSkills(skills);
        dataStorage.getDefinedSkills().addAll(skills.getSkillsNamesSet());
    }

    public SkillsList getEmployeeSkills(UUID uuid) {
        return dataStorage.getEmployees().get(uuid).getSkills();
    }

    public void deleteEmployeeSkills(UUID employeeUUID, Set<String> skillsNames) {
        dataStorage.getEmployees().get(employeeUUID).removeSkills(skillsNames);
    }
}
