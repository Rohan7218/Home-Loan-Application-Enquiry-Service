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
import com.example.enquiry.service.EnquiryService;

@RestController
@RequestMapping(value = "/api/enquiries")
public class EnquiryController 
{
	private static final Logger LOGGER=LoggerFactory.getLogger(EnquiryController.class);
	
	@Autowired
	private EnquiryService enquiryService;
	
	@PostMapping
	public ResponseEntity<String> registerEnquiry(@RequestBody @Valid EnquiryDTO enquiryDTO)
	{
		LOGGER.info("EnquiryController : PostMapping : registerEnquiry : Entry");
		String msg=enquiryService.registerEnquiry(enquiryDTO);
		
		LOGGER.info("EnquiryController : PostMapping : registerEnquiry : Exit");
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
	@PatchMapping(value = "/{enquiryId}")
	public ResponseEntity<String> updateEnquiryStatus(@RequestBody EnquiryStatusDTO enquiryStatusDTO, @PathVariable Integer enquiryId)
	{
		LOGGER.info("EnquiryController : PatchMapping : updateEnquiryStatus : Entry");
		String msg=enquiryService.updateEnquiryStatus(enquiryStatusDTO, enquiryId);
		if(msg!=null)
		{
			LOGGER.info("EnquiryController : PatchMapping : updateEnquiryStatus : Exit");
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		}
		else
		{
			LOGGER.info("EnquiryController : PostMapping : updateEnquiryStatus : Exit");
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/{enquiryId}")
	public ResponseEntity<GetEnquiryResponseDTO> getEnquiryById(@PathVariable Integer enquiryId)
	{
		LOGGER.info("EnquiryController : GetMapping : getEnquiryById : Entry");
		GetEnquiryResponseDTO getEnquiryResponseDTO=enquiryService.getEnquiryById(enquiryId);
		
		LOGGER.info("EnquiryController : GetMapping : getEnquiryById : Exit");
		return new ResponseEntity<GetEnquiryResponseDTO>(getEnquiryResponseDTO, HttpStatus.OK);	
	}
	
	@DeleteMapping(value = "/{enquiryId}")
	public ResponseEntity<String> softDeleteEnquiry(@PathVariable Integer enquiryId)
	{
		LOGGER.info("EnquiryController : DeleteMapping : softDeleteEnquiry : Entry");
		String msg=enquiryService.softDeleteEnquiry(enquiryId);
		
		LOGGER.info("EnquiryController : DeleteMapping : softDeleteEnquiry : Exit");
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<EnquiryDetails>>  getAllEnquiries()
	{
		LOGGER.info("EnquiryController : GetMapping : getAllEnquiries : Entry");
			List<EnquiryDetails> enquiries =	enquiryService.getAllEnquiries();
			if(enquiries!= null) 
			{
				LOGGER.info("EnquiryController : GetMapping : getAllEnquiries : Exit");
				return new ResponseEntity<List<EnquiryDetails>>(enquiries, HttpStatus.OK);
			}
			else
			{
				LOGGER.info("GetMapping : getAllEnquiries : Exit");
				return new ResponseEntity<List<EnquiryDetails>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
		
	@GetMapping(value = "/status/{enquiryStatus}")
	public ResponseEntity<List<EnquiryDetails>> getEnquiryByStatus(@PathVariable EnquiryStatus enquiryStatus)
	{
		LOGGER.info("@GetMapping : getEnquiryByStatus : Entry");
		List<EnquiryDetails> enquiryList=enquiryService.getEnquiryByStatus(enquiryStatus);
		if(!enquiryList.isEmpty())
		{
			LOGGER.info("GetMapping : getEnquiryByStatus : Exit");
			return new ResponseEntity<List<EnquiryDetails>>(enquiryList, HttpStatus.OK);
		}
		else
		{
			LOGGER.info("GetMapping : getEnquiryByStatus : Exit");
			return new ResponseEntity<List<EnquiryDetails>>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PutMapping(value = "/edit-enquiry/{enquiryId}")
	public ResponseEntity<String> updateEnquiry(@RequestBody @Valid EnquiryUpdateDTO enquiryUpdateDTO,@PathVariable Integer enquiryId)
	{
		LOGGER.info("PutMapping : updateEnquiry : Entry");
			String msg=enquiryService.updateEnquiry(enquiryUpdateDTO,enquiryId);
			
			LOGGER.info("PutMapping : updateEnquiry : Exit");
				return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	
}
