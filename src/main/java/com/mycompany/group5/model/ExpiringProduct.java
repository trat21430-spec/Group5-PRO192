package com.mycompany.group5.model;

import java.time.LocalDate;
    
public class ExpiringProduct extends Product{
    private static final int MAX_SALE_QUANTITY = 1000;
    private LocalDate expiryDate;

    public ExpiringProduct(LocalDate expiryDate, String id, String name, long price, int inventory) {
        super(id, name, price, inventory);
        if(expiryDate ==null){
            throw new IllegalArgumentException("Expiry date must not be null.");
        }
        this.expiryDate = expiryDate;
    }

  @Override
    public void validateSale(LocalDate saleDate, int quantity){
        if (saleDate ==null){
            throw new SaleValidationException("Sale date must not be null");
        }
        if (quantity < 1 || quantity >MAX_SALE_QUANTITY) {
            throw new SaleValidationException("Quantity must be from 1 to"+ MAX_SALE_QUANTITY + "product" + super.getId());
        }
        if (quantity > super.getInventory()){
            throw new SaleValidationException("Not enough stock for product" + super.getId()+ ":available" + super.getInventory() + "requested" + quantity);
        }
        if (saleDate.isAfter(this.expiryDate)) {
            throw new SaleValidationException("Product" +super.getId()+"expired on" +this.expiryDate);
        }
    }
    public LocalDate getExpiryDate(){
        return this.expiryDate;
    }
}
