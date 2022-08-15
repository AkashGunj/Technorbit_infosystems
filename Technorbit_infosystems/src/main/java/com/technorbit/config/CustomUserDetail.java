package com.technorbit.config;

import java.util.Collection;
import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.technorbit.entities.Stud_Registration;



public class CustomUserDetail implements UserDetails {
	
	private Stud_Registration stud_reg;

	public CustomUserDetail(Stud_Registration stud_reg) {
		super();
		this.stud_reg = stud_reg;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority =new SimpleGrantedAuthority(stud_reg.getRole());
		
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		System.out.println(stud_reg.getPassword());
		return stud_reg.getPassword();
	}

	@Override
	public String getUsername() {
		System.out.println(stud_reg.getPassword());
		return stud_reg.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	
	

}
