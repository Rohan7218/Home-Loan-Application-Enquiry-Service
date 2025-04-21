package com.example.enquiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EnquiryServiceApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(EnquiryServiceApplication.class, args);
	}
}
