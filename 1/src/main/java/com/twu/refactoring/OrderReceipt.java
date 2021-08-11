package com.twu.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 * 
 */
public class OrderReceipt {
    private Order o;

    public OrderReceipt(Order o) {
        this.o = o;
	}

	public String printReceipt() {
		StringBuilder output = new StringBuilder();
		output.append("======Printing Orders======\n");
        output.append(o.getCustomerName());
        output.append(o.getCustomerAddress());

		double totSalesTx = 0d;
		double tot = 0d;
		for (LineItem lineItem : o.getLineItems()) {
			extracted(output, lineItem);
			double salesTax = lineItem.totalAmount() * .10;
            totSalesTx += salesTax;
            tot += lineItem.totalAmount() + salesTax;
		}

		output.append("Sales Tax").append('\t').append(totSalesTx);
		output.append("Total Amount").append('\t').append(tot);
		return output.toString();
	}

	private void extracted(StringBuilder output, LineItem lineItem) {
		output.append(lineItem.getDescription());
		output.append('\t');
		output.append(lineItem.getPrice());
		output.append('\t');
		output.append(lineItem.getQuantity());
		output.append('\t');
		output.append(lineItem.totalAmount());
		output.append('\n');
	}
}