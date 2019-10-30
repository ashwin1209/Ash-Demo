package com.target.myretail.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

/**
 * @author Ashwin
 *
 */
public class BadRequestException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException() {
		this(null, null);
	}

	public BadRequestException(String message) {
		this(message, null);
	}

	public BadRequestException(Throwable cause) {
		this(null, cause);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadRequestException(BaseError error) {
		super(error);
	}

	@Override
	public StatusType getHttpStatus() {
		// TODO Auto-generated method stub
		return Response.Status.BAD_REQUEST;
	}
}
