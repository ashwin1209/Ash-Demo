package com.target.myretail.response;

import java.util.List;

/**
 * @author Ashwin
 *
 * @param <T>
 */
public class EntitiesResponse<T> extends ServiceResponse {
	private List<T> entities;

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}
	
	public String getType() {
		return "EntitiesResponse";
	}
}
