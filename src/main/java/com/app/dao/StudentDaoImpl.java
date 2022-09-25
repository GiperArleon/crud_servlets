package com.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import com.app.model.Student;

public class StudentDaoImpl implements StudentDao {
    private static final int STEP = 4;
    private final AtomicInteger currentId = new AtomicInteger(0);
	private static final ConcurrentHashMap<Integer, Student> storage = new ConcurrentHashMap<>();

	@Override
	public void addStudent(Student student) {
		student.setStudentId(currentId.addAndGet(STEP));
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

    @Override
    public void clearAll() {
        storage.clear();
    }
}
