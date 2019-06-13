package com.nt.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DemoDate {
		public static void main(String[] args) throws ParseException {
			SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
			//Getting current date
			Calendar cal = Calendar.getInstance();
			//Displaying current date in the desired format
			System.out.println("Current Date: "+sdf.format(cal.getTime()));
			   
			//Number of Days to add
		        cal.add(Calendar.DAY_OF_MONTH, 30);  
			//Date after adding the days to the current date
			String newDate = sdf.format(cal.getTime());  
			//Displaying the new Date after addition of Days to current date
			System.out.println("Date after Addition: "+newDate);}
}
