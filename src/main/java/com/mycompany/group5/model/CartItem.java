package com.mycompany.group5.model;
 
/**
 * CartItem: mot dong trong gio hang.
 * Chi luu productId va quantity (khong phu thuoc Product).
 */
public class CartItem {

    private String productId;
    private int quantity;
    
    public CartItem(String productId, int quantity) {
            //Chuan hoa ma san pham
            if(productId != null){
                this.productId = productId.trim().toUpperCase();
            }else {
                this.productId = "";
            }
            // Mang loc so luong (tranh so am va so lon hon 1000)
            if(quantity >= 1 && quantity <=1000){
                this.quantity = quantity;
            }else{
                this.quantity = 0;
            }
}
    public String getProductId(){return this.productId;}
    public int getQuantity(){return this.quantity;}
    public void setQuantity(int quantity){
        if (quantity >= 1 && quantity <= 1000){
            this.quantity = quantity;
        }
    }
    public void addQuantity(int amount){
        if (amount >=1 && amount <= 1000){
            int newQuantity = this.quantity + amount;
            if(newQuantity <= 1000){
                this.quantity = newQuantity;
            }
        }
    }
}
