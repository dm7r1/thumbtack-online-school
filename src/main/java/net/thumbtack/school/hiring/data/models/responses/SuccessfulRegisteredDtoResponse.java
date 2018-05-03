package net.thumbtack.school.hiring.data.models.responses;

import java.util.UUID;

public class SuccessfulRegisteredDtoResponse implements DtoResponse {
    private UUID token;

    public SuccessfulRegisteredDtoResponse(UUID token) {
       this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}
