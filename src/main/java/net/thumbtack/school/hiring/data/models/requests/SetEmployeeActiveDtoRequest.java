package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.requests.base.AbstractExistingPersonDirectedDtoRequest;

public class SetEmployeeActiveDtoRequest extends AbstractExistingPersonDirectedDtoRequest {
    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
