package com.student.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.student.exception.SchoolException;
import com.student.model.School;
import com.student.model.Student;
import com.student.repository.SchoolRepository;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	SchoolRepository schoolRepository;

	@Override
	public School createSchool(School school) {
		List<Student> students = school.getStudents();
		if (!org.springframework.util.CollectionUtils.isEmpty(students)) {
			for (Student student : students) {
				student.setSchool(school);
			}
		}
		school = schoolRepository.save(school);
		return school;
	}

	@Override
	public School updateSchool(School school) throws SchoolException {
		//validate school present or not
		getSchoolById(school.getId());
		school = schoolRepository.save(school);
		return school;
	}

	@Override
	public void deleteSchool(long id) throws SchoolException {
		try {
			if(schoolRepository.existsById(id))
				schoolRepository.deleteById(id);
		} catch (Exception e) {
			throw new SchoolException("School not deleted");
		}
	}

	@Override
	public School getSchoolById(long id) throws SchoolException {
		Optional<School> school = schoolRepository.findById(id);
		if (!school.isPresent()) {
			throw new SchoolException("School not found");
		}
		return school.get();
	}

	@Override
	public School updateSchool(Map<String, String> update, Long id) throws SchoolException {
		School school = getSchoolById(id);
		boolean isDataupdated = false;
		if (!StringUtils.isEmpty(update.get("name"))) {
			school.setName(update.get("name"));
			isDataupdated = true;
		}
		if(isDataupdated)
			school = schoolRepository.save(school);
		else
			new SchoolException("No change in school info");
		return school;
	}

	@Override
	public Page<School> getAllSchool(Pageable page) {
		Page<School> users = schoolRepository.findAll(page);
		return users;
	}

}
