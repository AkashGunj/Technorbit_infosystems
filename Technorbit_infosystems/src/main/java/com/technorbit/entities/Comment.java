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
@Table(name="comment")
public class Comment{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;	
	private String stud_id;
	private String stud_date;
	private String stud_comment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStud_id() {
		return stud_id;
	}
	public void setStud_id(String stud_id) {
		this.stud_id = stud_id;
	}
	public String getStud_date() {
		return stud_date;
	}
	public void setStud_date(String stud_date) {
		this.stud_date = stud_date;
	}
	public String getStud_comment() {
		return stud_comment;
	}
	public void setStud_comment(String stud_comment) {
		this.stud_comment = stud_comment;
	}
	
	
	
	
	

	

}
