package net.thumbtack.school.hiring.data.exceptions;

public class InvalidRequestException extends Exception {
    private InvalidRequestErrors error;
    public InvalidRequestException(InvalidRequestErrors error) {
        this.error = error;
    }

    public InvalidRequestErrors getError() {
        return error;
    }

    public String getDescription() {
        return error.description;
    }
}
