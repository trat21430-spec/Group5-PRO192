
package com.mycompany.group5.model;
    import com.mycompany.group5.exception.SaleValidationException;
import java.time.LocalDate;
public class RegularProduct extends Product{
 private static final int MAX_SALE_QUANTITY =1000;

    public RegularProduct(String id, String name, long price, int inventory){
        super(id, name, price, inventory);
    }

    @Override
    public void validateSale(LocalDate saleDate, int quantity){
         if (saleDate ==null){
            throw new SaleValidationException("Sale date must not be null");
        }
        if (quantity <1 || quantity >MAX_SALE_QUANTITY){
            throw new SaleValidationException("Quantity must be from 1 to" +MAX_SALE_QUANTITY +"product" +super.getId());
        }
        if (quantity >super.getInventory()){
            throw new SaleValidationException("Not enough stock for product" +super.getId()+ ":available" +super.getInventory() +"requested" +quantity);
        }
        }
    }
