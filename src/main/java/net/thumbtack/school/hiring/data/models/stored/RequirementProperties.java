package net.thumbtack.school.hiring.data.models.stored;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequirementProperties that = (RequirementProperties) o;
        return lvl == that.lvl &&
                necessary == that.necessary;
    }

    @Override
    public int hashCode() {

        return Objects.hash(lvl, necessary);
    }
}
