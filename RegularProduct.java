package com.mycompany.group5.model;

import com.mycompany.group5.exception.SaleValidationException;
import java.time.LocalDate;

public class RegularProduct extends Product {

    public RegularProduct(String id, String name, long price, int inventory) {
        super(id, name, price, inventory);
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
    }
}
