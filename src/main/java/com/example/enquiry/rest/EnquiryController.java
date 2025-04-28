package com.example.enquiry.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enquiry.dto.EnquiryDTO;
import com.example.enquiry.dto.EnquiryStatus;
import com.example.enquiry.dto.EnquiryStatusDTO;
import com.example.enquiry.dto.EnquiryUpdateDTO;
import com.example.enquiry.dto.GetEnquiryResponseDTO;
import com.example.enquiry.entity.EnquiryDetails;
import com.example.enquiry.response.ApiResponse;
import com.example.enquiry.service.EnquiryService;

@RestController
@RequestMapping(value = "/api/enquiries")
public class EnquiryController 
{
	private static final Logger LOGGER=LoggerFactory.getLogger(EnquiryController.class);
	
	@Autowired
	private EnquiryService enquiryService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<String>> registerEnquiry(@RequestBody @Valid EnquiryDTO enquiryDTO)
	{
		LOGGER.info("EnquiryController : PostMapping : registerEnquiry : Entry");
		String msg=enquiryService.registerEnquiry(enquiryDTO);
		
		LOGGER.info("EnquiryController : PostMapping : registerEnquiry : Exit");
		ApiResponse<String> apiResponse=new ApiResponse<String>(msg);
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.CREATED);
	}
	
	@PatchMapping(value = "/{enquiryId}")
	public ResponseEntity<ApiResponse<String>> updateEnquiryStatus(@RequestBody EnquiryStatusDTO enquiryStatusDTO, @PathVariable Integer enquiryId)
	{
		LOGGER.info("EnquiryController : PatchMapping : updateEnquiryStatus : Entry");
		String msg=enquiryService.updateEnquiryStatus(enquiryStatusDTO, enquiryId);
		if(msg!=null)
		{
			LOGGER.info("EnquiryController : PatchMapping : updateEnquiryStatus : Exit");
			ApiResponse<String> apiResponse=new ApiResponse<String>(msg);
			return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.OK);		
		}
		else
		{
			LOGGER.info("EnquiryController : PostMapping : updateEnquiryStatus : Exit");
			ApiResponse<String> apiResponse=new ApiResponse<String>(msg);
			return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);		}
		}
	
	
	@GetMapping(value = "/{enquiryId}")
	public ResponseEntity<ApiResponse<Object>> getEnquiryById(@PathVariable Integer enquiryId)
	{
		LOGGER.info("EnquiryController : GetMapping : getEnquiryById : Entry");
		GetEnquiryResponseDTO getEnquiryResponseDTO=enquiryService.getEnquiryById(enquiryId);
		
		LOGGER.info("EnquiryController : GetMapping : getEnquiryById : Exit");
		ApiResponse<Object> apiResponse=new ApiResponse<Object>(getEnquiryResponseDTO);
		return new ResponseEntity<ApiResponse<Object>>(apiResponse, HttpStatus.OK);		
		}
	
	@DeleteMapping(value = "/{enquiryId}")
	public ResponseEntity<ApiResponse<String>> softDeleteEnquiry(@PathVariable Integer enquiryId)
	{
		LOGGER.info("EnquiryController : DeleteMapping : softDeleteEnquiry : Entry");
		String msg=enquiryService.softDeleteEnquiry(enquiryId);
		
		LOGGER.info("EnquiryController : DeleteMapping : softDeleteEnquiry : Exit");
		ApiResponse<String> apiResponse=new ApiResponse<String>(msg);
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.OK);	}
	
	@GetMapping(value = "/")
	public ResponseEntity<ApiResponse<Object>> getAllEnquiries()
	{
		LOGGER.info("EnquiryController : GetMapping : getAllEnquiries : Entry");
			List<EnquiryDetails> enquiries =	enquiryService.getAllEnquiries();
			if(enquiries!= null) 
			{
				LOGGER.info("EnquiryController : GetMapping : getAllEnquiries : Exit");
				ApiResponse<Object> apiResponse=new ApiResponse<Object>(enquiries);
				return new ResponseEntity<ApiResponse<Object>>(apiResponse, HttpStatus.OK);			}
			else
			{
				LOGGER.info("GetMapping : getAllEnquiries : Exit");
				ApiResponse<Object> apiResponse=new ApiResponse<Object>(enquiries);
				return new ResponseEntity<ApiResponse<Object>>(apiResponse, HttpStatus.NO_CONTENT);			}
	}
		
	@GetMapping(value = "/status/{enquiryStatus}")
	public ResponseEntity<ApiResponse<Object>> getEnquiryByStatus(@PathVariable EnquiryStatus enquiryStatus)
	{
		LOGGER.info("@GetMapping : getEnquiryByStatus : Entry");
		List<EnquiryDetails> enquiryList=enquiryService.getEnquiryByStatus(enquiryStatus);
		if(!enquiryList.isEmpty())
		{
			LOGGER.info("GetMapping : getEnquiryByStatus : Exit");
			ApiResponse<Object> apiResponse=new ApiResponse<Object>(enquiryList);
			return new ResponseEntity<ApiResponse<Object>>(apiResponse, HttpStatus.OK);		}
		else
		{
			LOGGER.info("GetMapping : getEnquiryByStatus : Exit");
			ApiResponse<Object> apiResponse=new ApiResponse<Object>(enquiryList);
			return new ResponseEntity<ApiResponse<Object>>(apiResponse, HttpStatus.NO_CONTENT);		}
	}
	
	
	@PutMapping(value = "/edit-enquiry/{enquiryId}")
	public ResponseEntity<ApiResponse<String>> updateEnquiry(@RequestBody @Valid EnquiryUpdateDTO enquiryUpdateDTO,@PathVariable Integer enquiryId)
	{
		LOGGER.info("PutMapping : updateEnquiry : Entry");
			String msg=enquiryService.updateEnquiry(enquiryUpdateDTO,enquiryId);
			
			LOGGER.info("PutMapping : updateEnquiry : Exit");
			ApiResponse<String> apiResponse=new ApiResponse<String>(msg);
			return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.OK);		}

	
}
