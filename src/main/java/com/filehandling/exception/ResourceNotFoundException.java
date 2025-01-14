package com.filehandling.exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * Author: Asif Khan
	 * Designation: Senior Software Engineer
	 * Date: 01/10/2024
	 */
	 
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String message) {
	        super(message);
	    }

}
