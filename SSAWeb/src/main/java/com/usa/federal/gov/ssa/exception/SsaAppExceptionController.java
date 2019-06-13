package com.usa.federal.gov.ssa.exception;

import java.util.Date;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usa.federal.gov.ssa.restcontroller.SSNVerifyResponse;

@Controller
@ControllerAdvice
public class SsaAppExceptionController implements ErrorController {
	
	@RequestMapping("/error")
    public String handleError() {
        return "error";
    }
	
	@Override
	public String getErrorPath() {
		return "/error";
	}
	
	 @ExceptionHandler(SSNVerifyException.class)
	    public final ResponseEntity<SSNVerifyResponse> handleUserNotFoundException(SSNVerifyException ex) {
		 	SSNVerifyResponse				ssnVerifyResponse		=		null;
		 	ssnVerifyResponse			=		new SSNVerifyResponse();
		 	ssnVerifyResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
		 	ssnVerifyResponse.setResponseMsg("Invalid ssnNo No.........Please make sure");
		 	ssnVerifyResponse.setResponseDate(new Date());
		 	return new ResponseEntity<SSNVerifyResponse>(ssnVerifyResponse,HttpStatus.BAD_REQUEST);
	    }
	
	 	@ExceptionHandler(SsaAppException.class)
	 	public String genrateSsaAppException(SsaAppException e)
	 	{
	 			return "error";
	 	}
}
