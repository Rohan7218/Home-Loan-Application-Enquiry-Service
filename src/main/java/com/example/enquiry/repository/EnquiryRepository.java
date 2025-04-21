package com.example.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enquiry.entity.EnquiryDetails;
@Repository
public interface EnquiryRepository extends JpaRepository<EnquiryDetails, Integer>
{
	
}
