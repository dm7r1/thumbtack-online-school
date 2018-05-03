package net.thumbtack.school.hiring.data.models;

import java.util.HashMap;
import java.util.Map;

public class Employee extends AbstractPerson {
    private Map<String, Integer> skills;
    private boolean active;

    public Employee(String firstName, String lastName, String patronymic, String login, String email, String password) {
        super(firstName, lastName, patronymic, login, email, password);
        skills = new HashMap<>();
        active = true;
    }
}
