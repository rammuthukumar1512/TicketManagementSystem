package com.ticketmanaging.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ticketmanaging.dto.ErrorHandlingDto;
import com.ticketmanaging.exception.EmployeeFetchException;
import com.ticketmanaging.exception.EmployeeNotRegisterException;
import com.ticketmanaging.exception.FetchTicketException;
import com.ticketmanaging.exception.TechTeamControllException;
import com.ticketmanaging.exception.TicketControllerException;
import com.ticketmanaging.exception.TicketRaisingFailException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
      
	@ExceptionHandler(EmployeeNotRegisterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandlingDto EmployeeNotRegisterationException(EmployeeNotRegisterException e) {
		ErrorHandlingDto error = new ErrorHandlingDto();
		error.setErrorCode(9001);
		error.setErrorMessage(e.getMessage());
		return error;
	}
	
	@ExceptionHandler(TicketRaisingFailException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandlingDto TicketRaisingException(TicketRaisingFailException e) {
		ErrorHandlingDto error = new ErrorHandlingDto();
		error.setErrorCode(9002);
		error.setErrorMessage(e.getMessage());
		return error;
	}
	
	@ExceptionHandler(EmployeeFetchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandlingDto EmployeeFetchFailedException(EmployeeFetchException e) {
		ErrorHandlingDto error = new ErrorHandlingDto();
		error.setErrorCode(9003);
		error.setErrorMessage(e.getMessage());
		return error;
	}
	
	@ExceptionHandler(FetchTicketException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandlingDto FetchTicketFailException(FetchTicketException e) {
		ErrorHandlingDto error = new ErrorHandlingDto();
		error.setErrorCode(9005);
		error.setErrorMessage(e.getMessage());
		return error;
	}
	
	@ExceptionHandler(TicketControllerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandlingDto TicketControllerFailException(TicketControllerException e) {
		ErrorHandlingDto error = new ErrorHandlingDto();
		error.setErrorCode(9005);
		error.setErrorMessage(e.getMessage());
		return error;
	}
	
	@ExceptionHandler(TechTeamControllException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandlingDto TechTeamControllFailException(TechTeamControllException e) {
		ErrorHandlingDto error = new ErrorHandlingDto();
		error.setErrorCode(9005);
		error.setErrorMessage(e.getMessage());
		return error;
	}

}
