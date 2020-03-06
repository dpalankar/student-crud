package com.student.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.student.exception.SchoolException;
import com.student.model.School;

public interface SchoolService {

	School createSchool(School school);
	
	School updateSchool(School school) throws SchoolException;
	
	School getSchoolById(long id) throws SchoolException;

	void deleteSchool(long id) throws SchoolException;

	School updateSchool(Map<String, String> update, Long id) throws SchoolException;

	Page<School> getAllSchool(Pageable page);
	
}
