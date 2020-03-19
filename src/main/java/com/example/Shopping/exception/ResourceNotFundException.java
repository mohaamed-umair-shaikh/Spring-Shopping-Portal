package com.example.Shopping.exception;

public class ResourceNotFundException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	private long id;
	private String s;
	
	public ResourceNotFundException(long id) {
		this.id = id;
		
	}
	

	public String getMessage(){
		return "Resource Not Found: invalid id: " + id;
	}

}

