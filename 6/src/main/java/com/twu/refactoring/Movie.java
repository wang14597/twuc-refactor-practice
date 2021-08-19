package com.twu.refactoring;

public class Movie {

	private String title;
	private int priceCode;

	public Movie(String title, int priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int arg) {
    	priceCode = arg;
	}

	public String getTitle () {
		return title;
	}
}

