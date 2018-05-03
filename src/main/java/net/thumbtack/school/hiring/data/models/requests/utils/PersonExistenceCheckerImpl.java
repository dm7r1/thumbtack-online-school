package net.thumbtack.school.hiring.data.models.requests.utils;

import net.thumbtack.school.hiring.data.DataBase;

import java.util.UUID;

public class PersonExistenceCheckerImpl implements PersonExistenceChecker {
    private DataBase dataBase;
    public PersonExistenceCheckerImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public boolean employeeExists(UUID uuid) {
        return this.dataBase.employeeExists(uuid);
    }

    @Override
    public boolean employerExists(UUID uuid) {
        return this.dataBase.employerExists(uuid);
    }
}
