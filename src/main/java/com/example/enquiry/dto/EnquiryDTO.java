package com.example.enquiry.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EnquiryDTO 
{
		
	@NotNull
	@NotEmpty
	@NotBlank(message = "Firstname is required")
	@Size(min = 3, message = "Name must be at least 3 characters long")
	private String firstName;
	
	@NotNull
	@NotEmpty
	@NotBlank(message = "Lastname is required")
	@Size(min = 3, message = "Name must be at least 3 characters long")
	private String lastName;

	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email format")
	@NotBlank(message = "Email-Id is required")
	private String emailId;

	@NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[789]\\d{9}$", message = "Mobile number must start with 7, 8, or 9 and be 10 digits long")
	private String contactNo;

	@NotBlank(message = "PAN card number is required")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN card number format")
	private String panCardNo;
	
	@NotBlank(message = "City name is required")
	private String cityName;
}
