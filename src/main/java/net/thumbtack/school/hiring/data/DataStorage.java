package net.thumbtack.school.hiring.data;

import net.thumbtack.school.hiring.data.models.stored.Employee;
import net.thumbtack.school.hiring.data.models.stored.Employer;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataStorage that = (DataStorage) o;
        return Objects.equals(employees, that.employees) &&
                Objects.equals(employers, that.employers) &&
                Objects.equals(definedSkills, that.definedSkills);
    }

    @Override
    public int hashCode() {

        return Objects.hash(employees, employers, definedSkills);
    }
}
