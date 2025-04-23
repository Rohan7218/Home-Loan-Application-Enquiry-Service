package com.example.enquiry.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.enquiry.dto.EnquiryStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Enquiry_Details")
public class EnquiryDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Enquiry_Id")
	private Integer enquiryId;

	@Column(name = "First_Name")
	private String firstName;
	
	@Column(name = "Last_Name")
	private String lastName;

	@Column(name = "Email_Id")
	private String emailId;

	@Column(name = "Contact_Number")
	private Long contactNo;

	@Column(name = "Pancard_Number")
	private String panCardNo;

	@Column(name = "City_Name")
	private String cityName;

	@CreationTimestamp
	@Column(name = "Enquiry_Created_Date", updatable = false)
	private LocalDate createdDate;

	@UpdateTimestamp
	@Column(name = "Enquiry_Updated_Date", insertable = false)
	private LocalDate updatedDate;

	@Enumerated(EnumType.STRING)
	private EnquiryStatus enquiryStatus;
	
	@Column(name = "Is_Present")
	private Boolean isPresent;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Enquiry_Cibil_Id")
	private CibilDetails cibilId;
}
