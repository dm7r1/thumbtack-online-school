package net.thumbtack.school.hiring.integration.searchOperations.specialModels;

import net.thumbtack.school.hiring.integration.Server;

import java.util.UUID;

public class EnvironmentForVacationsSearchTests {
    private Server server;
    private UUID employeeToken;

    public EnvironmentForVacationsSearchTests(Server server, UUID employeeToken) {
        this.server = server;
        this.employeeToken = employeeToken;
    }

    public Server getServer() {
        return server;
    }

    public UUID getEmployeeToken() {
        return employeeToken;
    }
}
