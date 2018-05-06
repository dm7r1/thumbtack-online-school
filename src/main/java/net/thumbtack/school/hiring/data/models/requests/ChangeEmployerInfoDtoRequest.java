package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.requests.base.AbstractChangePersonInfoDtoRequest;

public class ChangeEmployerInfoDtoRequest extends AbstractChangePersonInfoDtoRequest {
    private String newCompanyName, newAddress;

    public String getNewCompanyName() {
        return newCompanyName;
    }

    public void setNewCompanyName(String newCompanyName) {
        this.newCompanyName = newCompanyName;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }
}
