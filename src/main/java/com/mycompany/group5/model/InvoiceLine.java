package com.mycompany.group5.model;

public class InvoiceLine {

    private String productId;
    private String productName;
    private long unitPrice;
    private int quantity;
    private long lineTotal;

    public InvoiceLine(String productId, String productName, long unitPrice, int quantity) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product id must not be null or empty.");
        }
        String newId = productId.trim().toUpperCase();
        if (newId.length() < 3 || newId.length() > 10 || !newId.matches("[A-Z0-9]+")) {
            throw new IllegalArgumentException("Product id must have 3 to 10 characters and contain only letters and digits.");
        }

        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be null or empty.");
        }
        String newName = productName.trim();
        if (newName.length() > 80) {
            throw new IllegalArgumentException("Product name must not exceed 80 characters.");
        }

        if (unitPrice <= 0 || unitPrice > 10000000) {
            throw new IllegalArgumentException("Unit price must be greater than 0 and must not exceed 10000000.");
        }

        if (quantity < 1 || quantity > 1000) {
            throw new IllegalArgumentException("Quantity must be from 1 to 1000.");
        }

        this.productId = newId;
        this.productName = newName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.lineTotal = unitPrice * quantity;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public long getUnitPrice() {
        return this.unitPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public long getLineTotal() {
        return this.lineTotal;
    }
}