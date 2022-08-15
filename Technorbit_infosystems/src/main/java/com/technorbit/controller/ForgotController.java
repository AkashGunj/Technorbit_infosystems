package com.technorbit.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.technorbit.dao.Student_RegistrationRepository;
import com.technorbit.entities.Stud_Registration;


@Controller
public class ForgotController {
	 Random random=new Random(1000);
	
	 @Autowired
	 private BCryptPasswordEncoder encoader;
	 
	 @Autowired
	 private EmailService emailservice;
	 
	 @Autowired
	 private Student_RegistrationRepository repository;
	 
	@RequestMapping("/forgot")
	public String openEmailForm()
	{
		return "forgot_email_form";
	}
	
	@PostMapping("/send-otp")
	public String sendOTP(@RequestParam("email")String email ,HttpSession session)
	{
		 System.out.println("Email"+email);
		 Stud_Registration stud_mail = this.repository.getUserMail(email);
			
			if(stud_mail==null)
			{
				session.setAttribute("message","User does not exist with this email !!");
				return "forgot_email_form";
			}
		
		int otp= random.nextInt(99999999);
		System.out.println("otp"+otp);
		
		String subject="OTP from TechNorbit Infosystems";
		String message=""+"<div style ='border:1px solid #e2e2e2;padding:20px'>"+"<h1>"+"OTP is"+"<b>"+otp+"</b>"+"</h1>"+"</div>";
		String to=email;
		boolean flag=this.emailservice.sendEmail(subject,message,to);
		if(flag==true)
		{
			session.setAttribute("myotp", otp);
			session.setAttribute("email",email);
			return "verify_otp";
		}
		else
		{
			session.setAttribute(message,"Check Your email id !!");
			return "forgot_email_form";
		}
		}
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam("otp")int otp,HttpSession session)
	{
		int myOtp=(int)session.getAttribute("myotp");
		String email=(String) session.getAttribute("email");
		if(myOtp==otp)
		{
			Stud_Registration stud_mail = this.repository.getUserMail(email);
			
			if(stud_mail==null)
			{
				session.setAttribute("message","User does not exist with this email !!");
				return "forgot_email_form";
			}
			else
			{
				
				return "password_change_password";
			}
			
			
		}else
		{
			session.setAttribute("message","You have entered wrong otp");
			return "verify_otp";
		}
		
	}
	//chage pass
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("txtpassword")String password, Object stud,HttpSession session)
	{
		String email=(String)session.getAttribute("email");
		Stud_Registration stud_pass = this.repository.getUserMail(email);
		stud_pass.setPassword(this.encoader.encode(password));
		this.repository.save(stud_pass);
		session.setAttribute("message","Your Password is change successfully");
		return "password_change_password";
		
	}
		
		
	}

