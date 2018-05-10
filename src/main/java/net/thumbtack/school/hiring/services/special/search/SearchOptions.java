package net.thumbtack.school.hiring.services.special.search;

public enum SearchOptions {
    ALL_SKILLS_WITH_ENOUGH_LEVEL(EmployeeValuers.valuerAllSkillsWithEnoughLevel),
    NECESSARY_SKILLS_WITH_ENOUGH_LEVEL(EmployeeValuers.valuerNecessarySkillsWithEnoughLevel),
    ALL_SKILLS_WITH_ANY_LEVEL(EmployeeValuers.valuerAllSkillsWithAnyLevel),
    ONE_SKILL_WITH_ENOUGH_LEVEL(EmployeeValuers.valuerOneSkillWithEnoughLevel);

    private final EmployeeValuer valuer;

    SearchOptions(EmployeeValuer valuer) {
        this.valuer = valuer;
    }

    public EmployeeValuer getValuer() {
        return valuer;
    }

    public static SearchOptions getByCode(int code) {
        switch (code) {
            case 0:
                return ALL_SKILLS_WITH_ENOUGH_LEVEL;
            case 1:
                return NECESSARY_SKILLS_WITH_ENOUGH_LEVEL;
            case 2:
                return ALL_SKILLS_WITH_ANY_LEVEL;
            case 3:
                return ONE_SKILL_WITH_ENOUGH_LEVEL;
            default:
                return null;
        }
    }

    public static boolean isCodeValid(int code) {
        return 0 <= code && code <= 3;
    }
}
