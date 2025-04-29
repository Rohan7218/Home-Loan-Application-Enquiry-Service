package com.example.enquiry.dto;

import lombok.Data;

@Data
public class MailParameterDTO 
{
	private Integer enquiryId;

	private String firstName;
	
	private String middleName;
	
	private String lastName;

	private String emailId;

	private Long contactNo;
}
