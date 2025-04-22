package com.example.enquiry.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enquiry.dto.EnquiryDTO;
import com.example.enquiry.dto.EnquiryStatus;
import com.example.enquiry.dto.EnquiryStatusDTO;
import com.example.enquiry.dto.GetEnquiryResponseDTO;
import com.example.enquiry.entity.EnquiryDetails;
import com.example.enquiry.service.EnquiryService;

@RestController
@RequestMapping(value = "/api/enquiries")
public class EnquiryController 
{
	@Autowired
	private EnquiryService enquiryService;
	
	@PostMapping
	public ResponseEntity<String> registerEnquiry(@RequestBody @Valid EnquiryDTO enquiryDTO)
	{
		String msg=enquiryService.registerEnquiry(enquiryDTO);
		
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
	@PatchMapping(value = "/{enquiryId}")
	public ResponseEntity<String> updateEnquiryStatus(@RequestBody EnquiryStatusDTO enquiryStatusDTO, @PathVariable Integer enquiryId)
	{
		String msg=enquiryService.updateEnquiryStatus(enquiryStatusDTO, enquiryId);
		if(msg!=null)
		{
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(value = "/{enquiryId}")
	public ResponseEntity<GetEnquiryResponseDTO> getEnquiryById(@PathVariable Integer enquiryId)
	{
		GetEnquiryResponseDTO getEnquiryResponseDTO=enquiryService.getEnquiryById(enquiryId);
		return new ResponseEntity<GetEnquiryResponseDTO>(getEnquiryResponseDTO, HttpStatus.OK);	
	}
	
	@DeleteMapping(value = "/{enquiryId}")
	public ResponseEntity<String> softDeleteEnquiry(@PathVariable Integer enquiryId)
	{
		String msg=enquiryService.softDeleteEnquiry(enquiryId);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<EnquiryDetails>>  getAllEnquiries()
	{
			List<EnquiryDetails> enquiries =	enquiryService.getAllEnquiries();
			if(enquiries!= null) 
			{
				return new ResponseEntity<List<EnquiryDetails>>(enquiries, HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<List<EnquiryDetails>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
		
	@GetMapping(value = "/status/{enquiryStatus}")
	public ResponseEntity<List<EnquiryDetails>> getEnquiryByStatus(@PathVariable EnquiryStatus enquiryStatus)
	{
		List<EnquiryDetails> enquiryList=enquiryService.getEnquiryByStatus(enquiryStatus);
		if(!enquiryList.isEmpty())
		{
			return new ResponseEntity<List<EnquiryDetails>>(enquiryList, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<EnquiryDetails>>(HttpStatus.NO_CONTENT);
		}
	}
	
}
