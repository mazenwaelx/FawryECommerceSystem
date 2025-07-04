package com.fawry.store;

import java.time.LocalDate;

public class Biscuits extends Product implements Expirable, Shippable {
    private final LocalDate expiryDate;
    private final double weight;

    public Biscuits(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }
}
