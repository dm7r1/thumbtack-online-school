package net.thumbtack.school.hiring.server;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.DataBase;
import net.thumbtack.school.hiring.data.DataBaseImpl;
import net.thumbtack.school.hiring.data.models.requests.*;
import net.thumbtack.school.hiring.services.PersonsManager;
import net.thumbtack.school.hiring.services.VacanciesManager;

public class Server {
    private DataBase dataBase;
    private PersonsManager usersManager;
    private VacanciesManager vacanciesManager;
    private Gson gson;

    public Server() {
        dataBase = new DataBaseImpl();
        usersManager = new PersonsManager(dataBase);
        vacanciesManager = new VacanciesManager(dataBase);
        gson = new Gson();
    }

    public void startServer(String savedDataFileName) {}

    public void stopServer(String saveDataFileName) {}

    public String registerEmployer(String requestJsonString) {
        RegisterEmployerDtoRequest request = gson.fromJson(requestJsonString, RegisterEmployerDtoRequest.class);
        return gson.toJson(usersManager.registerEmployer(request));
    }

    public String registerEmployee(String requestJsonString) {
        RegisterEmployeeDtoRequest request = gson.fromJson(requestJsonString, RegisterEmployeeDtoRequest.class);
        return gson.toJson(usersManager.registerEmployee(request));
    }

    public String changeEmployerInfo(String requestJsonString) {
        ChangeEmployerInfoDtoRequest request = gson.fromJson(requestJsonString, ChangeEmployerInfoDtoRequest.class);
        return gson.toJson(usersManager.changeEmployerInfo(request));
    }

    public String changeEmployeeInfo(String requestJsonString) {
        ChangeEmployeeInfoDtoRequest request = gson.fromJson(requestJsonString, ChangeEmployeeInfoDtoRequest.class);
        return gson.toJson(usersManager.changeEmployeeInfo(request));
    }

    public String deleteEmployee(String requestJsonString) {
        DeleteEmployeeDtoRequest request = gson.fromJson(requestJsonString, DeleteEmployeeDtoRequest.class);
        return gson.toJson(usersManager.deleteEmployee(request));
    }

    public String deleteEmployer(String requestJsonString) {
        DeleteEmployerDtoRequest request = gson.fromJson(requestJsonString, DeleteEmployerDtoRequest.class);
        return gson.toJson(usersManager.deleteEmployer(request));
    }

    public String addVacancy(String requestJsonString) {
        AddVacancyDtoRequest request = gson.fromJson(requestJsonString, AddVacancyDtoRequest.class);
        return gson.toJson(vacanciesManager.addVacancy(request));
    }
}
