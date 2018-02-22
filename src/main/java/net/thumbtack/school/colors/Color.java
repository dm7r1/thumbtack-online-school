package net.thumbtack.school.colors;

public enum Color {
    RED,
    GREEN,
    BLUE;

    public static Color colorFromString(String colorString) throws ColorException {
        try {
            return Color.valueOf(colorString);
        }
        catch (IllegalArgumentException exception) {
            throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
        }
        catch (NullPointerException exception) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
    }
}
