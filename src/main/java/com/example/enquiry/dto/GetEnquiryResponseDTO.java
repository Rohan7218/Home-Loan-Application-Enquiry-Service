package com.example.enquiry.dto;

import java.time.LocalDate;

import com.example.enquiry.entity.CibilDetails;

import lombok.Data;

@Data
public class GetEnquiryResponseDTO 
{
	private Integer enquiryId;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String emailId;
	
	private Long contactNo;
	
	private String panCardNo;
	
	private LocalDate createdDate;
	
	private Boolean isPresent;
	
	private CibilDetails cibilId;

}
