package com.technorbit.entities;

import javax.persistence.*;

@Entity
@Table(name="Contactus")
public class ContactUs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String contactName;
	private String contactemail;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactemail() {
		return contactemail;
	}
	public void setContactemail(String contactemail) {
		this.contactemail = contactemail;
	}
	public String getContactquery() {
		return contactquery;
	}
	public void setContactquery(String contactquery) {
		this.contactquery = contactquery;
	}
	private String contactquery;
		
}
