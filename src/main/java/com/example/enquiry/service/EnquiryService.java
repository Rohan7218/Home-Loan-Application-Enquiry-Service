package com.example.enquiry.service;

import java.util.List;

import com.example.enquiry.dto.EnquiryDTO;
import com.example.enquiry.dto.EnquiryStatus;
import com.example.enquiry.dto.EnquiryStatusDTO;
import com.example.enquiry.dto.GetEnquiryResponseDTO;
import com.example.enquiry.entity.EnquiryDetails;

public interface EnquiryService
{

	String registerEnquiry(EnquiryDTO enquiryDTO);

	String updateEnquiryStatus(EnquiryStatusDTO enquiryStatusDTO, Integer enquiryId);

	GetEnquiryResponseDTO getEnquiryById(Integer enquiryId);

	String softDeleteEnquiry(Integer enquiryId);

	List<EnquiryDetails> getAllEnquiries();

	List<EnquiryDetails> getEnquiryByStatus(EnquiryStatus enquiryStatus);



}
