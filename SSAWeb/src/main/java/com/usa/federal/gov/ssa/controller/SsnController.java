package com.usa.federal.gov.ssa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.usa.federal.gov.ssa.constants.SsaConstants;
import com.usa.federal.gov.ssa.exception.SsaAppException;
import com.usa.federal.gov.ssa.model.SsnModel;
import com.usa.federal.gov.ssa.properties.SsaProperty;
import com.usa.federal.gov.ssa.service.SsnService;
import com.usa.federal.gov.ssa.validator.SsaValidator;

/**
 * this class having the Integration logic
 * 
 * @author shrikant
 *
 */
@Controller("ssnController")
public class SsnController {

	 private static final Logger  logger= LoggerFactory.getLogger(SsnController.class);
	 @Autowired
	private SsnService service;
	@Autowired
	private  SsaProperty	SSA_PROPERTY	;
	@Autowired
	private SsaValidator	ssa_Validator;	
	@GetMapping(value = { "/", "/welcome.htm" })
	public String showHome() {
		logger.debug("Show Home method (Get Request)  is started........................");
		logger.debug("Show Home method (Post Request) is ended........................");
		logger.info("Show Home Method (Get Request) is successfully completed....");	
		return "home";
	}

	/**
	 * this method is to launch the Home page
	 * 
	 * @param model :- it is used to bind with the form data
	 * @return logical view name (i.e. home page)
	 * @throws SsaAppException 
	 */
	@GetMapping(value = "/register.htm")
	public String showHome(Model model) throws SsaAppException  {
		logger.debug("Show Home  method (Post Request)  is started........................");
		SsnModel ssnModel = null;
		// create the object of an ssnModel
		ssnModel = new SsnModel();
		// Add ssnModel Object to the model Object for showing initial values
		model.addAttribute("ssnModel", ssnModel);
		// invoke method for initializing the form data
		initializeFormData(model);
		//Write Logger Messages
		logger.debug("Show Home  method (Post Request)  is completed........................");
		logger.info("show home(Post Request) execution successfully completed..............");
		return "ssn_register";
	}

	/**
	 * this method is used to initialize the binding object
	 * 
	 * @param model : map with ssn_register
	 * @throws SsaAppException 
	 */
	@ExceptionHandler(SsaAppException.class)
	private void initializeFormData(Model model) throws SsaAppException {
		List<String> stateList = 	null;
		logger.debug("initializeFormData() method execution started...........");
		logger.debug("service.getStateNames() is invoked....");
		//fetch the all state details
		stateList		=		service.getStateNames();
		logger.debug("service.getStateNames() executed successfully....");
		if(stateList.isEmpty())
		{
			logger.warn("Database table not having any state Record so please look once to database..............");
			throw new SsaAppException();	
		}
			// add all state details to model Attribute
		model.addAttribute("states", stateList);
		logger.debug("States Details added to model attribute...");	
		logger.debug("initializeFormData() method  execution ended........");
		logger.info("initializeFormData() method  execution successfully done...........");
	}

	/**
	 * this method is used to insert a record to DB
	 * 
	 * @param model    :- this is used to get model attribute data
	 * @param ssnModel :- hold the business data for passing to service class
	 * @return logical view name
	 * @throws SsaAppException 
	 */
	@PostMapping(value = "/register.htm")
	@ExceptionHandler(SsaAppException.class)
	public String registerSsn(Model model, @Valid @ModelAttribute("ssnModel") SsnModel ssnModel,BindingResult errors,HttpServletRequest  req) throws SsaAppException {
		logger.debug("registerSsn() method got started execution.........................");	
		Long ssn_No = null;
		  Map<String, String> SSA_PROPERTIES=SSA_PROPERTY.getSsaProperties();
		  String  			clientSideValidationStatus =	null;
		  //get the request parameter
		  clientSideValidationStatus		=		req.getParameter("clientSideValidationStatus");
		  if(clientSideValidationStatus.equalsIgnoreCase("no")) {
			  logger.debug("Server Side client inputs Validation started.....................");
			  			//validating the client supplied inputs
			  				if(ssa_Validator.supports(SsnModel.class)) {
			  					 	ssa_Validator.validate(ssnModel, errors);
							}
						//check if client data having error or not 
			  			if(errors.hasErrors()) {
			  				logger.info("Client inputs having some issues.......");
			  					initializeFormData(model);
			  					return "ssn_register";
			  			}
			  		  logger.debug("Server Side client inputs Validation completed.....................");
		  }			
		else {
										// invoke the service method for enrolling
								try {
											logger.debug("service.enrollSSN() method got invoked"); 
											ssn_No = service.enrollSSN(ssnModel);
											logger.debug("service.enrollSSN() method got successfully executed");		
										}
								catch(Exception e)
								{
									logger.debug("Failed to Enroll SSN....."+e.getMessage());
									throw new SsaAppException();
								}
								System.out.println(ssn_No);
								// check the ssnNo weather it empty or not
								if (ssn_No != null && ssn_No > 0) {
										// success message	
									model.addAttribute(SsaConstants.ENROLL_SUCCESS, SSA_PROPERTIES.get(SsaConstants.SUCCESS_MESSAGE));
								}
								else {
												// failure message
												model.addAttribute(SsaConstants.ENROLL_FAILURE, SSA_PROPERTIES.get(SsaConstants.FAILURE_MESSAGE));
								}
				}
		  logger.debug("registerSsn() method execution got completed.........................");
		  logger.info("registerSsn() method executed successfully done..................");
		return "success_view";
		
	}
	@GetMapping(value = "/getAllSsndetails.htm")
	public String getAllSsnDetails(Model model) {
		logger.debug("getAllSsnDetails() method execution started....................................."); 
		List<SsnModel> ssnModelsList = null;
		// create arraylist object for ssnmodellist
		ssnModelsList = new ArrayList<SsnModel>();
		// invoke the service method for getting the details
		ssnModelsList = service.fetchAllSsnRecords();
		if(ssnModelsList.isEmpty()) {
				logger.info("No SSNRecord available in Database.....................");
		}
		//Add the SsnListModel to model Attribute 
		model.addAttribute("ssnModelsList", ssnModelsList);
		System.out.println(ssnModelsList);
		logger.debug("getAllSsnDetails() method execution ended....................................."); 
		logger.info("getAllSsnDetails() method execution completed....................................."); 
		return "all_Ssn_details";
	}
}
