package com.technorbit.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.net.httpserver.HttpsConfigurator;
import com.technorbit.dao.Comment_Repository;
import com.technorbit.dao.ContactUs_Repository;
import com.technorbit.dao.Course_Repository;
import com.technorbit.dao.Payment_Repository;
import com.technorbit.dao.Student_RegistrationRepository;
import com.technorbit.entities.Comment;
import com.technorbit.entities.ContactUs;
import com.technorbit.entities.*;
import com.technorbit.helper.Message;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private Student_RegistrationRepository repository;
	
	@Autowired
	private Comment_Repository comment_repo;
	
	@Autowired
	private Course_Repository course_repo;
	
	@Autowired
	private Payment_Repository payment_repo;
	
	@Autowired
	private ContactUs_Repository contactus;
	
	
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principle)
	{
		String username=principle.getName();
		System.out.println("USER"+username);
		
					Stud_Registration student = repository.getUserName(username);
					String name=student.getSname();
					if(name.contains(" "))
					{
						name=name.substring(0,name.indexOf(" "));
						System.out.println(name);
					}
					model.addAttribute("student",name);
					
		return "normal/user_dashboard";
	}
	@RequestMapping("/quiz")
	public String quiz()
	{
		return "normal/quiz";
	}
	
	
	@RequestMapping("/quiz_result")
	public String quizResult()
	{
		System.out.println("i am akash");
		
		return "normal/test";
	}
	
	
	
	
	@RequestMapping("/courses")
	public String courses()
	{
		return "normal/courses";
	}
	
	@GetMapping("/c")
	public String c()
	{
		return "normal/c";
	}
	
	@GetMapping("/gallery")
	public String gallery()
	{
		return "normal/gallery";
	}
	
	@GetMapping("/enrollc")
	public String enroll()
	{
		return "normal/enrollc";
	}

	@GetMapping("/about")
	public String about()
	{
		return "normal/about";
	}
	

	@GetMapping("/contactus")
	public String contactus()
	{
		return "normal/contactus";
	}
	
	@RequestMapping(value = "/do_contactus",method=RequestMethod.POST)
	public String contactus(@ModelAttribute("contact_us") ContactUs contact_us,HttpSession session,Model model)
	{
		try 
		{
				
				ContactUs result=this.contactus.save(contact_us);
				//System.out.println(name+"\n");
				model.addAttribute("contact_us",new ContactUs());
				
				session.setAttribute("messsage",new Message("Thank you for reaching to us", "alert-Success"));
	
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			model.addAttribute("contact_us",contact_us);
			session.setAttribute("messsage",new Message(e.getMessage(), "alert-danger"));
			
		}
		
		return "normal/contactus";
		
	}
	
	
	
	@GetMapping("/python")
	public String python()
	{
		return "normal/python";
	}
	
	@GetMapping("/cpp")
	public String cpp()
	{
		return "normal/cpp";
	}
	
	@GetMapping("/java")
	public String java()
	{
		return "normal/java";
	}
	
	@GetMapping("/ds")
	public String ds()
	{
		return "normal/ds";
	}
	
	@RequestMapping(value = "/do_enroll",method=RequestMethod.POST)
	public String enrollCourse(@RequestParam(value="agreement",defaultValue = "false")boolean agreement,Model model,HttpSession session)
	{
		try {
			
			if(!agreement)
			{
				System.out.println("you are not aggred with terms and conditions !");
				throw new Exception("you are not aggred with terms and conditions !");
			}
			if(agreement==true)
			{
			System.out.println("sucess");
			return "normal/payment";
			//session.setAttribute("messsage",new Message("Successfully Agreed terms and conditions", "alert-Success"));
			}		
				
					
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			session.setAttribute("messsage",new Message("Something vent wrong"+e.getMessage(), "alert-danger"));
			return "normal/enrollc";
		}
		
		return "normal/enrollc";
		
	}
	@RequestMapping(value = "/do_payment",method=RequestMethod.POST)
	public String Paymentc(@ModelAttribute("payment")Payment payment,Principal principle)
	{
		String username=principle.getName();
		Stud_Registration student = repository.getUserName(username);
		int name=student.getId();
		
		
		payment.setCourse_id(1);
		payment.setStud_id(name);
		String id=payment.getTransactionId();
		String date=payment.getTransactionDate();
		int amt=payment.getFee_paid();
		System.out.println(id);
		System.out.println(date);
		System.out.println(""+amt);
		payment_repo.save(payment);
		
		
		return "normal/test";
	}
	@GetMapping("/comment")
	public String comment(Principal principle,Model model)
	{
		String username=principle.getName();
		//System.out.println(username);
		
		Stud_Registration student = repository.getUserName(username);
		String name=""+student.getId();
		//System.out.println(name);
		
		List<Comment> abcd=comment_repo.findComment(name);
		/*for(int i=0;i<abcd.size();i++)
		{
			String id=abcd.get(i).getStud_id();
			System.out.println(id);
			String xd=abcd.get(i).getStud_comment();
			System.out.println(xd);
			
		}*/
		
		model.addAttribute("akash",abcd);
		
		
				
		return "normal/comment";
	}

	

}
