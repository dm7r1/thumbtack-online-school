package net.thumbtack.school.ttschool;

public enum TrainingErrorCode {
    TRAINEE_WRONG_FIRSTNAME("First name string is wrong (null or empty string)"),
    TRAINEE_WRONG_LASTNAME("Last name string is wrong (null or empty string)"),
    TRAINEE_WRONG_RATING("Rating is wrong (rating must be in the range 1 - 5)");

    private String errorString;

    TrainingErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
