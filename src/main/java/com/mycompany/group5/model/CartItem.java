package com.mycompany.group5.model;
 
/**
 * CartItem: một dòng trong giỏ hàng.
 * Chỉ lưu productId và quantity (không phụ thuộc Product).
 */
public class CartItem {
 
    private String productId;
    private int quantity;
 
    public CartItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
 
    public String getProductId() {
        return productId;
    }
 
    public int getQuantity() {
        return quantity;
    }
 
    /** Ghi đè số lượng; bỏ qua nếu giá trị không hợp lệ (<= 0). */
    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }
 
    /** Cộng dồn số lượng; bỏ qua nếu amount không hợp lệ (<= 0). */
    public void addQuantity(int amount) {
        if (amount > 0) {
            this.quantity += amount;
        }
    }
}
 