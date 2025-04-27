package com.example.enquiry.service;

import com.example.enquiry.dto.CibilDTO;
import com.example.enquiry.entity.CibilDetails;

public interface CibilService {

	String addCibil(CibilDTO cibilDTO);

	String updateCibilDetails(Integer enquiryId, CibilDTO cibilDTO);

}
