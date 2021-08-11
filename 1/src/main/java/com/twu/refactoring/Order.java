package com.twu.refactoring;

import java.util.List;

public class Order {
    String numberId;
    String address;
    List<LineItem> li;

    public Order(String numberId, String address, List<LineItem> lineItems) {
        this.numberId = numberId;
        this.address = address;
        this.li = lineItems;
    }

    public String getCustomerName() {
        return numberId;
    }

    public String getCustomerAddress() {
        return address;
    }

    public List<LineItem> getLineItems() {
        return li;
    }
}
