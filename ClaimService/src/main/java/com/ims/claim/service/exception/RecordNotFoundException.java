package com.ims.claim.service.exception;

public class RecordNotFoundException extends Exception {

	public RecordNotFoundException() {
		super("Resource not found.");
	}

	public RecordNotFoundException(String message) {
		super(message);
	}
	
}
