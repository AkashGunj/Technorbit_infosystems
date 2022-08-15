package com.technorbit.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.technorbit.dao.Student_RegistrationRepository;

import com.technorbit.entities.Stud_Registration;

@Controller
public class Registration_cntr {

	@Autowired
	private Student_RegistrationRepository userRepository;
	
	@GetMapping("/test")
	@ResponseBody
	public String test()
	{
		Stud_Registration student=new Stud_Registration();
		student.setSname("Akash");
		userRepository.save(student); 
		return "working";
	}
}
