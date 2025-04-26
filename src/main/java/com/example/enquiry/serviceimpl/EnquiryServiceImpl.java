package com.example.enquiry.serviceimpl;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enquiry.config.ApiFeign;
import com.example.enquiry.dto.EnquiryDTO;
import com.example.enquiry.dto.EnquiryStatus;
import com.example.enquiry.dto.EnquiryStatusDTO;
import com.example.enquiry.dto.EnquiryUpdateDTO;
import com.example.enquiry.dto.GetEnquiryResponseDTO;
import com.example.enquiry.entity.CibilDetails;
import com.example.enquiry.entity.EnquiryDetails;
import com.example.enquiry.exceptionhandling.NoEnquiriesFoundException;
import com.example.enquiry.exceptionhandling.NoEnquiryFoundException;
import com.example.enquiry.repository.EnquiryRepository;
import com.example.enquiry.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService 
{
	@Autowired
	private EnquiryRepository enquiryRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApiFeign apiFeign;

	@Override
	public String registerEnquiry(EnquiryDTO enquiryDTO) {
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
		return "!!!!....Enquiry Registered SuccessFully....!!!!";
	}

	@Override
	public String updateEnquiryStatus(EnquiryStatusDTO enquiryStatusDTO, Integer enquiryId)
	{
		if (enquiryRepository.findById(enquiryId).isPresent())
		{
			EnquiryDetails enquiryDetails = enquiryRepository.findById(enquiryId).get();
			enquiryDetails.setEnquiryStatus(enquiryStatusDTO.getEnquiryStatus());

			EnquiryDetails savedEnquiryDetails = enquiryRepository.save(enquiryDetails);
			if(savedEnquiryDetails.getEnquiryStatus().equals(EnquiryStatus.APPROVED))
			{
				apiFeign.addCustomer(enquiryDetails);
			}
			return "!!!!....Enquiry Status Updated SuccessFully....!!!!";
		}

		return null;
	}

	@Override
	public GetEnquiryResponseDTO getEnquiryById(Integer enquiryId)
	{
		if(enquiryRepository.findById(enquiryId).isPresent())
		{
				EnquiryDetails enquiryDetails = enquiryRepository.findById(enquiryId).get();
				GetEnquiryResponseDTO responseDTO = modelMapper.map(enquiryDetails, GetEnquiryResponseDTO.class);
				return responseDTO;
		}
		else
		{
			throw new NoEnquiryFoundException("!!!!....For Given Enquiry Id Record Not Found....!!!!");
		}
	}

	@Override

	public String softDeleteEnquiry(Integer enquiryId)
	{
		EnquiryDetails  getEnquiryDetails = enquiryRepository.findById(enquiryId).get();
		if(getEnquiryDetails.getEnquiryStatus().equals(EnquiryStatus.REJECTED))
		{
				getEnquiryDetails.setIsPresent(false);
				enquiryRepository.save(getEnquiryDetails);
				return "!!!!....Record Deleted SuccessFully....!!!!";
		}
		return "!!!!....For Given Enquiry Id User Is Not Found....!!!!";
	}

	@Override
	public List<EnquiryDetails> getAllEnquiries() {
		List<EnquiryDetails> enquiryList = enquiryRepository.findAllByIsPresent(true);

		 if(!enquiryList.isEmpty())
		 {
			 return enquiryList; 
		 }
		 else
		 {
			 throw new NoEnquiriesFoundException("!!!!....No Enquiries Are Available....!!!!");
		 }
	}

	@Override
	public List<EnquiryDetails> getEnquiryByStatus(EnquiryStatus enquiryStatus) {
		List<EnquiryDetails> enquiryList = enquiryRepository.findAllByEnquiryStatus(enquiryStatus);
		return enquiryList;
	}
	
	
	@Override
	public String updateEnquiry(@Valid EnquiryUpdateDTO enquiryUpdateDTO, Integer enquiryId)
	{
		if (enquiryRepository.findById(enquiryId).isPresent()) 
		{
			EnquiryDetails enquiryDetails = enquiryRepository.findById(enquiryId).get();
			
<<<<<<< HEAD
			if(enquiryDTO.getFirstName()!=null && !enquiryDTO.getFirstName().isEmpty())
				
=======
			if(enquiryUpdateDTO.getFirstName()!=null)
>>>>>>> 08d01ddb9dc90d0abcf173f9d35966b7269391f6
			{
				enquiryDetails.setFirstName(enquiryUpdateDTO.getFirstName());
			}
			if(enquiryUpdateDTO.getLastName()!=null)
			{
				enquiryDetails.setLastName(enquiryUpdateDTO.getLastName());
			}
			if(enquiryUpdateDTO.getMiddleName()!=null)
			{
				enquiryDetails.setMiddleName(enquiryUpdateDTO.getMiddleName());
			}
			
			if(enquiryUpdateDTO.getCityName()!=null)
			{
				enquiryDetails.setCityName(enquiryUpdateDTO.getCityName());
			}
			if(enquiryUpdateDTO.getContactNo()!=null)
			{
				enquiryDetails.setContactNo(Long.valueOf(enquiryUpdateDTO.getContactNo()));
			}
			if(enquiryUpdateDTO.getEmailId()!=null)
			{
				enquiryDetails.setEmailId(enquiryUpdateDTO.getEmailId());
			}
			
			if(enquiryUpdateDTO.getPanCardNo()!=null)
			{
				enquiryDetails.setPanCardNo(enquiryUpdateDTO.getPanCardNo());
			}

			enquiryRepository.save(enquiryDetails);
			return "Enquiry Details Updated Succesfully";
		}
			return "Enquiry Not Found";
		
	}
	
	
	
	
	


}
