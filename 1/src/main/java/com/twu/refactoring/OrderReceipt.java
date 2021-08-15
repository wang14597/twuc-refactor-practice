package com.twu.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        output.append("======Printing Orders======\n");
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
        double totalPrice = getTotalPrice(output);
        output.append("Sales Tax").append('\t').append(totalPrice / 11);
        output.append("Total Amount").append('\t').append(totalPrice);
        return output.toString();
    }

    private double getTotalPrice(StringBuilder output) {
        double totalPrice = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            extracted(output, lineItem);
            totalPrice += lineItem.totalAmount() + lineItem.salesTax();
        }
        return totalPrice;
    }

    private void extracted(StringBuilder output, LineItem lineItem) {
        output.append(lineItem.toString());
    }
}