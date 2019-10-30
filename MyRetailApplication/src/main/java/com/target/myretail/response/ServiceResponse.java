package com.target.myretail.response;

/**
 * @author Ashwin
 *
 */
public abstract class ServiceResponse implements OdataResponse {

	private String status = null;
	private String code = null;
	private String message = null;
	private String exception = null;
	private String contentType = null;
	private String type = null;

	public ServiceResponse() {

	}

	public ServiceResponse(String operation, String status, String code, String message) {
		this();
		setStatus(status);
		setCode(code);
		setMessage(message);
	}

	public boolean isRequestSuccessful() {
		return "200".equals(getCode());
	}

	public boolean isRequestCreated() {
		return "201".equals(getCode());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
