package com.mycompany.group5.model;
 
/**
 * CartItem: mot dong trong gio hang.
 * Chi luu productId va quantity (khong phu thuoc Product).
 */
public class CartItem {

    private String productId;
    private int quantity;
    
    public CartItem(String productId, int quantity) {
            
            if(productId == null || productId.trim().isEmpty()){
                throw new IllegalArgumentException("Product id must not be null or empty.") ;
            }
            
            if(quantity < 1 || quantity > 1000){
                throw new IllegalArgumentException("Quantity must be from 1 to 1000.");
            }
            this.productId = productId.trim().toUpperCase();
            this.quantity = quantity;
}
    public String getProductId(){
        return this.productId;
        }
    public int getQuantity(){
        return this.quantity;
        }
    public void setQuantity(int quantity){
        if (quantity < 1 || quantity > 1000){
            throw new IllegalArgumentException("Quantity must be from 1 to 1000.");
        }else {
            this.quantity = quantity;
            }
    }
    public void addQuantity(int amount){
        if (amount < 1){
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
        int newQuantity = this.quantity + amount;
        if (newQuantity > 1000){
            throw new IllegalArgumentException("Total quantity for a line must not exceed 1000.");
        } 
        this.quantity += amount;
    }
}
