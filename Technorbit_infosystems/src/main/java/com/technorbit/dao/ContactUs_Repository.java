package com.technorbit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.technorbit.entities.ContactUs;
import com.technorbit.entities.Stud_Registration;

public interface ContactUs_Repository extends JpaRepository<ContactUs,Integer> {
	@Override
	List<ContactUs> findAll();
	
	
}



