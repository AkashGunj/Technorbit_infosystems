package com.technorbit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.technorbit.entities.Course;
import com.technorbit.entities.Stud_Registration;

public interface Course_Repository extends JpaRepository<Course,Integer> {
	
	@Query("select u from Course u where u.name = :name")
	public Course getCourseId(@Param("name") String name);
	
	

}



