package com.ticketmanaging.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ticketmanaging.repository.EmployeeLoginDataRepo;
import com.ticketmanaging.repository.TechTeamRepository;

@Service
public class UserDataService implements UserDetailsService{
	@Autowired
    EmployeeLoginDataRepo employeeDataRepo; 
	@Autowired
    TechTeamRepository techTeamRepo; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BasicLoginData user = null;
		if(username.startsWith("tech")) {
			user = techTeamRepo.findByUsername(username);
		}
		else {
			user = employeeDataRepo.findByUsername(username);
		}
		 
		if(user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
        String role = user.getRole(); //Get the role from the user object.
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role)); // Add "ROLE_" prefix!
        System.out.println(user.getPassword());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);// Authorities are set here!
	}
	
}

