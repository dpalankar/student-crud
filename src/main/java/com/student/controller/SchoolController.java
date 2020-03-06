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

import com.student.exception.SchoolException;
import com.student.model.School;
import com.student.service.SchoolService;

@Controller
public class SchoolController {

	@Autowired
	SchoolService schoolService;
	
	
	@PostMapping("/school")
	public ResponseEntity<School> createSchool(@RequestBody School school){
		school = schoolService.createSchool(school);
		return new ResponseEntity<>(school, HttpStatus.CREATED);
	}
	
	@PutMapping("/school/update")
	public ResponseEntity<School> updateSchool(@RequestBody School school) throws SchoolException{
		school = schoolService.updateSchool(school);
		return new ResponseEntity<>(school, HttpStatus.OK);
	}
	
	@PatchMapping("/school/{id}")
	public ResponseEntity<School> updatePartial(@RequestBody Map<String, String> update, @PathVariable Long id) throws SchoolException {
		School school = schoolService.updateSchool(update,id);
		return new ResponseEntity<>(school, HttpStatus.OK);
	}
	
	@DeleteMapping("/school/{id}")
	public ResponseEntity<String> deleteSchool(@PathVariable long id) throws SchoolException{
		schoolService.deleteSchool(id);
		return new ResponseEntity<>("School deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/school/{id}")
	public ResponseEntity<School> getSchool(@PathVariable long id) throws SchoolException{
		School school =  schoolService.getSchoolById(id);
		return new ResponseEntity<>(school, HttpStatus.OK);
	}
	
	@GetMapping("/schools")
	public ResponseEntity<Page<School>> getAllSchools(@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) throws SchoolException{
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return new ResponseEntity<>(schoolService.getAllSchool(pageable), HttpStatus.OK);
	}
}
