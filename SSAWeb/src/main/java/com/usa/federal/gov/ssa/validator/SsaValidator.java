package com.usa.federal.gov.ssa.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.usa.federal.gov.ssa.constants.SsaConstants;
import com.usa.federal.gov.ssa.model.SsnModel;
@Component(value = "ssaValidator")
public class SsaValidator implements Validator {
	Logger		logger		=	LoggerFactory.getLogger(SsaValidator.class);
	@Override
	public boolean supports(Class<?> clazz) {
				return clazz.isAssignableFrom(SsnModel.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.debug("SsaValidator.validate() method Execution has started....................");
		SsnModel											ssnModel			=		null;
		//create Model Class Object
		ssnModel					=	(SsnModel) target;
		//create Map Collection Object
					if(ssnModel.getFirst_Name()==""||ssnModel.getFirst_Name().length()==0) {
												logger.error("user FirstName is Empty.....");
												errors.rejectValue(SsaConstants.SSNMODEL_first_Name,SsaConstants.EMPTY_FIRSTNAME_ERRORCODE);
												return;
					}
					if(ssnModel.getLast_Name()==""||ssnModel.getLast_Name().length()==0) {	
												logger.error("user LastName is Empty.....");
												errors.rejectValue(SsaConstants.SSNMODEL_last_Name,SsaConstants.EMPTY_LASTNAME_ERRORCODE);
												return;
					}
					if(ssnModel.getDob()==null || ssnModel.getDob().length()==0) {
												logger.error("user DOB is empty......................");
												errors.rejectValue(SsaConstants.SSNMODEL_dob, SsaConstants.EMPTY_DOB_ERRORCODE);
												return ;
					}
					if(ssnModel.getPhoneNo()==null||ssnModel.getPhoneNo().toString().length()==0) {
												logger.error("user Phone No is Empty...................");
												errors.rejectValue(SsaConstants.SSNMODEL_phoneNo,SsaConstants.EMPTY_PHONE_ERRORCODE);
												return ;
					}
					if(ssnModel.getPhoto().isEmpty())
					{
												logger.error("user photo not selected");
												errors.rejectValue(SsaConstants.SSNMODEL_photo,SsaConstants.EMPTY_PHOTO_ERRORCODE);
												return ;
					}
					logger.debug("SsaValidator.validate() method Execution has finished....................");
					logger.info("SsaValidator.validate() method Execution has completed....................");
		}

}
