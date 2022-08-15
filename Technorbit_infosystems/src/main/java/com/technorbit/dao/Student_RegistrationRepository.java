package com.technorbit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.technorbit.entities.Stud_Registration;
import com.technorbit.entities.Subscribe;

public interface Student_RegistrationRepository extends JpaRepository<Stud_Registration,Integer> {
	
	@Query("select u from Stud_Registration u where u.username = :username")
	public Stud_Registration getUserName(@Param("username") String username); 
	
	@Query("select u from Stud_Registration u where u.mailid = :mailid")
	public Stud_Registration getUserMail(@Param("mailid") String mailid); 
	
	
}



