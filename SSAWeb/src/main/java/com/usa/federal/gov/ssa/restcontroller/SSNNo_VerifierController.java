package com.usa.federal.gov.ssa.restcontroller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.usa.federal.gov.ssa.exception.SSNVerifyException;
import com.usa.federal.gov.ssa.service.SsnService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("SSNVerifier")
@Api(value ="ssaApp",  description ="Rest API for SSNNo Verfication", tags = "SSA Api")
public class SSNNo_VerifierController {
		Logger  logger 		=	LoggerFactory.getLogger(SSNNo_VerifierController.class);	
		@Autowired	
		SsnService			ssnService		;
	
		@GetMapping(path ="/{ssnNo}", produces = {MediaType.APPLICATION_JSON_VALUE})	
		@ApiOperation(value = "Verify ssnNo and return respective state name ", response = SSNVerifyResponse.class)
		public @ResponseBody ResponseEntity<SSNVerifyResponse>  verify_ssnNo(@PathVariable String ssnNo) throws SSNVerifyException
		{
				logger.debug("SSNNo_VerifierController.verify_ssnNo() got Execution has started..................");
				System.out.println("SSNNo_VerifierController.verify_ssnNo()");
				SSNVerifyResponse	ssnVerifyResponse		=		null;
				Long			ssn_No			=		null;
				
			//convert String to long type
			try {
					ssn_No				=		Long.parseLong(ssnNo);
					//invoke verifySSNNo method 
					ssnVerifyResponse		=		ssnService.verfiySsnNo(ssn_No);
			}
			catch(Exception e)
			{
				logger.debug("SSN No is not valid so please check once.......................");
				throw new SSNVerifyException();
			}
			logger.debug("SSNNo_VerifierController.verify_ssnNo() got Execution has finished..................");
			logger.info("SSNNo_VerifierController.verify_ssnNo() got Execution has completed..................");
			if(ssnVerifyResponse.getResponseCode()==200)
				return new ResponseEntity<SSNVerifyResponse>(ssnVerifyResponse,HttpStatus.OK);
			else
				return new ResponseEntity<SSNVerifyResponse>(ssnVerifyResponse,HttpStatus.BAD_REQUEST);
		}
}
