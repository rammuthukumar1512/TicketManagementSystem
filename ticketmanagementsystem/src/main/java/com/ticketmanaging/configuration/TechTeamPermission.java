package com.ticketmanaging.configuration;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ticketmanaging.dto.CheckAuthorityDto;
import com.ticketmanaging.dto.CheckTechAuthorityDto;
import com.ticketmanaging.repository.EmployeeRepository;
import com.ticketmanaging.repository.TechTeamRepository;

import jakarta.servlet.http.HttpServletRequest;

@Component("techTeamPermission")
public class TechTeamPermission {
	@Autowired
	HttpServletRequest servletRequest;
	@Autowired
	ApplicationContext context;
	@Autowired
	TechTeamRepository techRepo;
	
	public boolean isAuthorize() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		List<CheckTechAuthorityDto> userListData = techRepo.CheckAuthority(userName);
		Stream<CheckTechAuthorityDto> userData = userListData.stream();
		if(servletRequest.getMethod().equals("PATCH") && (userListData.stream().anyMatch(v->v.getRole() == 1) || userListData.stream().anyMatch(v->v.getRole() == 2))) {
	 	        return userData.anyMatch(v->v.getAuthority().equals("auth.update"));
		}
		else if(servletRequest.getMethod().equals("GET")){
			System.out.println("get called" +userListData.stream().anyMatch(v->v.getRole() == 2));
			return userListData.stream().anyMatch(v->v.getRole() == 1) || userListData.stream().anyMatch(v ->v.getRole() == 2);
		}
		
		return false;
	}
}
