package com.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import com.app.model.Student;

public class StudentDaoImpl implements StudentDao{
    private int currentId = 0;
	private static final ConcurrentHashMap<Integer, Student> storage = new ConcurrentHashMap<>();

	@Override
	public void addStudent(Student student) {
        currentId += 2;
		student.setStudentId(currentId);
		storage.put(student.getStudentId(), student);
	}

	@Override
	public void deleteStudent(int studentId) {
		storage.remove(studentId);
	}

	@Override
	public void updateStudent(Student student) {
		storage.put(student.getStudentId(), student);
	}

	@Override
	public List<Student> getAllStudents() {
		return new ArrayList<>(storage.values());
	}

	@Override
	public Student getStudentById(int studentId) {
		return storage.get(studentId);
	}
}
