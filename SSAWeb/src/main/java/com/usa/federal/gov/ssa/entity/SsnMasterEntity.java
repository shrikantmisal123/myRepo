package com.usa.federal.gov.ssa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


/** this Entity class is map with the database object which having direct relation with DB table
 * @author shrikant
 *
 */
@Entity
@Table(name = "MASTER_SSN")
@Data
public class  SsnMasterEntity {
	@Id
	@GeneratedValue(generator = "MASTER_SEQ")
	@SequenceGenerator(name = "MASTER_SEQ",
					sequenceName = "MASTER_SSN_SEQ",
					allocationSize = 1,initialValue = 804242901
			)
	 Long ssn_No;
	
	@Column(name = "FIRST_NAME",length = 10)
	String first_Name;
	
	@Column(name = "LAST_NAME",length = 10)
	 String last_Name;
	
	@Column(name = "GENDER",length = 6)
	  String gender;

	@Column(name = "dob",length =20)
	String dob;
	@Column(name = "PHONE_NO")	
	Long		phoneNo;
	
	@Column(name = "STATE")
	String state;
	
	@Lob
	@Column(name = "PHOTO")
	byte[] photo;
	
	@CreationTimestamp
	@DateTimeFormat(pattern = "dd/MMM/YY")
	@Column(name = "CREATED_DATE")
	Date createdDate;
	
	@UpdateTimestamp
	@DateTimeFormat(pattern = "dd/MMM/YY hh:mm:yy")
	@Column(name = "UPDATED_DATE")
	Date updatedDate;
	
	@Column(name = "CREATED_BY")
	String createdBy;
	@Column(name = "UPDATED_BY")
	String updatedBy;
	
	
}
