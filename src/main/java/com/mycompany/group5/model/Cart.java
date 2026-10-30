package com.mycompany.group5.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Cart: gio hang, quan ly Map<String, CartItem> (key = productId).
 * Chi dung kieu chuan cua Java + CartItem, khong phu thuoc Product/ProductService.
 */
public class Cart {

    private LocalDate saleDate;
    private Map<String, CartItem> items;
    private boolean confirmed;
    
    public Cart(LocalDate saleDate){
        this(saleDate, null, false);
    }
    public Cart(LocalDate saleDate, Map<String, CartItem> items, boolean confirmed){
            
            if(saleDate != null){
            this.saleDate = saleDate;
            }
            else{
                this.saleDate = LocalDate.now();
            }
            if(items != null){
                
               this.items = new LinkedHashMap<>(items);
            }
            else{
                this.items = new LinkedHashMap<>();
                }
            this.confirmed = confirmed;
    } 
    public boolean addItem(String productId, int quantity){
        if(this.confirmed || productId == null || productId.trim().isEmpty() || quantity < 1 || quantity > 1000 ){
            return false;
        }
        String id = productId.trim().toUpperCase();
        CartItem existing = this.items.get(id);
        if (existing != null){
            if (existing.getQuantity() + quantity > 1000){
                return false;
            }
            existing.addQuantity(quantity);
            return true;
        }else{
            if (this.items.size() >= 20){
                return false;
            }
            this.items.put(id, new CartItem(id, quantity));
            return true;
        }
    }
    public boolean updateQuantity(String productId, int newQuantity){
        if(this.confirmed || productId == null || newQuantity < 1 || newQuantity > 1000){
            return false;
    }
    String id = productId.trim().toUpperCase();
    CartItem item = this.items.get(id);
        if (item == null){
            return false;
        }
    items.setQuantity(newQuantity);
    return true;
    }
    public boolean removeItem(String productId){
        if (this.confirmed || productId == null){
            return false;
        }
        String id = productId.trim().toUpperCase();
        return this.items.remove(id) != null;
    }

    public LocalDate getSaleDate(){
        return this.saleDate;
        }

    public Map<String, CartItem> getItems(){
        return Collections.unmodifiableMap(this.items);
        }

    public boolean isConfirmed(){
        return this.confirmed;
        }

    public void setConfirmed(boolean confirmed){
        this.confirmed = confirmed;
        }
    
    public int getItemCount(){
        return this.items.size();
    }
}