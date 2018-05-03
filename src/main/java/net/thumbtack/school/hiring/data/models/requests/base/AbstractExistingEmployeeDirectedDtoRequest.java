package net.thumbtack.school.hiring.data.models.requests.base;

import java.util.UUID;

public class AbstractExistingEmployeeDirectedDtoRequest extends AbstractDtoRequest {
    protected UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
