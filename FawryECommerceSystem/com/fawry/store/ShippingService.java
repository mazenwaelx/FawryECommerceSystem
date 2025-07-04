package com.fawry.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {

    public static void shipItems(List<CartItem> cartItems) {
        System.out.println("** Shipment notice **");

        Map<String, Double> productWeights = new HashMap<>();
        Map<String, Integer> productQuantities = new HashMap<>();
        double totalWeight = 0;

        for (CartItem item : cartItems) {
            Product p = item.getProduct();
            if (p instanceof Shippable) {
                String name = p.getName();
                double weight = ((Shippable) p).getWeight();
                int qty = item.getQuantity();

                productWeights.put(name, weight); // same weight per unit
                productQuantities.put(name, productQuantities.getOrDefault(name, 0) + qty);

                totalWeight += weight * qty;
            }
        }

        for (String name : productQuantities.keySet()) {
            int qty = productQuantities.get(name);
            double weight = productWeights.get(name);
            System.out.printf("%dx %-12s %.0fg%n", qty, name, weight * qty * 1000);
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}
