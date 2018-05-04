package net.thumbtack.school.hiring.data.models.responses;

import java.util.UUID;

public class SuccessfulRegisteredDtoResponse implements DtoResponse {
    private UUID token;

    public static SuccessfulRegisteredDtoResponse makeNewInstance(UUID token) {
        SuccessfulRegisteredDtoResponse response = new SuccessfulRegisteredDtoResponse();
        response.token = token;
        return response;
    }

    public UUID getToken() {
        return token;
    }
}
