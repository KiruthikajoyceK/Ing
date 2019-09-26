package com.hcl.bank.dto;

import java.security.SecureRandom;
import java.util.Random;


public class RandomPasswordGenerator {
 
	 private static SecureRandom random = new SecureRandom();

	    /** different dictionaries used */
	   
	    @SuppressWarnings("unused")
		private static final String NUMERIC = "0123456789";
	    @SuppressWarnings("unused")
		private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";

	    /**
	     * Method will generate random string based on the parameters
	     * 
	     * @param len
	     *            the length of the random string
	     * @param dic
	     *            the dictionary used to generate the password
	     * @return the random password
	     */
	    public static String generatePassword(int len, String dic) {
	    	
		String result = "";
		for (int i = 0; i < len; i++) {
		    int index = random.nextInt(dic.length());
		    result += dic.charAt(index);
		}
		return result;
	    }
	
	
		private static final Random randomm = new Random();

		public static long random(int min, int max) {
		    return randomm.nextInt(max) + min;
		}
		
	
}