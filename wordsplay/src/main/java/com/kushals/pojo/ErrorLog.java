package com.kushals.pojo;

public class ErrorLog {

	private String httpStatus;
	private String errorMessage;

	public ErrorLog(String httpStatus, String errorMessage) {
		super();
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
