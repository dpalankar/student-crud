package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.model.School;

public interface SchoolRepository extends JpaRepository<School, Long>{

}
