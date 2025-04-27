package com.example.enquiry.serviceimpl;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOGGER=LoggerFactory.getLogger(EnquiryServiceImpl.class);
	
	@Autowired
	private EnquiryRepository enquiryRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApiFeign apiFeign;

	@Override
	public String registerEnquiry(EnquiryDTO enquiryDTO)
	{
		LOGGER.debug("EnquiryServiceImpl : registerEnquiry : Entry");
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
		
		LOGGER.debug("EnquiryServiceImpl : registerEnquiry : Exit");
		
		return "!!!!....Enquiry Registered SuccessFully....!!!!";
	}

	@Override
	public String updateEnquiryStatus(EnquiryStatusDTO enquiryStatusDTO, Integer enquiryId)
	{
		if (enquiryRepository.findById(enquiryId).isPresent())
		{
			LOGGER.debug("EnquiryServiceImpl : updateEnquiryStatus : Entry");
			EnquiryDetails enquiryDetails = enquiryRepository.findById(enquiryId).get();
			enquiryDetails.setEnquiryStatus(enquiryStatusDTO.getEnquiryStatus());

			EnquiryDetails savedEnquiryDetails = enquiryRepository.save(enquiryDetails);
			if(savedEnquiryDetails.getEnquiryStatus().equals(EnquiryStatus.APPROVED))
			{
				LOGGER.debug("EnquiryServiceImpl : updateEnquiryStatus : Feign client : Entry");
				apiFeign.addCustomer(enquiryDetails);
			}
			LOGGER.debug("EnquiryServiceImpl : updateEnquiryStatus : Exit");
			return "!!!!....Enquiry Status Updated SuccessFully....!!!!";
		}

		return null;
	}

	@Override
	public GetEnquiryResponseDTO getEnquiryById(Integer enquiryId)
	{
		if(enquiryRepository.findById(enquiryId).isPresent())
		{
				LOGGER.debug("EnquiryServiceImpl : getEnquiryById : Entry");
				EnquiryDetails enquiryDetails = enquiryRepository.findById(enquiryId).get();
				GetEnquiryResponseDTO responseDTO = modelMapper.map(enquiryDetails, GetEnquiryResponseDTO.class);
				LOGGER.debug("EnquiryServiceImpl : getEnquiryById : Exit");
				return responseDTO;
		}
		else
		{
			LOGGER.debug("EnquiryServiceImpl : getEnquiryById : Exit");
			throw new NoEnquiryFoundException("!!!!....For Given Enquiry Id Record Not Found....!!!!");
		}
	}

	@Override

	public String softDeleteEnquiry(Integer enquiryId)
	{
		EnquiryDetails  getEnquiryDetails = enquiryRepository.findById(enquiryId).get();
		if(getEnquiryDetails.getEnquiryStatus().equals(EnquiryStatus.REJECTED))
		{
			LOGGER.warn("EnquiryServiceImpl : softDeleteEnquiry : Entry");
				getEnquiryDetails.setIsPresent(false);
				enquiryRepository.save(getEnquiryDetails);
				LOGGER.warn("EnquiryServiceImpl : softDeleteEnquiry : Exit");
				return "!!!!....Record Deleted SuccessFully....!!!!";
		}
		LOGGER.warn("EnquiryServiceImpl : softDeleteEnquiry : Exit");
		return "!!!!....For Given Enquiry Id User Is Not Found....!!!!";

	}
	public String softdeleteEnquiry(Integer enquiryId) {
		EnquiryDetails getEnquiryDetails = enquiryRepository.findById(enquiryId).get();
		if (getEnquiryDetails.getEnquiryStatus().equals(EnquiryStatus.REJECTED)) {
			getEnquiryDetails.setIsPresent(false);
			enquiryRepository.save(getEnquiryDetails);
			return "RECORD DELETED SUCCESFULLY ";
		}
		return "FOR GIVEN ENQUIRY ID USER IS NOT FOUND " + enquiryId;


	}

	@Override
	public List<EnquiryDetails> getAllEnquiries() 
	{
		LOGGER.debug("EnquiryServiceImpl : getAllEnquiries : Entry");
		List<EnquiryDetails> enquiryList = enquiryRepository.findAllByIsPresent(true);
		 if(!enquiryList.isEmpty())
		 {
			 LOGGER.debug("EnquiryServiceImpl : getAllEnquiries : Exit");
			 return enquiryList; 
		 }
		 else
		 {
			 LOGGER.debug("EnquiryServiceImpl : getAllEnquiries : Exit");
			 throw new NoEnquiriesFoundException("!!!!....No Enquiries Are Available....!!!!");
		 }
		 
	}

	@Override
	public List<EnquiryDetails> getEnquiryByStatus(EnquiryStatus enquiryStatus) 
	{
		LOGGER.info("EnquiryServiceImpl : getEnquiryByStatus : Entry");
		List<EnquiryDetails> enquiryList = enquiryRepository.findAllByEnquiryStatus(enquiryStatus);
		LOGGER.info("EnquiryServiceImpl : getEnquiryByStatus : Exit");
		return enquiryList;
	}
	
	
	@Override
	public String updateEnquiry(@Valid EnquiryUpdateDTO enquiryUpdateDTO, Integer enquiryId)
	{
		if (enquiryRepository.findById(enquiryId).isPresent()) 
		{
			LOGGER.info("EnquiryServiceImpl : updateEnquiry : Entry");
			EnquiryDetails enquiryDetails = enquiryRepository.findById(enquiryId).get();
				

			if(enquiryUpdateDTO.getFirstName()!=null)

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
			LOGGER.info("EnquiryServiceImpl : updateEnquiry : Exit");
			return "Enquiry Details Updated Succesfully";
		}
			LOGGER.info("EnquiryServiceImpl : updateEnquiry : Exit");
			return "Enquiry Not Found";
		
	}
	
	
	
	
	


}
