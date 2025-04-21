package com.example.enquiry.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class CibilDTO
{
	
	private Integer cibilId;
	
	private Integer cibilScore;
	
	private String cibilEligibility;
	
	private String cibilRemark;
}
