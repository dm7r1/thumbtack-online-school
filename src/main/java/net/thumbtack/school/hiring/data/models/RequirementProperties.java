package net.thumbtack.school.hiring.data.models;

public class RequirementProperties {
    private int lvl;
    private boolean necessary;

    public RequirementProperties(int lvl, boolean necessary) {
        this.lvl = lvl;
        this.necessary = necessary;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public boolean isNecessary() {
        return necessary;
    }

    public void setNecessary(boolean necessary) {
        this.necessary = necessary;
    }
}
