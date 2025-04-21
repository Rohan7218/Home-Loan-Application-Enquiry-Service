package com.example.enquiry.rest;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enquiry.dto.EnquiryDTO;
import com.example.enquiry.dto.EnquiryStatusDTO;
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
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}
	
	@PatchMapping(value = "/{enquiryId}")
	public ResponseEntity<String> updateEnquiryStatus(@RequestBody EnquiryStatusDTO enquiryStatusDTO, @PathVariable Integer enquiryId)
	{
		String msg=enquiryService.updateEnquiryStatus(enquiryStatusDTO, enquiryId);
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	
}
