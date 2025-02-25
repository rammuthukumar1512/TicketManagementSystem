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

@Component("employeePermission")
public class PermissionConfig {
	
	@Autowired
	HttpServletRequest servletRequest;
	@Autowired
	ApplicationContext context;
	@Autowired
	EmployeeRepository employeeRepo;
		public boolean isAuthorize() {
			   
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			List<CheckAuthorityDto> userListData = employeeRepo.CheckAuthority(userName);
			Stream<CheckAuthorityDto> userData = userListData.stream();
			 if(servletRequest.getMethod().equals("GET")){
				return userListData.stream().anyMatch(v->v.getRole() == 1) || userListData.stream().anyMatch(v ->v.getRole() == 2) && 
						userListData.stream().anyMatch(v->v.getAuthority().equals("auth.view"));
			}
			
			return false;
		}
	}
	
