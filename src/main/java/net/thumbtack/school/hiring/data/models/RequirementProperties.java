package net.thumbtack.school.hiring.data.models;

public class RequirementProperties {
    private int lvl;
    private boolean required;

    public RequirementProperties(int lvl, boolean required) {
        this.lvl = lvl;
        this.required = required;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
