package net.thumbtack.school.hiring.data.models;


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

    public void setSkills(SkillsList skills) {
        this.skills = skills;
    }

    public void addSkills(SkillsList skills) {
        this.skills.addSkills(skills);
    }

    public void removeSkills(Set<String> skillsNames) {
        this.skills.removeSkills(skillsNames);
    }
}
