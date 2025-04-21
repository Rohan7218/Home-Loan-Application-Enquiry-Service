package com.example.enquiry.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

@Data
public class EnquiryStatusDTO 
{
	@Enumerated(EnumType.STRING)
	private EnquiryStatus EnquiryStatus;
}
