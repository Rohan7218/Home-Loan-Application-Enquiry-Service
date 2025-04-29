package com.example.enquiry.dto;

import lombok.Data;

@Data
public class EnquiryMailDTO 
{
	private String to;
	private String subject;
	private String fileName;
	private MailParameterDTO mailParameterDTO;
}
