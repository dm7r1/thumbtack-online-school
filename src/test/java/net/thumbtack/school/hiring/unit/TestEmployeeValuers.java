package net.thumbtack.school.hiring.unit;

import net.thumbtack.school.hiring.data.models.stored.Employer;
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
    public void testValuerAllSkillsWithEnoughLevel() {
        EmployeeValuer valuer = EmployeeValuers.valuerAllSkillsWithEnoughLevel;

        RequirementsList requirements = new RequirementsList();
        requirements.addRequirement("a", new RequirementProperties(2, false));
        requirements.addRequirement("b", new RequirementProperties(2, true));
        requirements.addRequirement("c", new RequirementProperties(2, false));

        SkillsList skills = new SkillsList();
        skills.addSkill("a", 2);
        skills.addSkill("b", 2);
        skills.addSkill("c", 2);
        assertTrue(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        skills.addSkill("a", 2);
        skills.addSkill("b", 1);
        skills.addSkill("c", 2);
        assertFalse(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        skills.addSkill("a", 2);
        skills.addSkill("b", 2);
        assertFalse(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        assertFalse(valuer.isEmployeeRight(skills, requirements));
    }

    @Test
    public void testValuerNecessarySkillsWithEnoughLevel() {
        EmployeeValuer valuer = EmployeeValuers.valuerNecessarySkillsWithEnoughLevel;

        RequirementsList requirements = new RequirementsList();
        requirements.addRequirement("a", new RequirementProperties(2, false));
        requirements.addRequirement("b", new RequirementProperties(2, true));
        requirements.addRequirement("c", new RequirementProperties(2, true));

        SkillsList skills = new SkillsList();
        skills.addSkill("a", 2);
        skills.addSkill("b", 2);
        skills.addSkill("c", 2);
        assertTrue(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        skills.addSkill("a", 1);
        skills.addSkill("b", 2);
        skills.addSkill("c", 2);
        assertTrue(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        skills.addSkill("b", 2);
        skills.addSkill("c", 2);
        assertTrue(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        skills.addSkill("a", 2);
        skills.addSkill("b", 1);
        skills.addSkill("c", 2);
        assertFalse(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        skills.addSkill("a", 2);
        skills.addSkill("c", 2);
        assertFalse(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        assertFalse(valuer.isEmployeeRight(skills, requirements));
    }

    @Test
    public void testValuerAllSkillsWithAnyLevel() {
        EmployeeValuer valuer = EmployeeValuers.valuerAllSkillsWithAnyLevel;

        RequirementsList requirements = new RequirementsList();
        requirements.addRequirement("a", new RequirementProperties(2, false));
        requirements.addRequirement("b", new RequirementProperties(2, true));
        requirements.addRequirement("c", new RequirementProperties(2, true));

        SkillsList skills = new SkillsList();
        skills.addSkill("a", 2);
        skills.addSkill("b", 2);
        skills.addSkill("c", 2);
        assertTrue(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        skills.addSkill("a", 1);
        skills.addSkill("b", 1);
        skills.addSkill("c", 1);
        assertTrue(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        skills.addSkill("b", 2);
        skills.addSkill("c", 2);
        assertFalse(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        skills.addSkill("a", 2);
        skills.addSkill("c", 2);
        assertFalse(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        assertFalse(valuer.isEmployeeRight(skills, requirements));
    }

    @Test
    public void testOneSkillWithEnoughLevel() {
        EmployeeValuer valuer = EmployeeValuers.valuerOneSkillWithEnoughLevel;

        RequirementsList requirements = new RequirementsList();
        requirements.addRequirement("a", new RequirementProperties(2, false));
        requirements.addRequirement("b", new RequirementProperties(2, true));
        requirements.addRequirement("c", new RequirementProperties(2, false));

        SkillsList skills = new SkillsList();
        skills.addSkill("a", 2);
        skills.addSkill("b", 3);
        skills.addSkill("c", 5);
        assertTrue(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        skills.addSkill("a", 2);
        skills.addSkill("b", 2);
        skills.addSkill("c", 2);
        assertTrue(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        skills.addSkill("a", 2);
        skills.addSkill("b", 2);
        skills.addSkill("c", 2);
        assertTrue(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        skills.addSkill("a", 2);
        assertTrue(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        skills.addSkill("a", 1);
        assertFalse(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        skills.addSkill("skill", 5);
        assertFalse(valuer.isEmployeeRight(skills, requirements));

        skills = new SkillsList();
        assertFalse(valuer.isEmployeeRight(skills, requirements));
    }
}
