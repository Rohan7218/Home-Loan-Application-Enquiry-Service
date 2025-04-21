package com.example.enquiry.exceptionhandling;

public class NoEnquiryFoundException extends RuntimeException
{
	public NoEnquiryFoundException(String msg) 
	{
		super(msg);
	}
}
