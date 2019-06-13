package com.usa.federal.gov.ssa.exception;

public class SSNVerifyException extends Exception {
	private static final long serialVersionUID = 1L;
	public SSNVerifyException()
	{
		super("Ssn No is not valid");
	}
	SSNVerifyException(String ssnErrorMsg)
	{
		super(ssnErrorMsg);
	}
}
