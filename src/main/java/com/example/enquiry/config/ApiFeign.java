package com.example.enquiry.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.enquiry.entity.EnquiryDetails;

@FeignClient(name = "customer-service")
public interface ApiFeign 
{
	@PostMapping(value = "/api/customer")
	public ResponseEntity<String> addCustomer(@RequestBody  EnquiryDetails enquiryDetails);
	
}
