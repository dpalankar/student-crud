package com.student.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.student.exception.StudentException;
import com.student.model.Student;
import com.student.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@GetMapping("/")
	public ResponseEntity<String> defaultMethod(){
		return new ResponseEntity<>("Welcome ", HttpStatus.OK);
	}
	
	@GetMapping("/welcome/{name}")
	public ResponseEntity<String> welcomeMethod(@PathVariable String name){
		return new ResponseEntity<>("Welcome "+name, HttpStatus.OK);
	}
	
	@PostMapping("/student")
	public ResponseEntity<Student> createStudent(@RequestBody Student student){
		student = studentService.createStudent(student);
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}
	
	@PutMapping("/student/update")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) throws StudentException{
		student = studentService.updateStudent(student);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@PatchMapping("/student/{id}")
	public ResponseEntity<Student> updatePartial(@RequestBody Map<String, String> update, @PathVariable Long id) throws StudentException {
		Student student = studentService.updatePartial(update,id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable long id) throws StudentException{
		studentService.deleteStudent(id);
		return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable long id) throws StudentException{
		Student student =  studentService.getStudentById(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@GetMapping("/students")
	public ResponseEntity<Page<Student>> getAllStudents(@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) throws StudentException{
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return new ResponseEntity<>(studentService.getAllStudents(pageable), HttpStatus.OK);
	}
}
