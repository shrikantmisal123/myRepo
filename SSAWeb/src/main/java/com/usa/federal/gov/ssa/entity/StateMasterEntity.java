package com.usa.federal.gov.ssa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;


/** this Entity class is map with the database object which having direct relation with DB table
 * @author shrikant
 *
 */

@Entity
@Table(name = "STATE_MASTER")
@Data
public class StateMasterEntity {

	@Id
	@Column(name = "stateId")
	@GeneratedValue(generator ="STATE_SEQ" )
	@SequenceGenerator(name = "STATE_SEQ",sequenceName = "STATE_SEQ",
					allocationSize = 1,initialValue = 101)
	Integer stateId;
	@Column(name = "stateCode",length = 10)
	String stateCode;
	
	@Column(name = "stateName",length = 20)
	String stateName;
}
