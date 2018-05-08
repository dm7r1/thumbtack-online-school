package net.thumbtack.school.hiring.server;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.DataBase;
import net.thumbtack.school.hiring.data.DataBaseImpl;
import net.thumbtack.school.hiring.data.models.requests.*;
import net.thumbtack.school.hiring.data.models.responses.SuccessEmptyDtoResponse;
import net.thumbtack.school.hiring.services.*;

public class Server {
    private DataBase dataBase;
    private PersonsManager usersManager;
    private VacanciesManager vacanciesManager;
    private SkillsManager skillsManager;
    private GlobalInfoManager globalInfoManager;
    private SearchManager searchManager;
    private Gson gson;

    public Server() {
        dataBase = new DataBaseImpl();
        usersManager = new PersonsManager(dataBase);
        vacanciesManager = new VacanciesManager(dataBase);
        skillsManager = new SkillsManager(dataBase);
        globalInfoManager = new GlobalInfoManager(dataBase);
        searchManager = new SearchManager(dataBase);
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

    public String deleteVacancy(String requestJsonString) {
        DeleteVacancyDtoRequest request = gson.fromJson(requestJsonString, DeleteVacancyDtoRequest.class);
        return gson.toJson(vacanciesManager.deleteVacancy(request));
    }

    public String getVacancies(String requestJsonString) {
        GetVacanciesDtoRequest request = gson.fromJson(requestJsonString, GetVacanciesDtoRequest.class);
        return gson.toJson(vacanciesManager.getVacancies(request));
    }

    public String changeVacancy(String requestJsonString) {
        ChangeVacancyDtoRequest request = gson.fromJson(requestJsonString, ChangeVacancyDtoRequest.class);
        return gson.toJson(vacanciesManager.changeVacancy(request));
    }

    public String changeSkills(String requestJsonString) {
        ChangeSkillsDtoRequest request = gson.fromJson(requestJsonString, ChangeSkillsDtoRequest.class);
        return gson.toJson(skillsManager.changeSkills(request));
    }

    public String getSkills(String requestJsonString) {
        GetSkillsDtoRequest request = gson.fromJson(requestJsonString, GetSkillsDtoRequest.class);
        return gson.toJson(skillsManager.getSkills(request));
    }

    public String getDefinedSkills(String requestJsonString) {
        GetDefinedSkillsDtoRequest request = gson.fromJson(requestJsonString, GetDefinedSkillsDtoRequest.class);
        return gson.toJson(globalInfoManager.getDefinedSkills(request));
    }

    public String searchEmployeesByVacancy(String requestJsonString) {
        SearchEmployeesByVacancyDtoRequest request = gson.fromJson(requestJsonString, SearchEmployeesByVacancyDtoRequest.class);
        return gson.toJson(searchManager.searchEmployeesByVacancy(request));
    }

    public String searchVacanciesBySkills(String requestJsonString) {
        SearchVacanciesBySkillsDtoRequest request = gson.fromJson(requestJsonString, SearchVacanciesBySkillsDtoRequest.class);
        return gson.toJson(searchManager.searchVacanciesForEmployee(request));
    }
}
