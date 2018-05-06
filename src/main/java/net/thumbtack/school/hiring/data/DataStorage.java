package net.thumbtack.school.hiring.data;

import net.thumbtack.school.hiring.data.models.Employee;
import net.thumbtack.school.hiring.data.models.Employer;
import net.thumbtack.school.hiring.data.models.Vacancy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

class DataStorage {
    private Map<UUID,Employee> employees;
    private Map<UUID,Employer> employers;

    DataStorage() {
        employees = new HashMap<>();
        employers = new HashMap<>();
    }

    Map<UUID,Employee> getEmployees() {
        return employees;
    }

    Map<UUID,Employer> getEmployers() {
        return employers;
    }
}
