package com.exceptions;

public class XBoxTagException extends MC_Exception {
	private int responseCode;
	
	public XBoxTagException() {
		// TODO Auto-generated constructor stub
	}
	public XBoxTagException(int responseCode)
	{
		this.responseCode = responseCode;
	}
	public XBoxTagException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public XBoxTagException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public XBoxTagException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public XBoxTagException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public int getResponseCode() {
		return responseCode;
	}

}
