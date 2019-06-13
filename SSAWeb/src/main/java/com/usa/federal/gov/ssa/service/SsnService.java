package com.usa.federal.gov.ssa.service;

import java.util.List;

import com.usa.federal.gov.ssa.model.SsnModel;
import com.usa.federal.gov.ssa.restcontroller.SSNVerifyResponse;

public interface SsnService {
	public List<String> getStateNames();  
	public Long enrollSSN(SsnModel  ssnModel);
	public List<SsnModel>  fetchAllSsnRecords();
	public SSNVerifyResponse verfiySsnNo(Long ssN_No);
}
