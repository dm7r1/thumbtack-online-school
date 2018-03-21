package net.thumbtack.school.ttschool;

public enum TrainingErrorCode {
    TRAINEE_WRONG_FIRSTNAME("First name string is wrong (null or empty string)"),
    TRAINEE_WRONG_LASTNAME("Last name string is wrong (null or empty string)"),
    TRAINEE_WRONG_RATING("Rating is wrong (rating must be in the range 1 - 5)"),
    GROUP_WRONG_NAME("Group name string is wrong (null or empty string)"),
    GROUP_WRONG_ROOM("Room string is wrong (null or empty string)"),
    TRAINEE_NOT_FOUND("Trainee isn't found"),
    SCHOOL_WRONG_NAME("School name is wrong (null or empty string)"),
    DUPLICATE_GROUP_NAME("Group with that name already exists"),
    GROUP_NOT_FOUND("School doesn't contain such group"),
    DUPLICATE_TRAINEE("Info about such trainee already exists"),
    EMPTY_TRAINEE_QUEUE("Trainee queue is empty");

    private String errorString;

    TrainingErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
