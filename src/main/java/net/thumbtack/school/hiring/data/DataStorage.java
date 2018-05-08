package net.thumbtack.school.hiring.data;

import net.thumbtack.school.hiring.data.models.Employee;
import net.thumbtack.school.hiring.data.models.Employer;

import java.util.*;

class DataStorage {
    private Map<UUID,Employee> employees;
    private Map<UUID,Employer> employers;
    private Set<String> definedSkills;

    DataStorage() {
        employees = new HashMap<>();
        employers = new HashMap<>();
        definedSkills = new HashSet<>();
    }

    Map<UUID,Employee> getEmployees() {
        return employees;
    }

    Map<UUID,Employer> getEmployers() {
        return employers;
    }

    public Set<String> getDefinedSkills() {
        return definedSkills;
    }
}
