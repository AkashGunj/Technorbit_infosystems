package com.technorbit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.technorbit.entities.Comment;


public interface Comment_Repository extends JpaRepository<Comment,Integer> {
	
	@Query("from Comment as c where c.stud_id =:name ")
	public List<Comment> findComment(@Param("name")String name);
	
	@Override
	List<Comment> findAll();
	
	
}



