package com.example.enquiry.exceptionhandling;

public class NoEnquiriesFoundException extends RuntimeException
{
	public NoEnquiriesFoundException(String msg) {
		super(msg);
	}
	
}
