package net.thumbtack.school.hiring.data.daoimpl;

import net.thumbtack.school.hiring.data.DataStorage;
import net.thumbtack.school.hiring.data.dao.GlobalInfoDao;

import java.util.Set;

public class GlobalInfoDaoImpl implements GlobalInfoDao {
    private DataStorage dataStorage;

    public GlobalInfoDaoImpl(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public Set<String> getDefinedSkillsNames() {
        return dataStorage.getDefinedSkills();
    }
}
