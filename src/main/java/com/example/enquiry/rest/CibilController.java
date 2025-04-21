package com.example.enquiry.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enquiry.dto.CibilDTO;
import com.example.enquiry.service.CibilService;

@RestController
@RequestMapping(value = "/api/cibils")
public class CibilController 
{
	@Autowired
	private CibilService cibilService;
	
	@PostMapping
	public ResponseEntity<String> addCibil(@RequestBody CibilDTO cibilDTO)
	{
		String msg=cibilService.addCibil(cibilDTO);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}
}
