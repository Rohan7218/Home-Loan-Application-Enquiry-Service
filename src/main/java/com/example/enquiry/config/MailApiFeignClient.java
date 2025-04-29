package com.example.enquiry.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.enquiry.dto.EnquiryMailDTO;

@FeignClient(name = "mail-service")
public interface MailApiFeignClient 
{
	@PostMapping(value = "/api/mail/mails")
	public ResponseEntity<String> registrationMail(@RequestBody EnquiryMailDTO enquiryMailDTO);
}
