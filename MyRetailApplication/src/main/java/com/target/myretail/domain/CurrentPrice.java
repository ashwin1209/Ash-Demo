package com.target.myretail.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ashwin
 *
 */
@Document
public class CurrentPrice {
	/**
	 * value
	 */
	private double value;
	/**
	 * currency_code
	 */
	private String currency_code;
	/**
	 * @return
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * @return
	 */
	public String getCurrency_code() {
		return currency_code;
	}

	/**
	 * @param currency_code
	 */
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

}
