package net.thumbtack.school.hiring.data.models.responses;

public class SuccessEmptyDtoResponse implements DtoResponse {
    private String result;

    public static SuccessEmptyDtoResponse makeNewInstance() {
        SuccessEmptyDtoResponse response = new SuccessEmptyDtoResponse();
        response.result = "success";
        return response;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
