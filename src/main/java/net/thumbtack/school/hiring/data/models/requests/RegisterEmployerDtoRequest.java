package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.requests.base.AbstractRegisterPersonDtoRequest;

public class RegisterEmployerDtoRequest extends AbstractRegisterPersonDtoRequest {
    private String companyName, address;

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
