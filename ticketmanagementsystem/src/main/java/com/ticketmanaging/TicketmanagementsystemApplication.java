package com.ticketmanaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.ticketmanaging.repository.EmployeeRepository;

@SpringBootApplication
public class TicketmanagementsystemApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TicketmanagementsystemApplication.class, args);
	}

}
