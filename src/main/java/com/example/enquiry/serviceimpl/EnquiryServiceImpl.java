package com.example.enquiry.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enquiry.dto.EnquiryDTO;
import com.example.enquiry.dto.EnquiryStatus;
import com.example.enquiry.dto.EnquiryStatusDTO;
import com.example.enquiry.entity.CibilDetails;
import com.example.enquiry.entity.EnquiryDetails;
import com.example.enquiry.repository.EnquiryRepository;
import com.example.enquiry.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	@Autowired
	private EnquiryRepository enquiryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String registerEnquiry(EnquiryDTO enquiryDTO) 
	{
		CibilDetails cibilDetails = new CibilDetails();
							cibilDetails.setCibilEligibility("Inprocess");
							cibilDetails.setCibilRemark("Inprocess");
							cibilDetails.setCibilScore(0);
							cibilDetails.setPanCardNo(enquiryDTO.getPanCardNo());

		EnquiryDetails enquiryDetails = modelMapper.map(enquiryDTO, EnquiryDetails.class);
								enquiryDetails.setContactNo(Long.valueOf(enquiryDTO.getContactNo()));
								enquiryDetails.setEnquiryStatus(EnquiryStatus.REGISTERED);
								enquiryDetails.setCibilId(cibilDetails);
								enquiryDetails.setIsPresent(true);

		enquiryRepository.save(enquiryDetails);
		cibilDetails.setEnquiryId(enquiryDetails.getEnquiryId());
		enquiryRepository.save(enquiryDetails);
		return "!!!!...Enquiry Registered SuccessFully....!!!!";
	}
	
	@Override
	public String updateEnquiryStatus(EnquiryStatusDTO enquiryStatusDTO, Integer enquiryId)
	{
		if(enquiryRepository.findById(enquiryId).isPresent())
		{
			EnquiryDetails enquiryDetails = enquiryRepository.findById(enquiryId).get();
								   enquiryDetails.setEnquiryStatus(enquiryStatusDTO.getEnquiryStatus());
		
			enquiryRepository.save(enquiryDetails);
			return "!!!!....Enquiry Status Updated SuccessFully....!!!!";
		}
		
		return null;
	}

}
