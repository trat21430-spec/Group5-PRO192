package com.mycompany.group5.model;

import com.mycompany.group5.exception.SaleValidationException;
import java.time.LocalDate;


public class ExpiringProduct extends Product {

    private LocalDate expiryDate;

    public ExpiringProduct(String id, String name, long price, int inventory, LocalDate expiryDate) {
        super(id, name, price, inventory);
        this.expiryDate = expiryDate;
    }

    @Override
    public void validateSale(LocalDate saleDate, int quantity) {
        if (quantity <= 0) {
            throw new SaleValidationException(
                    "So luong phai lon hon 0 (san pham " + getId() + ").");
        }
        if (quantity > getInventory()) {
            throw new SaleValidationException(
                    "Khong du hang trong kho cho san pham " + getId()
                    + ": con " + getInventory() + ", yeu cau " + quantity + ".");
        }
  
        if (saleDate.isAfter(expiryDate)) {
            throw new SaleValidationException(
                    "San pham " + getId() + " da het han ngay " + expiryDate + ".");
        }
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
