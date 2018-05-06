package net.thumbtack.school.hiring.data.models;


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
}
