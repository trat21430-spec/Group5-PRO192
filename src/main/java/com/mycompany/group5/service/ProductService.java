package com.mycompany.group5.service;

import com.mycompany.group5.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class ProductService {
    private Map<String, Product> products;
    public ProductService(Map<String, Product> products){
        if( products != null){
            this.products = new LinkedHashMap<>(products);
        }else{
            this.products = new LinkedHashMap<>();
        }
    }

    public boolean addProduct(Product product){
        if(product == null){
            return false;
        }
        if(this.products.containsKey(product.getId())){
            return false;
        }
        this.products.put(product.getId(), product);
        return true;
    }
    public Product findById(String id){
        String key = id;
        if(key == null){
            return null;
        }
        return this.products.get(id.trim().toUpperCase());
    }
    public List<Product> findByName(String name){
        List<Product> result = new ArrayList<>();
        if(name == null || name.trim().isEmpty()){
            return result;
        }
        //List ko tìm thấy thì return 1 arraylist rỗng
        String keyword = name.trim().toLowerCase();
        for(Product p : this.products.values()){
            if(p.getName().toLowerCase().contains(keyword)){
                result.add(p);
            }
        }
        return result;
    }
    public List<Product> getAll(){
        return new ArrayList<>(this.products.values());
    }
    public boolean addInventory(String id, int addedQuantity){
        Product p = this.findById(id);
        if(p == null){
            return false;
        }else{
            return p.addInventory(addedQuantity);
        }
    }
    
    public boolean deductInventory(String id, int deductQuantity){
        Product p = this.findById(id);
        if(p == null){
            return false;
        }
        return p.deductInventory(deductQuantity);
    }
    public long getTotalInventory(){
        long total = 0;
        for(Product p : this.products.values()){
            total += p.getInventory();
        }
        return total;
    }
    public boolean updateProduct(String id, String newName, long newPrice){
        Product p = this.findById(id);
        if(p == null){
            return false;
        }
        String oldName = p.getName();
        long oldPrice = p.getPrice();
        try{
            p.setName(newName);
            p.setPrice(newPrice);
            return true;
        } catch (IllegalArgumentException e){
            p.setName(oldName);
            p.setPrice(oldPrice);
            return false;
        }
    }

}
