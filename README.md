# 🛒 Fawry Internship Challenge - E-Commerce System

## ✅ Features

### 🧾 Products
- Products can be **expirable** (e.g., Cheese, Biscuits) or non-expirable (e.g., TV).
- Products can be **shippable** (e.g., Cheese, TV) or not (e.g., Scratch Cards).
- Shippable products include their **weight** in kilograms.

### 🛒 Cart
- Customers can add products to the cart in valid quantities.
- Prevents adding more than the available stock.

### 💳 Checkout
- Calculates:
  - Subtotal
  - Shipping fees (`10` per shippable product)
  - Total paid amount
  - Updated customer balance
- Validates:
  - Cart is not empty
  - Products are in stock
  - Products are not expired
  - Customer has enough balance

### 📦 Shipping Notice
- Displays quantity, product name, and total weight per product in grams.
- Shows total shipment weight in kilograms.



## 🧪 Example Console Output


** Shipment notice **
2x Cheese        400g
1x Biscuits      700g
Total package weight 1.1kg

** Checkout receipt **
2x Cheese        200
1x Biscuits      150
1x ScratchCard   50
----------------------
Subtotal         400
Shipping         20
Amount           420
Balance left     580




## 📁 Project Structure


com.fawry.store/
├── Product.java
├── Expirable.java
├── Shippable.java
├── Cheese.java
├── Biscuits.java
├── TV.java
├── ScratchCard.java
├── Customer.java
├── CartItem.java
├── Cart.java
├── ShippingService.java
└── ECommerceApp.java




## 🚀 How to Run

1. Open the project in **IntelliJ IDEA** or any Java IDE.
2. Run the `ECommerceApp` class.
3. Observe the shipment notice and checkout receipt in the console.



