package com.exceptions;

public class XboxException extends MC_Exception {
	private int responseCode;

	public XboxException() {
		// TODO Auto-generated constructor stub
	}

	public XboxException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public XboxException(int responseCode)
	{
		this.responseCode = responseCode;
	}

	public XboxException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public XboxException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public XboxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public int getResponseCode() {
		return responseCode;
	}
	

}
