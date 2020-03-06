package com.student.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.student.exception.StudentException;
import com.student.model.Student;
import com.student.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public Student createStudent(Student student) {
		student = studentRepository.save(student);
		return student;
	}

	@Override
	public Student updateStudent(Student student) throws StudentException {
		//validate student present or not
		getStudentById(student.getId());
		student = studentRepository.save(student);
		return student;
	}

	@Override
	public void deleteStudent(long id) throws StudentException {
		try {
			if(studentRepository.existsById(id))
				studentRepository.deleteById(id);
		} catch (Exception e) {
			throw new StudentException("Student not deleted");
		}
	}

	@Override
	public Student getStudentById(long id) throws StudentException {
		Optional<Student> student = studentRepository.findById(id);
		if (!student.isPresent()) {
			throw new StudentException("Student not found");
		}
		return student.get();
	}

	@Override
	public Student updatePartial(Map<String, String> update, Long id) throws StudentException {
		Student student = getStudentById(id);
		boolean isDataupdated = false;
		if (!StringUtils.isEmpty(update.get("name"))) {
			student.setName(update.get("name"));
			isDataupdated = true;
		}
		if (!StringUtils.isEmpty(update.get("email"))) {
			student.setEmail(update.get("email"));
			isDataupdated = true;
		}
		if (!StringUtils.isEmpty(update.get("address"))) {
			student.setAddress(update.get("address"));
			isDataupdated = true;
		}
		if(isDataupdated)
			student = studentRepository.save(student);
		else
			new StudentException("No change in student info");
		return student;
	}

	@Override
	public Page<Student> getAllStudents(Pageable page) {
		Page<Student> users = studentRepository.findAll(page);
		return users;
	}

}
