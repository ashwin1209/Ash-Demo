package com.target.myretail.exceptions;

import javax.ws.rs.core.Response;

/**
 * @author Ashwin
 *
 */
public class BaseError {

	private int code;

	private String type;

	private String message;

	private String errorCode;

	public BaseError() {
		this.code = Response.Status.BAD_REQUEST.getStatusCode();
		this.message = "";
		this.errorCode = "";
	}

	public BaseError(int code, String type, String message, String errorCode) {
		this.code = code;
		this.type = type;
		this.message = message;
		this.errorCode = errorCode;

	}

	public BaseError(Response.StatusType statusCode, String message, Object object) {

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
