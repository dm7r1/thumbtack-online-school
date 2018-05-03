package net.thumbtack.school.hiring.data.models.requests.base;

public class AbstractDtoRequest {
    private String operation;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
