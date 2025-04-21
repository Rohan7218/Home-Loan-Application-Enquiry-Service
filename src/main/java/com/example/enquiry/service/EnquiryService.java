package com.example.enquiry.service;

import com.example.enquiry.dto.EnquiryDTO;
import com.example.enquiry.dto.EnquiryStatusDTO;

public interface EnquiryService
{

	String registerEnquiry(EnquiryDTO enquiryDTO);

	String updateEnquiryStatus(EnquiryStatusDTO enquiryStatusDTO, Integer enquiryId);


}
