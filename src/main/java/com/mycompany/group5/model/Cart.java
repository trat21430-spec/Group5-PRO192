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
    private Map<String, CartItem> item;
    private boolean confirmed;

    public Cart(LocalDate saleDate, Map<String, CartItem> item, boolean confirmed){
            if(saleDate == null){
            this.saleDate = saleDate;
            }
            if(item != null){
                this.item = item.trim().toUpperCase();
            }else{this.item = "";}
            this.confirmed = confirmed;
    } 
    public boolean addItem(String productId, int quantity){
        if(productId == null || quantity < 0 && quantity > 1000){
            this.productId = productId.trim().toUpperCase();
        }
    }

    public boolean updateQuantity(String productId, int newQuantity){}

    public boolean removeItem(String productId){}

    public LocalDate getSaleDate(){return this.saleDate;}

    public Map<String CartItem> getItem(){return this.items;}

    public boolean isConfirmed(){return this.confirmed = confirmed;}

    public void setConfirmed(boolean confirmed){this confirmed;}
    
    public int getItemCount(){}
}