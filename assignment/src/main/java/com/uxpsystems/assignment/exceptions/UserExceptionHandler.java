package com.uxpsystems.assignment.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.uxpsystems.assignment.dto.UserFieldError;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
	
	  @ExceptionHandler(UserNotFoundException.class)

	    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {

		  UserFieldError userFieldError = new UserFieldError(new Date(),

	                "Record Not Found",

	                 ex.getMessage());

	        logger.error("Record Not Found" , ex);

	        return new ResponseEntity(userFieldError, HttpStatus.NOT_FOUND);

	    }



	    @ExceptionHandler(Exception.class)

	    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

	    	UserFieldError userFieldError = new UserFieldError(new Date(),

	                "Server Error",

	                ex.getMessage());

	        logger.error("Server Error" , ex);

	        return new ResponseEntity(userFieldError, HttpStatus.INTERNAL_SERVER_ERROR);

	    }
	

}
