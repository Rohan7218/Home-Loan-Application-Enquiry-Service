package com.example.enquiry.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cibil_Score_Details")
public class CibilDetails 
{
	@Id
	@SequenceGenerator(name = "cibilId", sequenceName = "cibilId", allocationSize = 1, initialValue =101)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cibilId")
	@Column(name = "Cibil_Id")
	private Integer cibilId;
	
	@Column(name = "Pancard_Number")
	private String panCardNo;
	
	@Column(name = "Cibil_Score")
	private Integer cibilScore;
	
	@Column(name = "Cibil_Eligibility")
	private String cibilEligibility;
	
	@Column(name = "Cibil_Remark")
	private String cibilRemark;
	
	@CreationTimestamp
	@Column(name = "Cibil_Created_Date", updatable = false)
	private LocalDate createdDate;
	
	@UpdateTimestamp
	@Column(name = "Cibil_Updated_Date", insertable = false)
	private LocalDate updatedDate;
	
	@Column(name = "Enquiry_Id")
	private Integer enquiryId;
}
