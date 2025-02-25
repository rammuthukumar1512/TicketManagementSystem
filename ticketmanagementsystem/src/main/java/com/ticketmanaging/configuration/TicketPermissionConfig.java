package com.ticketmanaging.configuration;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ticketmanaging.dto.CheckAuthorityDto;
import com.ticketmanaging.repository.EmployeeRepository;

import jakarta.servlet.http.HttpServletRequest;

@Component("ticketPermission")
public class TicketPermissionConfig {
	
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
		if(servletRequest.getRequestURI().equals("/ticket/update") && (userListData.stream().anyMatch(v->v.getRole() == 1) || userListData.stream().anyMatch(v->v.getRole() == 2) || userListData.stream().anyMatch(v->v.getRole() == 3))) {
	 	        return userListData.stream().anyMatch(v->v.getAuthority().equals("auth.update"));
		}
		else if(servletRequest.getRequestURI().equals("/ticket/addAuthority")) {
			    return (userListData.stream().anyMatch(v->v.getRole() == 1));
		}
		else if(servletRequest.getRequestURI().equals("/ticket/removeAuthority") && (userListData.stream().anyMatch(v->v.getRole() == 1))) {
		    return userListData.stream().anyMatch(v->v.getAuthority().equals("auth.delete"));
	    }
		else if(servletRequest.getRequestURI().equals("/ticket/viewTickets")) {
			
		    return userListData.stream().anyMatch(v->v.getAuthority().equals("auth.view")) && (userListData.stream().anyMatch(v->v.getRole() == 1) || userListData.stream().anyMatch(v->v.getRole() == 2));
	    }
		return false;
	}
}
