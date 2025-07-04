package com.fawry.store;

public class TV extends Product implements Shippable {
    private final double weight;

    public TV(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }
}
