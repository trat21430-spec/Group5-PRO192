package com.mycompany.group5.model;

import java.time.LocalDate;

public abstract class Product {



    private String id;
    private String name;
    private long price;
    private int inventory;

    public Product(String id, String name, long price, int inventory) {
        if (id == null) {
            throw new IllegalArgumentException("Product id must not be null.");
        }
        String newId = id.trim().toUpperCase();
        if (newId.length() < 3 || newId.length() > 10) {
            throw new IllegalArgumentException("Product id must have 3 to 10 characters.");
        }
        if (!newId.matches("[A-Z0-9]+")) {
            throw new IllegalArgumentException("Product id must contain only letters and digits.");
        }
        this.id = newId;

        this.setName(name);
        this.setPrice(price);


        if (inventory < 0 || inventory > 100000) {
            throw new IllegalArgumentException("Inventory must be from 0 to " + 100000 + ".");
        }
        this.inventory = inventory;
    }

    public abstract void validateSale(LocalDate saleDate, int quantity);

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Product name must not be null.");
        }
         String newName = name.trim();
        if (newName.isEmpty()) {
            throw new IllegalArgumentException("Product name must not be empty.");
        }
        this.name = newName;
    }

    public long getPrice() {
        return this.price;
    }

    public void setPrice(long price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must not be negative.");
        }
        this.price = price;
    }

    public int getInventory() {
        return this.inventory;
    }

    public boolean addInventory(int amount) {
        if (amount <= 0) {
            return false;
        }
  
        if (amount > 100000 - this.inventory) {
            return false;
        }
        this.inventory += amount;
        return true;
    }

    public boolean deductInventory(int amount) {
        if (amount <= 0 || amount > this.inventory) {
            return false;
        }
        this.inventory -= amount;
        return true;
    }
}
