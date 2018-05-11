package net.thumbtack.school.hiring.data;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.stored.Employee;
import net.thumbtack.school.hiring.data.models.stored.Employer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DataStorage {
    private Map<UUID,Employee> employees;
    private Map<UUID,Employer> employers;
    private Set<String> definedSkills;

    DataStorage() {
        employees = new HashMap<>();
        employers = new HashMap<>();
        definedSkills = new HashSet<>();
    }

    public Map<UUID,Employee> getEmployees() {
        return employees;
    }

    public Map<UUID,Employer> getEmployers() {
        return employers;
    }

    public Set<String> getDefinedSkills() {
        return definedSkills;
    }

    public static DataStorage getEmptyInstance() {
        return new DataStorage();
    }

    public void saveToFile(String filename) throws IOException {
        String data = new Gson().toJson(this);
        FileWriter fileWriter = new FileWriter(new File(filename));
        fileWriter.write(data);
        fileWriter.close();
    }

    public static DataStorage getInstanceFromFile(String filename) throws IOException {
        File file = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        fileInputStream.read(data);
        return new Gson().fromJson(new String(data), DataStorage.class);
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
