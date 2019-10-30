package com.target.myretail.response;

/**
 * @author Ashwin
 *
 * @param <T>
 */
public class EntityResponse<T> extends ServiceResponse {

	private T entityObj;

	public T getEntityObj() {
		return (T) entityObj;
	}

	public void setEntityObj(T entityObj) {
		this.entityObj = entityObj;
	}

	public String getType() {
		return "EntityResponse";
	}

}
