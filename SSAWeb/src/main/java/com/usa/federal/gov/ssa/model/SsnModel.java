package com.usa.federal.gov.ssa.model;

import java.util.Date;


import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/** this class is used to holding the business data for performing operation
 * @author shrikant
 *
 */

@Data
public class SsnModel {

	 Long ssn_No;
	 String first_Name;
		 String last_Name;
	  String gender;
	  String dob;
	  Long		phoneNo;
	  String state;
	MultipartFile photo;	
	  Date createdDate; 
	  Date updatedDate; 
	  String createdBy; 
	  String updatedBy;
	 
	

}
