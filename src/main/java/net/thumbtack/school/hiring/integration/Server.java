package net.thumbtack.school.hiring.integration;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.DataStorage;
import net.thumbtack.school.hiring.data.models.requests.*;
import net.thumbtack.school.hiring.services.*;

import java.io.IOException;

public class Server {
    private DataStorage dataStorage;

    private PersonsService personsService;
    private VacanciesService vacanciesService;
    private SkillsService skillsService;
    private GlobalInfoService globalInfoService;
    private SearchService searchService;
    private Gson gson;

    private void init(DataStorage dataStorage) {
        this.dataStorage = dataStorage;

        personsService = new PersonsService(dataStorage);
        vacanciesService = new VacanciesService(dataStorage);
        skillsService = new SkillsService(dataStorage);
        globalInfoService = new GlobalInfoService(dataStorage);
        searchService = new SearchService(dataStorage);
        gson = new Gson();
    }

    public DataStorage getDataStorage() {
        return dataStorage;
    }

    public void startServer(String savedDataFileName) {
        if(savedDataFileName.length() != 0) {
            try {
                init(DataStorage.getInstanceFromFile(savedDataFileName));
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
        else {
            init(DataStorage.getEmptyInstance());
        }
    }

    public void stopServer(String saveDataFileName) {
        if (saveDataFileName.length() != 0) {
            try {
                dataStorage.saveToFile(saveDataFileName);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public String registerEmployer(String requestJsonString) {
        RegisterEmployerDtoRequest request = gson.fromJson(requestJsonString, RegisterEmployerDtoRequest.class);
        return gson.toJson(personsService.registerEmployer(request));
    }

    public String registerEmployee(String requestJsonString) {
        RegisterEmployeeDtoRequest request = gson.fromJson(requestJsonString, RegisterEmployeeDtoRequest.class);
        return gson.toJson(personsService.registerEmployee(request));
    }

    public String changeEmployerInfo(String requestJsonString) {
        ChangeEmployerInfoDtoRequest request = gson.fromJson(requestJsonString, ChangeEmployerInfoDtoRequest.class);
        return gson.toJson(personsService.changeEmployerInfo(request));
    }

    public String changeEmployeeInfo(String requestJsonString) {
        ChangeEmployeeInfoDtoRequest request = gson.fromJson(requestJsonString, ChangeEmployeeInfoDtoRequest.class);
        return gson.toJson(personsService.changeEmployeeInfo(request));
    }

    public String deleteEmployee(String requestJsonString) {
        DeleteEmployeeDtoRequest request = gson.fromJson(requestJsonString, DeleteEmployeeDtoRequest.class);
        return gson.toJson(personsService.deleteEmployee(request));
    }

    public String deleteEmployer(String requestJsonString) {
        DeleteEmployerDtoRequest request = gson.fromJson(requestJsonString, DeleteEmployerDtoRequest.class);
        return gson.toJson(personsService.deleteEmployer(request));
    }

    public String addVacancy(String requestJsonString) {
        AddVacancyDtoRequest request = gson.fromJson(requestJsonString, AddVacancyDtoRequest.class);
        return gson.toJson(vacanciesService.addVacancy(request));
    }

    public String deleteVacancy(String requestJsonString) {
        DeleteVacancyDtoRequest request = gson.fromJson(requestJsonString, DeleteVacancyDtoRequest.class);
        return gson.toJson(vacanciesService.deleteVacancy(request));
    }

    public String getVacancies(String requestJsonString) {
        GetVacanciesDtoRequest request = gson.fromJson(requestJsonString, GetVacanciesDtoRequest.class);
        return gson.toJson(vacanciesService.getVacancies(request));
    }

    public String changeVacancy(String requestJsonString) {
        ChangeVacancyDtoRequest request = gson.fromJson(requestJsonString, ChangeVacancyDtoRequest.class);
        return gson.toJson(vacanciesService.changeVacancy(request));
    }

    public String changeSkills(String requestJsonString) {
        ChangeSkillsDtoRequest request = gson.fromJson(requestJsonString, ChangeSkillsDtoRequest.class);
        return gson.toJson(skillsService.changeSkills(request));
    }

    public String getSkills(String requestJsonString) {
        GetSkillsDtoRequest request = gson.fromJson(requestJsonString, GetSkillsDtoRequest.class);
        return gson.toJson(skillsService.getSkills(request));
    }

    public String getDefinedSkills(String requestJsonString) {
        GetDefinedSkillsDtoRequest request = gson.fromJson(requestJsonString, GetDefinedSkillsDtoRequest.class);
        return gson.toJson(globalInfoService.getDefinedSkills(request));
    }

    public String searchEmployeesByVacancy(String requestJsonString) {
        SearchEmployeesByVacancyDtoRequest request = gson.fromJson(requestJsonString, SearchEmployeesByVacancyDtoRequest.class);
        return gson.toJson(searchService.searchEmployeesByVacancy(request));
    }

    public String searchVacanciesBySkills(String requestJsonString) {
        SearchVacanciesBySkillsDtoRequest request = gson.fromJson(requestJsonString, SearchVacanciesBySkillsDtoRequest.class);
        return gson.toJson(searchService.searchVacanciesForEmployee(request));
    }

    public String setEmployeeActive(String requestJsonString) {
        SetEmployeeActiveDtoRequest request = gson.fromJson(requestJsonString, SetEmployeeActiveDtoRequest.class);
        return gson.toJson(personsService.setEmployeeActive(request));
    }

    public String setVacancyActive(String requestJsonString) {
        SetVacancyActiveDtoRequest request = gson.fromJson(requestJsonString, SetVacancyActiveDtoRequest.class);
        return gson.toJson(vacanciesService.setVacancyActive(request));
    }

    public String hireEmployee(String requestJsonString) {
        HireEmployeeDtoRequest request = gson.fromJson(requestJsonString, HireEmployeeDtoRequest.class);
        return gson.toJson(searchService.hireEmployee(request));
    }
}
