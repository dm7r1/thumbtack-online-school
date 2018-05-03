package net.thumbtack.school.hiring.data.models.responses;

import net.thumbtack.school.hiring.data.models.InvalidRequestException;

public class ErrorDtoResponse implements DtoResponse {
    private String error;

    public static ErrorDtoResponse fromException(InvalidRequestException exception) {
        ErrorDtoResponse response = new ErrorDtoResponse();
        response.error = exception.getDescription();
        return response;
    }

    public String getError() {
        return error;
    }
}
