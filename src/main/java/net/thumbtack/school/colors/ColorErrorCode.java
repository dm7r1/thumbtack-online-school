package net.thumbtack.school.colors;

public enum ColorErrorCode {
    WRONG_COLOR_STRING("color name in the string is wrong"),
    NULL_COLOR("color or color string is NULL");

    private String errorString;

    ColorErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
