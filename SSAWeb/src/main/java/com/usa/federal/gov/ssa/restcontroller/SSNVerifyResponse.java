package com.usa.federal.gov.ssa.restcontroller;
import java.util.Date;

import lombok.Data;

@Data
public class SSNVerifyResponse {
	int responseCode;
	String responseMsg;
	Date responseDate;
}
