package com.projecttracker.model;

public class RestResult {

	private boolean success;
	private String message;
	private Object data;
	private String stackTrace;

	public static RestResult negativeInstance() {
		RestResult res = new RestResult();
		res.setSuccess(false);
		return res;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

}
