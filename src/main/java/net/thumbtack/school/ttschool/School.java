package net.thumbtack.school.ttschool;

import java.util.*;

public class School {
    private int year;
    private String name;
    private Set<Group> groups;

    public School(String name, int year) throws TrainingException {
        setName(name);
        setYear(year);
        groups = new TreeSet<>(Comparator.comparing(Group::getName));
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if(name == null || name.length() == 0)
            throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_NAME);
        this.name = name;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void  addGroup(Group group) throws TrainingException {
        if(!groups.add(group))
            throw new TrainingException(TrainingErrorCode.DUPLICATE_GROUP_NAME);
    }

    public void  removeGroup(Group group) throws TrainingException {
        if(!groups.remove(group))
            throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
    }

    public void  removeGroup(String name) throws TrainingException {
        for(Group group: groups)
            if(name.equals(group.getName())) {
                groups.remove(group);
                return;
            }
        throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
    }

    public boolean  containsGroup(Group group) {
        return groups.contains(group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return year == school.year &&
                Objects.equals(name, school.name) &&
                Objects.equals(groups, school.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, name, groups);
    }
}
