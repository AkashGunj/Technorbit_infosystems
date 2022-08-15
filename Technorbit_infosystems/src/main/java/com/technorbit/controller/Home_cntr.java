package com.technorbit.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.technorbit.dao.ContactUs_Repository;
import com.technorbit.dao.Course_Repository;
import com.technorbit.dao.Student_RegistrationRepository;
import com.technorbit.dao.Subscribe_Repository;
import com.technorbit.entities.ContactUs;
import com.technorbit.entities.Course;
import com.technorbit.entities.Stud_Registration;
import com.technorbit.entities.Subscribe;
import com.technorbit.helper.Message;

@Controller
public class Home_cntr {
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;
	
	@Autowired
	Student_RegistrationRepository repository;
	
	@Autowired
	Course_Repository css;
	
	@Autowired
	Subscribe_Repository subscribe;
	
	@Autowired
	ContactUs_Repository contactus;
	

	@RequestMapping("/demo")
	@ResponseBody
	public String demo()
	{
		Course c=new Course();
		c.setName("Akash");
		css.save(c);
		return "sucess";
	}
	
	@RequestMapping("/")
	public String home()
	{
		
		
		return "home";
	}
	
	
	@RequestMapping("/register1")
	public String register(Model model)
	{
		model.addAttribute("stud_reg",new Stud_Registration());
		return "register1";
	}
	
	//handler for subscribing visitor.
	@RequestMapping("/do_suscribe")
	public String suscribe(@ModelAttribute("subscribeabc") Subscribe subscribeabc,@RequestParam("txtmail")String email,HttpSession session,Model model)
	{
		try 
		{
		
		Subscribe s= new Subscribe();
		String dbemail;

		if(email.isEmpty())
		{
			System.out.println("Please Enter Your Email ID first to Subscribe !");
		
			throw new Exception("Please Enter Your Email ID first to Subscribe !");
		}
		Subscribe s1=subscribe.getEmail(email);
		
		
		if(s1!=null) 
		{
		 dbemail=s1.getEmail();
		 System.out.println(dbemail);
		 if(email.equals(dbemail))
			{
				System.out.println("you are Already Registered With us :) !");
					throw new Exception("you are Already Registered With us :) !");
			
			}

		}
		else
		{
			s.setEmail(email);
			subscribe.save(s);
			model.addAttribute("subscribeabc",new Subscribe());
			session.setAttribute("messsage",new Message("Successfully Subscribed", "alert-Success"));
		}
		
				System.out.println(email);
		/*	if(s1==null || dbemail.isEmpty())
			{
				s.setEmail(email);
				//System.out.println("if");
				subscribe.save(s);
				//return "test";
				model.addAttribute("subscribeabc",new Subscribe());
				
				session.setAttribute("messsage",new Message("Successfully Subscribed", "alert-Success"));
				
			}
			else
			{
				
				else
				{
					s.setEmail(email);
					//System.out.println("if");
					subscribe.save(s);
					//return "test";
					model.addAttribute("subscribeabc",new Subscribe());
					
					session.setAttribute("messsage",new Message("Successfully Subscribed", "alert-Success"));
					
				}
			}
				/*if(!email.isEmpty())
				{
					if(email==dbemail)
					{
						System.out.println("you are Already Registered With us :) !");
						throw new Exception("you are Already Registered With us :) !");
					}
					else
					{
				s.setEmail(email);
				System.out.println("if");
				subscribe.save(s);
				//return "test";
				model.addAttribute("subscribeabc",new Subscribe());
				
				session.setAttribute("messsage",new Message("Successfully Subscribed", "alert-Success"));
					}
				}
				//else
				//{
				//System.out.println("Please Enter Your Email ID first to Subscribe !");
				//throw new Exception("Please Enter Your Email ID first to Subscribe !");
				
				}*/
			
			
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			model.addAttribute("subscribeabc",subscribeabc);
			session.setAttribute("messsage",new Message(e.getMessage(), "alert-danger"));
			
		}
		
		return "home";
		
	}
	
	
	//handler for registering user
	@RequestMapping(value = "/do_register",method=RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("stud_reg") Stud_Registration stud_reg,BindingResult result1,@RequestParam(value="agreement",defaultValue = "false")boolean agreement,@RequestParam(value="txtbdate")String date,@RequestParam(value="gender")String gender,Model model,HttpSession session)
	{
		try {
			
			if(!agreement)
			{
				System.out.println("you are not aggred with terms and conditions !");
				throw new Exception("you are not aggred with terms and conditions !");
			}
			
			if(result1.hasErrors())
			{
				System.out.println("adddddddddddd");
				System.out.println("error"+ result1.toString());
				model.addAttribute("stud_reg",stud_reg);
				return "register1";
			}
			stud_reg.setRole("ROLE_USER");
			stud_reg.setEnabled(true);
			stud_reg.setDate(date);
			stud_reg.setGender(gender);
			stud_reg.setPassword(PasswordEncoder.encode(stud_reg.getPassword()));
			
			System.out.println("agreement"+agreement);
			System.out.println("Stud_reg"+stud_reg);
			System.out.println("date"+date);
			
			
						
						
						Stud_Registration result=this.repository.save(stud_reg);
						model.addAttribute("stud_reg",new Stud_Registration());
						
						session.setAttribute("messsage",new Message("Successfully registered", "alert-Success"));
			
			
			
			
			return "register1";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("stud_reg",stud_reg);
			session.setAttribute("messsage",new Message("Something vent wrong"+e.getMessage(), "alert-danger"));
			return "register1";
		}
		
		
	}
	
	//handler for contact us.
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
						
						return "contactus";
						
					}
					
					


	//handler for custom login
	@GetMapping("/signin")
	public String customLogin()
	{
		return "login";
	}
	
	@GetMapping("/courses")
	public String courses()
	{
		return "courses";
	}
	
	@GetMapping("/c")
	public String c()
	{
		return "c";
	}
	
	@GetMapping("/cpp")
	public String cpp()
	{
		return "cpp";
	}
	@GetMapping("/java")
	public String java()
	{
		return "java";
	}
	
	@GetMapping("/ds")
	public String ds()
	{
		return "ds";
	}
	
	@GetMapping("/python")
	public String python()
	{
		return "python";
	}
	
	@GetMapping("/micro")
	public String micro()
	{
		return "micro";
	}

	@GetMapping("/html")
	public String html()
	{
		return "html";
	}
	
	@GetMapping("/about")
	public String about()
	{
		return "about";
	}
	
	@GetMapping("/contactus")
	public String contactus()
	{
		return "contactus";
	}
	
	@GetMapping("/Admin")
	public String admin()
	{
		return "admin";
	}
	
	
	
	
	
	@GetMapping("/gallery")
	public String gallery()
	{
		return "gallery";
	}
	
	
}
