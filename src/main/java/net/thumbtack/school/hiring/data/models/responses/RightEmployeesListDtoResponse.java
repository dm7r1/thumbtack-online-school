package net.thumbtack.school.hiring.data.models.responses;

import net.thumbtack.school.hiring.data.models.Employee;
import net.thumbtack.school.hiring.data.models.responses.specialModels.ShortEmployeeInfoDto;

import java.util.LinkedList;
import java.util.List;

public class RightEmployeesListDtoResponse implements DtoResponse {
    private List<ShortEmployeeInfoDto> rightEmployees;

    public static RightEmployeesListDtoResponse makeInstance(List<Employee> rightEmployees) {
        RightEmployeesListDtoResponse response = new RightEmployeesListDtoResponse();
        response.setRightEmployees(rightEmployees);
        return response;
    }

    public List<ShortEmployeeInfoDto> getRightEmployees() {
        return rightEmployees;
    }

    public void addRightEmployee(Employee employee) {
        rightEmployees.add(ShortEmployeeInfoDto.fromEmployee(employee));
    }

    public void addRightEmployees(List<Employee> rightEmployees) {
        for(Employee employee: rightEmployees)
            addRightEmployee(employee);
    }

    public void setRightEmployees(List<Employee> rightEmployees) {
        this.rightEmployees = new LinkedList<>();
        addRightEmployees(rightEmployees);
    }
}
