package com.demo.ezypay.model;

import java.time.LocalDate;
import java.util.List;

public class Subscription {

    private double amount;
    private Subtype type;
    private List<LocalDate> invoice;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Subtype getType() {
        return type;
    }

    public void setType(Subtype type) {
        this.type = type;
    }

    public List<LocalDate> getInvoices() {
        return invoice;
    }

    public void setInvoices(List<LocalDate> invoice) {
        this.invoice = invoice;
    }
}
