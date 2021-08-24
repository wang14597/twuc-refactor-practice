package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

	private final String name;
	private final ArrayList<Rental> rentalList = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}


	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = rentalList.iterator();
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental each = rentals.next();
			int priceCode = each.getMovie().getPriceCode();
			thisAmount = each.getThisAmount(thisAmount, priceCode);
			frequentRenterPoints++;
			if ((priceCode == MovieType.NEW_RELEASE) && each.getDaysRented() > 1)
				frequentRenterPoints++;
			result.append("\t").append(each.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
			totalAmount += thisAmount;
		}
		result.append("Amount owed is ")
				.append(totalAmount)
				.append("\n")
				.append("You earned ")
				.append(frequentRenterPoints)
				.append(" frequent renter points");
		return result.toString();
	}

}
