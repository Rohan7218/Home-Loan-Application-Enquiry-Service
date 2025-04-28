package com.example.enquiry.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enquiry.dto.CibilDTO;
import com.example.enquiry.response.ApiResponse;
import com.example.enquiry.service.CibilService;

@RestController
@RequestMapping(value = "/api/cibils")
public class CibilController 
{
	private static final Logger LOGGER=LoggerFactory.getLogger(CibilController.class);
	
	@Autowired
	private CibilService cibilService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<String>> addCibil(@RequestBody CibilDTO cibilDTO)
	{
		LOGGER.info("CibilController : PostMapping : addCibil : Entry");
		String msg=cibilService.addCibil(cibilDTO);
		LOGGER.info("CibilController : PostMapping : addCibil : Exit");
		ApiResponse<String> apiResponse=new ApiResponse<String>(msg);
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.CREATED);
	}
	
	@PatchMapping(value = "/update-cibli/{enquiryId}")
	public ResponseEntity<ApiResponse<String>> updateCibilDetails(@PathVariable Integer enquiryId , @RequestBody CibilDTO cibilDTO)
	{
		LOGGER.info("CibilController : PatchMapping : updateCibilDetails : Entry");
		String msg= cibilService.updateCibilDetails(enquiryId , cibilDTO);
		if(msg!=null) 
		{
			LOGGER.info("CibilController : PatchMapping : updateCibilDetails : Exit");
			ApiResponse<String> apiResponse=new ApiResponse<String>(msg);
			return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.OK);		
		}
			LOGGER.info("CibilController : PatchMapping : updateCibilDetails : Exit");
			return new ResponseEntity<ApiResponse<String>>(HttpStatus.NOT_FOUND);		
	}	
	
	
}
