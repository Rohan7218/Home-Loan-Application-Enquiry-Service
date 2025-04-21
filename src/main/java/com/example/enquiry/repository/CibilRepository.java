package com.example.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enquiry.entity.CibilDetails;

@Repository
public interface CibilRepository extends JpaRepository<CibilDetails,Integer>
{
	
}
