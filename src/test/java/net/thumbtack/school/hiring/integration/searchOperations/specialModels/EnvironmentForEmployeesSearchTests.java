package net.thumbtack.school.hiring.integration.searchOperations.specialModels;

import net.thumbtack.school.hiring.integration.Server;

import java.util.UUID;

public class EnvironmentForEmployeesSearchTests {
    private Server server;
    private UUID employerToken;
    private int vacancyNumber;

    public EnvironmentForEmployeesSearchTests(Server server, UUID employerToken, int vacancyNumber) {
        this.server = server;
        this.employerToken = employerToken;
        this.vacancyNumber = vacancyNumber;
    }

    public Server getServer() {
        return server;
    }

    public UUID getEmployerToken() {
        return employerToken;
    }

    public int getVacancyNumber() {
        return vacancyNumber;
    }
}
