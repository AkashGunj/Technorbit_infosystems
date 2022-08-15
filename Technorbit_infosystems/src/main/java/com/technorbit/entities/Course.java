package com.technorbit.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="courses")
public class Course{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;	
	private String name;
	private int duration;
	private int fees;
	
	
	 @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	    private Set<Stud_Registration> student = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	

}
