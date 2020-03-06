package com.student.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.student.exception.StudentException;
import com.student.model.Student;

public interface StudentService {

	Student createStudent(Student student);
	
	Student updateStudent(Student student) throws StudentException;
	
	Student getStudentById(long id) throws StudentException;

	void deleteStudent(long id) throws StudentException;

	Student updatePartial(Map<String, String> update, Long id) throws StudentException;

	Page<Student> getAllStudents(Pageable page);
	
}
