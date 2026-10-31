/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.group5.model;

/**
 *
 * @author ACER
 */
public class InvoiceLine {
    private String productId;
    private String productName;
    private long unitPrice;
    private int quantity;
    private long lineTotal;

  
    public InvoiceLine(String productId, String productName, long unitPrice, int quantity) {
     
        if (this.productId == null || this.productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product id must not be null or empty.");
        }
        
        String newId = productId.trim().toUpperCase();
        if (newId.length() < 3 || newId.length() > 10 || !newId.matches("[A-Z0-9]+")) {
            throw new IllegalArgumentException("Product id must have 3 to 10 characters and only letters and digits.");
        }
   
        if (this.productName == null || this.productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be null or empty.");
        }
        String newName = productName.trim();
     
        if (newName.length() > 80) {
            throw new IllegalArgumentException("Product name must not exceed 80 characters.");
        }
        if (this.unitPrice <= 0 || this.unitPrice > 10000000) {
            throw new IllegalArgumentException("Unit price must be greater than 0 and must not exceed 10000000.");
        }
        if (this.quantity < 1 || this.quantity > 1000) {
            throw new IllegalArgumentException("Quantity must be from 1 to 1000.");
        }
       
      
        this.productId = newId;
        this.productName = newName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.lineTotal = unitPrice * quantity;
    }

    public InvoiceLine(Product product, int quantity) {
        this(
            product != null ? product.getId() : null,
            product != null ? product.getName() : null,
            product != null ? product.getPrice() : 0,
            quantity
        );
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getLineTotal() {
        return this.lineTotal;
    }

    public void setLineTotal(long lineTotal) {
        this.lineTotal = lineTotal;
    }
}