package com.target.myretail.responsebuilder;

/**
 * @author Ashwin
 *
 */
public class ResponseBuilderFactory {
	
	public static ResponseBuilder getBuilder(String responseType) {
		ResponseBuilder responseBuilder;
		
		if("EntityResponse".equals(responseType)) {
			responseBuilder  = new EntityResponseBuilder();
		}else if("EntityResponse".equals(responseType)) {
			responseBuilder = new EntitiesResponseBuilder();
		}else {
			responseBuilder = new ErrorResponseBuilder();
		}
		return responseBuilder;
	}
}
