package com.example.enquiry.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enquiry.dto.CibilDTO;
import com.example.enquiry.entity.CibilDetails;
import com.example.enquiry.entity.EnquiryDetails;
import com.example.enquiry.repository.CibilRepository;
import com.example.enquiry.repository.EnquiryRepository;
import com.example.enquiry.service.CibilService;

@Service
public class CibilServiceImpl implements CibilService {
	@Autowired
	private CibilRepository cibilRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EnquiryRepository enquiryRepository; 

	@Override
	public String addCibil(CibilDTO cibilDTO)
	{
		CibilDetails cibilDetails1 = cibilRepository.findById(cibilDTO.getCibilId()).get();
		
		if (cibilRepository.findById(cibilDTO.getCibilId()).isPresent()) 
		{
			CibilDetails cibilDetails2 = modelMapper.map(cibilDTO, CibilDetails.class);
							  cibilDetails2.setPanCardNo(cibilDetails1.getPanCardNo());
							  cibilDetails2.setEnquiryId(cibilDetails1.getEnquiryId());
	
			cibilRepository.save(cibilDetails2);
			return "!!!!Cibil Score Added SuccessFully!!!!";
		} 
		else 
		{
			return "!!!...Invalid Cibil Id...!!!";
		}

	}
	
	@Override
	public String updateCibilDetails(Integer enquiryId, CibilDTO cibilDTO) 
	{
		if(enquiryRepository.findById(enquiryId).isPresent()) 
		{
			CibilDetails existingCibilDetails = cibilRepository.findById(cibilDTO.getCibilId()).get();
						
			if(cibilDTO.getCibilEligibility()!=null) 
			{
				existingCibilDetails.setCibilEligibility(cibilDTO.getCibilEligibility());
			}
			
			if(cibilDTO.getCibilRemark()!=null) 
			{
				existingCibilDetails.setCibilRemark(cibilDTO.getCibilRemark());
			}
			
			if(cibilDTO.getCibilScore()!=null) 
			{
				existingCibilDetails.setCibilScore(cibilDTO.getCibilScore());
			}
			
			cibilRepository.save(existingCibilDetails);
			return "!!!!....Updated SucessFully....!!!!";
		}
		
		return null;
	}
	
}
