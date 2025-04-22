package com.example.enquiry.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enquiry.dto.EnquiryDTO;
import com.example.enquiry.dto.EnquiryStatus;
import com.example.enquiry.dto.EnquiryStatusDTO;
import com.example.enquiry.dto.GetEnquiryResponseDTO;
import com.example.enquiry.entity.CibilDetails;
import com.example.enquiry.entity.EnquiryDetails;
import com.example.enquiry.exceptionhandling.NoEnquiriesFoundException;
import com.example.enquiry.exceptionhandling.NoEnquiryFoundException;
import com.example.enquiry.repository.EnquiryRepository;
import com.example.enquiry.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	@Autowired
	private EnquiryRepository enquiryRepository;

	@Autowired
	private ModelMapper modelMapper;

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
	public String updateEnquiryStatus(EnquiryStatusDTO enquiryStatusDTO, Integer enquiryId) {
		if (enquiryRepository.findById(enquiryId).isPresent()) {
			EnquiryDetails enquiryDetails = enquiryRepository.findById(enquiryId).get();
			enquiryDetails.setEnquiryStatus(enquiryStatusDTO.getEnquiryStatus());

			enquiryRepository.save(enquiryDetails);
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

	public GetEnquiryResponseDTO getEnquiryById(Integer enquiryId) {
		if (enquiryRepository.findById(enquiryId).isPresent()) {
			EnquiryDetails enquiryDetails = enquiryRepository.findById(enquiryId).get();
			GetEnquiryResponseDTO responseDTO = modelMapper.map(enquiryDetails, GetEnquiryResponseDTO.class);
			return responseDTO;
		} else {
			throw new NoEnquiryFoundException("For Given Enquiry Id Record Not Found");

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

		if (!enquiryList.isEmpty()) {
			return enquiryList;
		}
		throw new NoEnquiriesFoundException("No Enquiries Are Available");

	}

	@Override
	public List<EnquiryDetails> getEnquiryByStatus(EnquiryStatus enquiryStatus) {
		List<EnquiryDetails> enquiryList = enquiryRepository.findAllByEnquiryStatus(enquiryStatus);
		return enquiryList;
	}


}
