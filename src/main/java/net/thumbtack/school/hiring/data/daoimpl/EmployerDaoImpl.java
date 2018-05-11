package net.thumbtack.school.hiring.data.daoimpl;

import net.thumbtack.school.hiring.data.DataStorage;
import net.thumbtack.school.hiring.data.dao.EmployerDao;
import net.thumbtack.school.hiring.data.models.stored.Employer;

import java.util.UUID;

public class EmployerDaoImpl implements EmployerDao {
    private DataStorage dataStorage;

    public EmployerDaoImpl(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public UUID insertEmployer(Employer employer)
    {
        UUID uuid = UUID.randomUUID();
        dataStorage.getEmployers().put(uuid, employer);
        return uuid;
    }

    public Employer getEmployerByUUID(UUID uuid) {
        return dataStorage.getEmployers().get(uuid);
    }

    public boolean employerExists(UUID uuid) {
        return dataStorage.getEmployers().containsKey(uuid);
    }

    public boolean loginBelongsEmployer(String login) {
        for(Employer employer: dataStorage.getEmployers().values()) {
            if (employer.getLogin().equals(login))
                return true;
        }
        return false;
    }

    public void deleteEmployerByUUID(UUID uuid) {
        dataStorage.getEmployers().remove(uuid);
    }
}
