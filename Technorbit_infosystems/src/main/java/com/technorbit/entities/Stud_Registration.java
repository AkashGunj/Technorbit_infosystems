package com.technorbit.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;

@Entity
@Table(name="STUD_REGISTRATION")
public class Stud_Registration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "Name is required !")
	private String sname;
	@Column(length = 500)
	private String mailid;
	private String mobno;
	private String address;
	private String username;
	private String role;
	private boolean enabled;
	private String date;
	private String gender;
	
	 @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	    @JoinTable(name = "students_courses",
	            joinColumns = {
	                    @JoinColumn(name = "student_id", referencedColumnName = "id",
	                            nullable = false, updatable = false)},
	            inverseJoinColumns = {
	                    @JoinColumn(name = "course_id", referencedColumnName = "id",
	                            nullable = false, updatable = false)})
	    private Set<Course> courses = new HashSet<>();
	 
	   
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

	@Override
	public String toString() {
		return "Stud_Registration [id=" + id + ", sname=" + sname + ", mailid=" + mailid + ", mobno=" + mobno
				+ ", address=" + address + ", username=" + username + ", role=" + role + ", enabled=" + enabled
				+ ", date=" + date + ", gender=" + gender + ", password=" + password + "]";
	}



	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getMobno() {
		return mobno;
	}
	public void setMobno(String mobno) {
		this.mobno = mobno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	;
}
