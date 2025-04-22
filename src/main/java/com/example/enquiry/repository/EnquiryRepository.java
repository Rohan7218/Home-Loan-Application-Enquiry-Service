package com.example.enquiry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enquiry.dto.EnquiryStatus;
import com.example.enquiry.entity.EnquiryDetails;
@Repository
public interface EnquiryRepository extends JpaRepository<EnquiryDetails, Integer>
{	
	List<EnquiryDetails> findAllByEnquiryStatus(EnquiryStatus enquiryStatus);
	List<EnquiryDetails> findAllByIsPresent(Boolean isPresent);
}
