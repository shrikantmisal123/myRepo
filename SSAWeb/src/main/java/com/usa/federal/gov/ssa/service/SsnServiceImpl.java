package com.usa.federal.gov.ssa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.usa.federal.gov.ssa.entity.SsnMasterEntity;
import com.usa.federal.gov.ssa.entity.StateMasterEntity;
import com.usa.federal.gov.ssa.model.SsnModel;
import com.usa.federal.gov.ssa.repository.SsnMasterRepository;
import com.usa.federal.gov.ssa.repository.StateMasterRepository;
import com.usa.federal.gov.ssa.restcontroller.SSNVerifyResponse;

/** this class contain a business Logic 
 * @author shrikant
 *
 */
@Service("ssnService")
public class SsnServiceImpl implements SsnService{
		Logger   logger		=		LoggerFactory.getLogger(SsnServiceImpl.class);
	@Autowired	
	SsnMasterRepository    ssnRepository;
	@Autowired
	StateMasterRepository stateRepository; 

	/** this method is used to return the all state name of an US
	 *@return Us State Names
	 */
	@Override
	public List<String> getStateNames() {
		logger.debug("SsnServiceImpl.getStateNames() method execution got started......................");
		List<StateMasterEntity>  stateEntityList		=		null;
		List<String>							stateNamesList			=		new ArrayList<String>();;	
		
		//get list of states
		stateEntityList					=		stateRepository.findAll();
		  if(!stateEntityList.isEmpty()) 
		  {
			  		for(StateMasterEntity  entity:stateEntityList)
			  		{
			  			String stateName = entity.getStateName();
					  stateNamesList.add(stateName); 
			  		} 
		 }
		logger.debug("SsnServiceImpl.getStateNames() method execution got finished......................");
		logger.info("SsnServiceImpl.getStateNames() method execution got completed......................");
		return stateNamesList;
	}

	/** this method is use to persist or enroll the record
	 *@return	it will return the SSN_No	
	 * 
	 */
	@Override
	public Long enrollSSN(SsnModel ssnModel)  {
		logger.debug("SsnServiceImpl.enrollSSN() method Execution has been started.......................");
		SsnMasterEntity		ssnEntity		=	null;
		
		//create Entity object
		ssnEntity				=		new SsnMasterEntity();
		//copy ssn model state to ssn entity object  
		BeanUtils.copyProperties(ssnModel, ssnEntity);
		
		//set the photos In bytes and assign to the entity object
		try
		{
				ssnEntity.setPhoto(ssnModel.getPhoto().getBytes());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//invoke the repository method
		ssnEntity		=		ssnRepository.save(ssnEntity);	
		System.out.println(ssnEntity.getSsn_No());
		logger.debug("SsnServiceImpl.enrollSSN() method Execution has been finished.......................");
		logger.debug("SsnServiceImpl.enrollSSN() method Execution has been successfully completed.......................");
		return ssnEntity.getSsn_No();
	}

	@Override
	public List<SsnModel> fetchAllSsnRecords() {
		logger.debug("SsnServiceImpl.fetchAllSsnRecords() method has been started.................");
		List<SsnMasterEntity> 	ssnEntitiesList						=		null;
		
		//create List object for model and entities
		List<SsnModel> 					ssnModelsList						=		new ArrayList<SsnModel>();
		ssnEntitiesList				=		new ArrayList<SsnMasterEntity>();
		//fetch all SSNEntity Details 
		ssnEntitiesList				=		ssnRepository.findAll();
		
		//convert ssnEntities to ssnModels
		if(!ssnEntitiesList.isEmpty())
		{
			ssnEntitiesList.forEach(entity->{
					SsnModel		model		=			new SsnModel();
					BeanUtils.copyProperties(entity, model);
					ssnModelsList.add(model);
			});
			
		}
		logger.debug("SsnServiceImpl.fetchAllSsnRecords() has been finished............................");
		logger.info("SsnServiceImpl.fetchAllSsnRecords() has been successfully executed.....................");
		return ssnModelsList;
	}

	@Override
	public SSNVerifyResponse verfiySsnNo(Long ssN_No) {
		logger.debug("SsnServiceImpl:verifiySsnNo() method execution got started............");
		SSNVerifyResponse						ssnResponse					=	 null;
		Optional<SsnMasterEntity>		ssnEntityOptional			=	  null;			
		SsnMasterEntity							ssnEntity							=		null;
		String 											stateName							=		null;
		//create SsnVerifyReponse Object
		ssnResponse				=		new SSNVerifyResponse();
		//invoke the repository method for getting ssnEntity
		ssnEntityOptional				=		ssnRepository.findById(ssN_No);
		if(ssnEntityOptional.isPresent())
		{
			 ssnEntity			=	ssnEntityOptional.get();
			stateName		=	 ssnEntity.getState();
			ssnResponse.setResponseCode(HttpStatus.OK.value());
			ssnResponse.setResponseMsg(stateName);
			ssnResponse.setResponseDate(new Date());
		}
		else {
				ssnResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
				ssnResponse.setResponseMsg("Invalid SSN No..So Please Make sure ssnNo is correct or Not");	
				ssnResponse.setResponseDate(new Date());	
				logger.debug("ssnNo is not valid......So Please Check Once.....");
		}
		logger.debug("SsnServiceImpl:verifiySsnNo() method execution has finished............");
		logger.info("SsnServiceImpl:verifiySsnNo() method execution got completed............");
		return ssnResponse;
	}
}
