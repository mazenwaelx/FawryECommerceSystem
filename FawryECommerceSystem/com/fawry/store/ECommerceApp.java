package com.fawry.store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ECommerceApp {

    public static void main(String[] args) {
        Customer customer = new Customer("Ahmed", 1000);
        Cart cart = new Cart();

        Product cheese = new Cheese("Cheese", 100, 5, LocalDate.now().plusDays(3), 0.2);
        Product biscuits = new Biscuits("Biscuits", 150, 2, LocalDate.now().plusDays(2), 0.7);
        Product tv = new TV("TV", 5000, 1, 8.5);
        Product card = new ScratchCard("ScratchCard", 50, 5);

        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(card, 1);

        try {
            checkout(cart, customer);
        } catch (Exception e) {
            System.err.println("Checkout failed: " + e.getMessage());
        }
    }

    public static void checkout(Cart cart, Customer customer) {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty.");

        double subtotal = 0;

        // Step 1: Validate items and calculate subtotal
        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();

            if (p instanceof Expirable && ((Expirable) p).isExpired()) {
                throw new IllegalStateException("Product expired: " + p.getName());
            }

            if (item.getQuantity() > p.getQuantity()) {
                throw new IllegalStateException("Insufficient stock for: " + p.getName());
            }

            subtotal += item.getTotalPrice();
        }

        // Step 2: Calculate shipping fees
        long shippableItemCount = cart.getItems().stream()
                .filter(item -> item.getProduct() instanceof Shippable)
                .count();
        double shipping = 10 * shippableItemCount;

        // Step 3: Calculate total
        double total = subtotal + shipping;

        if (customer.getBalance() < total) {
            throw new IllegalStateException("Customer has insufficient balance.");
        }

        // Step 4: Deduct stock
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceStock(item.getQuantity());
        }

        // Step 5: Deduct from customer
        customer.deductBalance(total);

        // Step 6: Ship items
        ShippingService.shipItems(cart.getItems());

        // Step 7: Print receipt
        System.out.println("\n** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %-12s %.0f%n", item.getQuantity(), item.getProduct().getName(), item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal         %.0f%n", subtotal);
        System.out.printf("Shipping         %.0f%n", shipping);
        System.out.printf("Amount           %.0f%n", total);
        System.out.printf("Balance left     %.0f%n", customer.getBalance());
    }
}