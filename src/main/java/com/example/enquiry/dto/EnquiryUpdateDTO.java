package com.example.enquiry.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EnquiryUpdateDTO
{
	@Size(min = 1, message = "Name must be at least 1 characters long")
	private String firstName;
	
	@Size(min = 1, message = "Name must be at least 1 characters long")
	private String middleName;
	
	@Size(min = 1, message = "Name must be at least 1 characters long")
	private String lastName;

	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email format")
	private String emailId;

    @Pattern(regexp = "^[789]\\d{9}$", message = "Mobile number must start with 7, 8, or 9 and be 10 digits long")
	private String contactNo;

    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN card number format")
	private String panCardNo;
	
	private String cityName;
}
