package com.technorbit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.technorbit.entities.Stud_Registration;
import com.technorbit.entities.Subscribe;

public interface Subscribe_Repository extends JpaRepository<Subscribe,Integer> {
	
	@Query("select u from Subscribe u where u.email = :email")
	public Subscribe getEmail(@Param("email") String email);
	
	@Override
	List<Subscribe> findAll();
	
	@Query("select u from Subscribe u")
	public List<String> getEmail();
}



