package net.thumbtack.school.hiring.data.models.requests.utils.checkers;

public class SkillInfoChecker {
    public static boolean isValidSkillLvl(Integer skillLvl) {
        return skillLvl >= 1 && skillLvl <= 5;
    }

    public static boolean isValidSkillName(String skillName) {
        return skillName.length() > 0;
    }
}
