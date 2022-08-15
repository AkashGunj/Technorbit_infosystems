package com.technorbit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.technorbit.entities.ContactUs;
import com.technorbit.entities.Course;
import com.technorbit.entities.Payment;
import com.technorbit.entities.Stud_Registration;

public interface Payment_Repository extends JpaRepository<Payment,Integer> {
	
	
	@Override
	List<Payment> findAll();
	

}



