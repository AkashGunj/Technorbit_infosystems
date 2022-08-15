package com.technorbit.controller;

import java.security.Principal;
import java.util.List;
import com.technorbit.entities.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.net.httpserver.HttpsConfigurator;
import com.technorbit.dao.Comment_Repository;
import com.technorbit.dao.ContactUs_Repository;
import com.technorbit.dao.Payment_Repository;
import com.technorbit.dao.Student_RegistrationRepository;
import com.technorbit.dao.Subscribe_Repository;
import com.technorbit.helper.Message;
import com.technorbit.service.EmailService;


@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private Student_RegistrationRepository repository;
	
	
	@Autowired
	private Subscribe_Repository subscribe;
	
	@Autowired
	private ContactUs_Repository contact;
	
	@Autowired
	private EmailSubscriberService service;
	
	@Autowired
	private EmailService service_query;
	
	@Autowired
	private Comment_Repository comment_repo;
	
	@Autowired
	private Payment_Repository payment_repo;
	
	
	
	
	@RequestMapping("/index")
	public String dashboard()
	{
		return "admin/Admin";
	}
	
	
	@RequestMapping("/subscribe")
	public String subscribe(Model model)
	{
		List<Subscribe> xxx=subscribe.findAll();
		
		model.addAttribute("subscribe",xxx);
		
		
				return "admin/subscribe";
	}
	

	@RequestMapping("/registered")
	public String registered(Model model)
	{
		List<Stud_Registration> xxx=repository.findAll();
		
		model.addAttribute("super",xxx);
		
		
				return "admin/RegisteredStudents";
	}
	
	@RequestMapping("/contact_us")
	public String query(Model model)
	{
		List<ContactUs> abcd=contact.findAll();
		for(int i=0;i<abcd.size();i++)
		{
			String email=abcd.get(i).getContactemail();
			System.out.println(email);
		}
		
		model.addAttribute("akash",abcd);
		
		
				return "admin/contact_us";
	}
	
	@RequestMapping("/sendmail")
	public String sendmail(Model model)
	{
		
				return "admin/sendmail";
	}
	
	@RequestMapping(value = "/do_mail",method=RequestMethod.POST)
	public String sendEmail(@RequestParam("txtsubject")String subject ,@RequestParam("file")String file,@RequestParam("text")String text,HttpSession session)
	{
		 
			System.out.println(subject);
			System.out.println(file);
			System.out.println(text);
			List<Subscribe> abcd=subscribe.findAll();
			for(int i=0;i<abcd.size();i++)
			{
				String email=abcd.get(i).getEmail();
				
			
			
		
		
		String to=email;
		boolean flag=this.service.sendAttach(text,file,subject,to);
		if(flag==true)
		{
			System.out.println("successs");
		}
			}
		
		return "admin/sendmail";
		}
	
	@RequestMapping("/comment")
	public String comment(Model model)
	{
		
				return "admin/comment";
	}
	
	@RequestMapping(value = "/do_comment",method=RequestMethod.POST)
	public String comment(@ModelAttribute("comment") Comment comment)
	{
		/*System.out.println(comment.getId());
		System.out.println(comment.getStud_id());
		System.out.println(comment.getStud_date());
		System.out.println(comment.getStud_comment());*/
		Comment result=this.comment_repo.save(comment);
		return "admin/test2";
	}
	
	//To display comment table on webpage
	@RequestMapping("/sendQuery")
	public String sendQuery(Model model)
	{
		
				return "admin/sendQuery";
	}
	
	@RequestMapping(value = "/do_query",method=RequestMethod.POST)
	public String query(@RequestParam("txtemail")String email ,@RequestParam("txtsubject")String subject,@RequestParam("text")String text,HttpSession session)
	{
		System.out.println(email);
		System.out.println(subject);
		System.out.println(text);
		boolean flag=this.service_query.sendEmail(subject, text, email);
		if(flag==true)
		{
			System.out.println("successs");
		}
		
		return "admin/test";
	}

	@RequestMapping("/EnrolledList")
	public String Enrolled(Model model)
	{
		List<Payment> abcd=payment_repo.findAll();
		
		
		model.addAttribute("akash",abcd);
				return "admin/EnrolledList";
	}

}
