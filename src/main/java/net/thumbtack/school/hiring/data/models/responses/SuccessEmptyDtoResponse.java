package net.thumbtack.school.hiring.data.models.responses;

public class SuccessEmptyDtoResponse implements DtoResponse {
    private String result;

    public SuccessEmptyDtoResponse() {
        this.result = "success";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
