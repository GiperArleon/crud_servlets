package com.app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.app.config.PostgreSQL;
import com.app.model.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentDaoImpl implements StudentDao {

    @Override
    public void addStudent(Student student) {
        try(Connection conn = PostgreSQL.getConnection()) {
            if(conn == null) {
                log.error("conn is null");
                return;
            }
            String query = "insert into student (firstName, lastName, course, year) values (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getCourse());
            preparedStatement.setInt(4, student.getYear());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            log.error(e.toString());
        }
    }

    @Override
    public void deleteStudent(int studentId) {
        try(Connection conn = PostgreSQL.getConnection()) {
            if(conn == null) {
                log.error("conn is null");
                return;
            }
            String query = "delete from student where studentId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            log.error(e.toString());
        }
    }

    @Override
    public void updateStudent(Student student) {
        try(Connection conn = PostgreSQL.getConnection()) {
            if(conn == null) {
                log.error("conn is null");
                return;
            }
            String query = "update student set firstName=?, lastName=?, course=?, year=? where studentId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getCourse());
            preparedStatement.setInt(4, student.getYear());
            preparedStatement.setInt(5, student.getStudentId());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            log.error(e.toString());
        }
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try(Connection conn = PostgreSQL.getConnection()) {
            if(conn == null) {
                log.error("conn is null");
                return students;
            }
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from student");
            while(resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt("studentId"));
                student.setFirstName(resultSet.getString("firstName"));
                student.setLastName(resultSet.getString("lastName"));
                student.setCourse(resultSet.getString("course"));
                student.setYear(resultSet.getInt("year"));
                students.add(student);
            }
        } catch(Exception e) {
            log.error(e.toString());
        }
        return students;
    }

    @Override
    public Student getStudentById(int studentId) {
        Student student = new Student();
        try(Connection conn = PostgreSQL.getConnection()) {
            String query = "select * from student where studentId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                student.setStudentId(resultSet.getInt("studentId"));
                student.setFirstName(resultSet.getString("firstName"));
                student.setLastName(resultSet.getString("LastName"));
                student.setCourse(resultSet.getString("course"));
                student.setYear(resultSet.getInt("year"));
            }
        } catch(SQLException e) {
            log.error(e.toString());
        }
        return student;
    }
}
