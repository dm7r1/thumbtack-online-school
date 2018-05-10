package net.thumbtack.school.hiring.data.models.stored;


import net.thumbtack.school.hiring.data.models.stored.base.AbstractPerson;

import java.util.Objects;
import java.util.Set;

public class Employee extends AbstractPerson {
    private SkillsList skills;
    private boolean active;

    public Employee(String firstName, String lastName, String patronymic, String login, String email, String password) {
        super(firstName, lastName, patronymic, login, email, password);
        skills = new SkillsList();
        active = true;
    }

    public SkillsList getSkills() {
        return skills;
    }

    public boolean skillExists(String skillName) {
        return skills.skillExists(skillName);
    }

    public int getSkillLvl(String skillName) {
        return skills.getSkillLvl(skillName);
    }

    public void setSkills(SkillsList skills) {
        this.skills = skills;
    }

    public void addSkills(SkillsList skills) {
        this.skills.addSkills(skills);
    }

    public void removeSkills(Set<String> skillsNames) {
        this.skills.removeSkills(skillsNames);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return active == employee.active &&
                Objects.equals(skills, employee.skills);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), skills, active);
    }
}
