package io.apicode.profile.model;

public class APIException extends Exception {

	private static final long serialVersionUID = 7705275064137301494L;

	String message;

	public APIException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
