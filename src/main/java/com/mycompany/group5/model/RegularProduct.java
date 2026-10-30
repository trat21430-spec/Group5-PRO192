
package com.mycompany.group5.model;

import java.time.LocalDate;
public class RegularProduct extends Product{


    public RegularProduct(String id, String name, long price, int inventory){
        super(id, name, price, inventory);
    }

    @Override
    public void validateSale(LocalDate saleDate, int quantity){
         if (saleDate ==null){
            throw new IllegalArgumentException("Sale date must not be null");
        }
        if (quantity <1 || quantity >1000){
            throw new IllegalArgumentException("Quantity must be from 1 to" +1000 +"product" +super.getId());
        }
        if (quantity >super.getInventory()){
            throw new IllegalArgumentException("Not enough stock for product" +super.getId()+ ":available" +super.getInventory() +"requested" +quantity);
        }
        }
    }
