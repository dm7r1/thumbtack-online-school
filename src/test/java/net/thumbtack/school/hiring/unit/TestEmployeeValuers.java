package net.thumbtack.school.hiring.unit;

import net.thumbtack.school.hiring.data.models.stored.RequirementProperties;
import net.thumbtack.school.hiring.data.models.stored.RequirementsList;
import net.thumbtack.school.hiring.data.models.stored.SkillsList;
import net.thumbtack.school.hiring.services.special.search.EmployeeValuer;
import net.thumbtack.school.hiring.services.special.search.EmployeeValuers;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestEmployeeValuers {

    @Test
    public void testValuerAllSkillsWithEnoughLevel_AllSkillsWithEnoughLevelTrue() {
        assertTrue(EmployeeValuers.valuerAllSkillsWithEnoughLevel.isEmployeeRight(
                getSkills_a2b2c2(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerAllSkillsWithEnoughLevel_AllSkillsExistNotAllSkillsHaveEnoughLevel() {
        assertFalse(EmployeeValuers.valuerAllSkillsWithEnoughLevel.isEmployeeRight(
                getSkills_a2b1c2(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerAllSkillsWithEnoughLevel_NotAllSkillsExistAllSkillsHaveEnoughLevel() {
        assertFalse(EmployeeValuers.valuerAllSkillsWithEnoughLevel.isEmployeeRight(
                getSkills_a2(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerAllSkillsWithEnoughLevel_EmptySkillsList() {
        assertFalse(EmployeeValuers.valuerAllSkillsWithEnoughLevel.isEmployeeRight(
                getSkills_empty(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerNecessarySkillsWithEnoughLevel_AllSkillsWithEnoughLevel() {
        assertTrue(EmployeeValuers.valuerNecessarySkillsWithEnoughLevel.isEmployeeRight(
                getSkills_a2b2c2(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerNecessarySkillsWithEnoughLevel_AllSkillsExistNecessaryHaveEnoughLevelNotNecessarySkillsHaveNotEnoughLevel() {
        assertTrue(EmployeeValuers.valuerNecessarySkillsWithEnoughLevel.isEmployeeRight(
                getSkills_a1b2c2(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerNecessarySkillsWithEnoughLevel_NotAllSkillsExistsNecessarySkillsExistsAndHaveEnoughLevel() {
        assertTrue(EmployeeValuers.valuerNecessarySkillsWithEnoughLevel.isEmployeeRight(
                getSkills_b2c2(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerNecessarySkillsWithEnoughLevel_AllSkillsNecessarySkillsHaveNotEnoughLevel() {
        assertFalse(EmployeeValuers.valuerNecessarySkillsWithEnoughLevel.isEmployeeRight(
                getSkills_a2b1c2(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerNecessarySkillsWithEnoughLevel_NecessarySkillsNotExist() {
        assertFalse(EmployeeValuers.valuerNecessarySkillsWithEnoughLevel.isEmployeeRight(
                getSkills_a2(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerNecessarySkillsWithEnoughLevel_emptySkillsList() {
        assertFalse(EmployeeValuers.valuerNecessarySkillsWithEnoughLevel.isEmployeeRight(
                getSkills_empty(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerAllSkillsWithAnyLevel_AllSkillsWithEnoughLevel() {
        assertTrue(EmployeeValuers.valuerAllSkillsWithAnyLevel.isEmployeeRight(
                getSkills_a2b2c2(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerAllSkillsWithAnyLevel_AllSkillsWithNotEnoughLevel() {
        assertTrue(EmployeeValuers.valuerAllSkillsWithAnyLevel.isEmployeeRight(
                getSkills_a1b1c1(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerAllSkillsWithAnyLevel_NotAllSkillsExistAllSkillsHaveEnoughLevel() {
        assertFalse(EmployeeValuers.valuerAllSkillsWithAnyLevel.isEmployeeRight(
                getSkills_b2c2(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerAllSkillsWithAnyLevel_EmptySkillsList() {
        assertFalse(EmployeeValuers.valuerAllSkillsWithAnyLevel.isEmployeeRight(
                getSkills_empty(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerOneSkillWithEnoughLevel_AllSkillsWithEnoughLevel() {
        assertTrue(EmployeeValuers.valuerOneSkillWithEnoughLevel.isEmployeeRight(
                getSkills_a2b2c2(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerOneSkillWithEnoughLevel_OneSkillWithEnoughLevel() {
        assertTrue(EmployeeValuers.valuerOneSkillWithEnoughLevel.isEmployeeRight(
                getSkills_a2(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerOneSkillWithEnoughLevel_AllSkillsWithNotEnoughLevel() {
        assertFalse(EmployeeValuers.valuerOneSkillWithEnoughLevel.isEmployeeRight(
                getSkills_a1b1c1(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerOneSkillWithEnoughLevel_AnotherSkill() {
        assertFalse(EmployeeValuers.valuerOneSkillWithEnoughLevel.isEmployeeRight(
                getSkills_e5(), getRequirements_a2bN2c2()));
    }

    @Test
    public void testValuerOneSkillWithEnoughLevel_emptySkillsList() {
        assertFalse(EmployeeValuers.valuerOneSkillWithEnoughLevel.isEmployeeRight(
                getSkills_empty(), getRequirements_a2bN2c2()));
    }

    private RequirementsList getRequirements_a2bN2c2() {
        RequirementsList requirements = new RequirementsList();
        requirements.addRequirement("a", new RequirementProperties(2, false));
        requirements.addRequirement("b", new RequirementProperties(2, true));
        requirements.addRequirement("c", new RequirementProperties(2, false));

        return requirements;
    }

    private SkillsList getSkills_a2b2c2() {
        SkillsList skills = new SkillsList();
        skills.addSkill("a", 2);
        skills.addSkill("b", 2);
        skills.addSkill("c", 2);

        return skills;
    }

    private SkillsList getSkills_a2b1c2() {
        SkillsList skills = new SkillsList();
        skills.addSkill("a", 2);
        skills.addSkill("b", 1);
        skills.addSkill("c", 2);

        return skills;
    }

    private SkillsList getSkills_a1b2c2() {
        SkillsList skills = new SkillsList();
        skills.addSkill("a", 1);
        skills.addSkill("b", 2);
        skills.addSkill("c", 2);

        return skills;
    }

    private SkillsList getSkills_empty() {
        return new SkillsList();
    }

    private SkillsList getSkills_e5() {
        SkillsList skills = new SkillsList();
        skills.addSkill("e", 5);

        return skills;
    }

    private SkillsList getSkills_a2() {
        SkillsList skills = new SkillsList();
        skills.addSkill("a", 2);

        return skills;
    }

    private SkillsList getSkills_a1b1c1() {
        SkillsList skills = new SkillsList();
        skills.addSkill("a", 1);
        skills.addSkill("b", 1);
        skills.addSkill("c", 1);

        return skills;
    }

    private SkillsList getSkills_b2c2() {
        SkillsList skills = new SkillsList();
        skills.addSkill("b", 2);
        skills.addSkill("c", 2);

        return skills;
    }
}
