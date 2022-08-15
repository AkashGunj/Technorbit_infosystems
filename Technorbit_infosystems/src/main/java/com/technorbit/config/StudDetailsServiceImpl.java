package com.technorbit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.technorbit.dao.Student_RegistrationRepository;
import com.technorbit.entities.Stud_Registration;

public class StudDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private Student_RegistrationRepository student_RegistrationRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Stud_Registration stud_reg = student_RegistrationRepository.getUserName(username);
	    
		if(stud_reg==null) {
			throw new UsernameNotFoundException("could not found user");
		}
		CustomUserDetail customUserDetail=new CustomUserDetail(stud_reg);
		
		return customUserDetail;
	}

}
