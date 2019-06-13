package com.usa.federal.gov.ssa.exception;

public class SsaAppException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public SsaAppException()
	{
		super();
	}
	public SsaAppException(String errMsg)
	{
		super(errMsg);
	}
}
