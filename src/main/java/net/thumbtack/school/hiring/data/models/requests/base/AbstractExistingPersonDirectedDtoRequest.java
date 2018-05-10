package net.thumbtack.school.hiring.data.models.requests.base;

import java.util.UUID;

public class AbstractExistingPersonDirectedDtoRequest extends AbstractDtoRequest {
    protected UUID token;

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
