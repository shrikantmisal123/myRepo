package com.usa.federal.gov.ssa.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/** this class is used to load the properties from property file
 * @author shrikant
 *
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ssa")
public class SsaProperty {
		 private static Map<String,String>  SsaProperties	;		
		public   Map<String, String> getSsaProperties() {
			return SsaProperties;
		}

		public void setSsaProperties(Map<String, String> ssaProperties) {
			SsaProperties = ssaProperties;
		}
		
}
