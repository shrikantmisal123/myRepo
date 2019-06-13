package com.usa.federal.gov.ssa.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class SpringMvcAppConfig {
	
	 @Bean("messageSource")
	    public MessageSource messageSource() {
	    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	        messageSource.setBasename("errorMessages");
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    }		
}
