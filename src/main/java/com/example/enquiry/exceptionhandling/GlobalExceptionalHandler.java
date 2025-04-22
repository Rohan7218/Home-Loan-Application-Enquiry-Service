package com.example.enquiry.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler 
{
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerExceptionHandler(NullPointerException e)
	{
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex)
	{
        String errorMessage = ex.getBindingResult()
                                .getAllErrors()
                                .get(0)
                                .getDefaultMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(NoEnquiryFoundException.class)
	public ResponseEntity<String> handleNoEnquiryFoundExceptionHandler(NoEnquiryFoundException e)
	{
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NoEnquiriesFoundException.class)
	public ResponseEntity<String> handleNoEnquiriesFoundExceptionHandler(NoEnquiriesFoundException e)
	{
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
}
