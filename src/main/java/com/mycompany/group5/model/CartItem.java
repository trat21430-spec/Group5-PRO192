package com.mycompany.group5.model;
 
/**
 * CartItem: một dòng trong giỏ hàng.
 * Chỉ lưu productId và quantity (không phụ thuộc Product).
 */
public class CartItem {
 
    private String productId;
    private int quantity;
 
    public CartItem(String productId, int quantity) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("productId must not be null or blank");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }
        this.productId = productId.trim();
        this.quantity = quantity;
    }
 
    public String getProductId() {
        return productId;
    }
 
    public int getQuantity() {
        return quantity;
    }
 
    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }
        this.quantity = quantity;
    }
 
    public void addQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than 0");
        }
        this.quantity += amount;
    }
}