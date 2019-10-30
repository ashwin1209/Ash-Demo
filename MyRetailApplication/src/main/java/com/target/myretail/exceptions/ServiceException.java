package com.target.myretail.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.Response;

/**
 * @author Ashwin
 *
 */
public abstract class ServiceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final BaseError error;

	protected ServiceException(String message, Throwable cause) {
		super(message, cause);
		this.error = new BaseError(getHttpStatus(), message(), null);
	}

	protected ServiceException(BaseError error) {
		super(error.getMessage());
		this.error = error;
	}

	protected Response.StatusType code() {
		return getHttpStatus();
	}

	protected String message() {
		if (getMessage() != null) {
			return getMessage();
		}
		if (getHttpStatus() != null) {
			return getHttpStatus().getReasonPhrase();
		}
		return null;
	}

	protected String innerError() {
		StringWriter sw = new StringWriter();
		printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	public abstract Response.StatusType getHttpStatus();

	public BaseError getBaseError() {
		return this.error;
	}
}
