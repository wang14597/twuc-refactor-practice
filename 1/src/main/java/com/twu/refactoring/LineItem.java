package com.twu.refactoring;

public class LineItem {
	private String description;
	private double price;
	private int quantity;

	public LineItem(String Description, double price, int quantity) {
		this.description = Description;
		this.price = price;
		this.quantity = quantity;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

    double totalAmount() {
        return price * quantity;
    }

    double salesTax() {
		return totalAmount() * 0.1;
    }

	@Override
	public String toString() {
		return description + "\t" +
				price + "\t" +
				quantity + "\t" +
				totalAmount() + "\n";
	}
}