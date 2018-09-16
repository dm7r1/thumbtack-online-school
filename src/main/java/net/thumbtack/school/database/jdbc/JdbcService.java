package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcService {
    public static void insertTrainee(Trainee trainee) throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("INSERT INTO trainees values(null, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        sttmnt.setString(1, trainee.getFirstName());
        sttmnt.setString(2, trainee.getLastName());
        sttmnt.setInt(3, trainee.getRating());
        sttmnt.executeUpdate();
        ResultSet rs = sttmnt.getGeneratedKeys();
        rs.next();
        trainee.setId(rs.getInt(1));

    }

    public  static void updateTrainee(Trainee trainee) throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("UPDATE trainees SET firstname = ?, lastname = ?, rating = ? WHERE id = ?");
        sttmnt.setString(1, trainee.getFirstName());
        sttmnt.setString(2, trainee.getLastName());
        sttmnt.setInt(3, trainee.getRating());
        sttmnt.setInt(4, trainee.getId());
        sttmnt.executeUpdate();
    }

    private static Trainee traineeFromResultSetByNames(ResultSet rs) throws SQLException {
        return new Trainee(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getInt("rating"));
    }

    private static Trainee traineeFromResultSetByNumbers(ResultSet rs) throws SQLException {
        return new Trainee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
    }

    private static Subject subjectFromResultSetByNames(ResultSet rs) throws SQLException {
        return new Subject(rs.getInt("id"), rs.getString("name"));
    }

    private static Subject subjectFromResultSetByNumbers(ResultSet rs) throws SQLException {
        return new Subject(rs.getInt(1), rs.getString(2));
    }

    private static ResultSet getTraineeResultSetById(int traineeId) throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("SELECT * FROM trainees WHERE id = ?");
        sttmnt.setInt(1, traineeId);
        sttmnt.executeQuery();
        return sttmnt.getResultSet();
    }

    private static ResultSet getTraineesResultSet() throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("SELECT * FROM trainees");
        sttmnt.executeQuery();
        return sttmnt.getResultSet();
    }

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException {
       ResultSet rs = getTraineeResultSetById(traineeId);
       if (!rs.next())
           return null;

       return traineeFromResultSetByNames(rs);
    }

    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException {
        ResultSet rs = getTraineeResultSetById(traineeId);
        if (!rs.next())
            throw new SQLException();


        return traineeFromResultSetByNumbers(rs);
    }

    public static List<Trainee> getTraineesUsingColNames() throws SQLException {
        ResultSet rs = getTraineesResultSet();
        List<Trainee> trainees = new ArrayList<>();
        while (rs.next()) {
            trainees.add(traineeFromResultSetByNames(rs));
        }

        return trainees;
    }

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException {
        ResultSet rs = getTraineesResultSet();
        List<Trainee> trainees = new ArrayList<>();
        while (rs.next()) {
            trainees.add(traineeFromResultSetByNumbers(rs));
        }

        return trainees;
    }

    public static void deleteTrainee(Trainee trainee) throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("DELETE FROM trainees WHERE id = ?");
        sttmnt.setInt(1,  trainee.getId());
        sttmnt.executeUpdate();
    }

    public static void deleteTrainees() throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("DELETE FROM trainees");
        sttmnt.executeUpdate();
    }

    public static void insertSubject(Subject subject) throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("INSERT INTO subjects values(null, ?)", Statement.RETURN_GENERATED_KEYS);
        sttmnt.setString(1, subject.getName());
        sttmnt.executeUpdate();
        ResultSet rs = sttmnt.getGeneratedKeys();
        rs.next();
        subject.setId(rs.getInt(1));
    }

    private static ResultSet getSubjectResultSetById(int subjectId) throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("SELECT * FROM subjects WHERE id = ?");
        sttmnt.setInt(1, subjectId);
        sttmnt.executeQuery();
        return sttmnt.getResultSet();
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException {
        ResultSet rs = getSubjectResultSetById(subjectId);
        if(!rs.next())
            return null;

        return subjectFromResultSetByNames(rs);
    }

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException {
        ResultSet rs = getSubjectResultSetById(subjectId);
        if(!rs.next())
            return null;

        return subjectFromResultSetByNumbers(rs);
    }

    public static void deleteSubjects() throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("DELETE FROM trainees");
        sttmnt.executeUpdate();
    }

    public static void insertSchool(School school) throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("INSERT INTO schools values(null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        sttmnt.setString(1, school.getName());
        sttmnt.setInt(2, school.getYear());
        sttmnt.executeUpdate();
        ResultSet rs = sttmnt.getGeneratedKeys();
        rs.next();
        school.setId(rs.getInt(1));
    }

    private static ResultSet getSchoolResultSet(int schoolId) throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("SELECT * FROM schools WHERE id = ?");
        sttmnt.setInt(1, schoolId);
        sttmnt.executeQuery();
        return sttmnt.getResultSet();
    }

    private static School schoolFromResultSetByNames(ResultSet rs) throws SQLException {
        return new School(rs.getInt("id"), rs.getString("name"), rs.getInt("year"));
    }

    private static School schoolFromResultSetByNumbers(ResultSet rs) throws SQLException {
        return new School(rs.getInt(1), rs.getString(2), rs.getInt(3));
    }

    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException {
        ResultSet rs = getSchoolResultSet(schoolId);
        if (!rs.next())
            return null;
        return schoolFromResultSetByNames(rs);
    }

    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException {
        ResultSet rs = getSchoolResultSet(schoolId);
        if (!rs.next())
            throw new SQLException();
        return schoolFromResultSetByNumbers(rs);
    }

    public static void deleteSchools() throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("DELETE FROM schools");
        sttmnt.executeUpdate();
    }

    public static void insertGroup(School school, Group group) throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("INSERT INTO groups values(null, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        sttmnt.setString(1, group.getName());
        sttmnt.setString(2, group.getRoom());
        sttmnt.setInt(3, school.getId());
        sttmnt.executeUpdate();
        ResultSet rs = sttmnt.getGeneratedKeys();
        rs.next();
        group.setId(rs.getInt(1));
    }

    private static void addGroupsToSchool(School school) throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("SELECT * FROM groups WHERE schoolId = ?");
        sttmnt.setInt(1, school.getId());
        sttmnt.executeQuery();
        ResultSet rs = sttmnt.getResultSet();
        while(rs.next()) {
            school.addGroup(new Group(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
    }

    public static School getSchoolByIdWithGroups(int id) throws SQLException {
        School school = getSchoolByIdUsingColNumbers(id);
        addGroupsToSchool(school);
        return school;
    }

    public static List<School> getSchoolsWithGroups() throws SQLException {
        PreparedStatement sttmnt = JdbcUtils.getConnection().prepareStatement("SELECT * FROM schools JOIN groups ON schoolId = schools.id ORDER BY schools.id");
        sttmnt.executeQuery();
        ResultSet rs = sttmnt.getResultSet();
        List<School> schools = new ArrayList<>();
        while(rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            if (schools.size() == 0 || schools.get(schools.size() - 1).getId() != rs.getInt(1)) {
                schools.add(new School(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            schools.get(schools.size() - 1).addGroup(new Group(rs.getInt(4), rs.getString(5), rs.getString(6)));
        }
        return schools;
    }
}
