package com.mycompany.group5.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Cart: giỏ hàng, quản lý Map<String, CartItem> (key = productId).
 * Chỉ dùng kiểu chuẩn của Java + CartItem, không phụ thuộc Product/ProductService.
 */
public class Cart {

    private LocalDate saleDate;
    private Map<String, CartItem> items;
    private boolean confirmed;

    public Cart(LocalDate saleDate, Map<String, CartItem> items, boolean confirmed) {
        if (saleDate != null) {
            this.saleDate = saleDate;
        } else {
            this.saleDate = LocalDate.now();
        }

        if (items != null) {
            this.items = new LinkedHashMap<>(items);
        } else {
            this.items = new LinkedHashMap<>();
        }

        this.confirmed = confirmed;
    }

    /** Thêm sản phẩm; nếu đã có thì cộng dồn số lượng. */
    public boolean addItem(String productId, int quantity) {
        if (confirmed || productId == null || productId.trim().isEmpty() || quantity <= 0) {
            return false;
        }
        String id = productId.trim();
        CartItem existing = items.get(id);
        if (existing != null) {
            existing.addQuantity(quantity);
        } else {
            items.put(id, new CartItem(id, quantity));
        }
        return true;
    }

    /** Cập nhật số lượng của sản phẩm đã có trong giỏ. */
    public boolean updateQuantity(String productId, int newQuantity) {
        if (confirmed || productId == null || newQuantity <= 0) {
            return false;
        }
        CartItem item = items.get(productId.trim());
        if (item == null) {
            return false;
        }
        item.setQuantity(newQuantity);
        return true;
    }

    /** Xóa sản phẩm khỏi giỏ. */
    public boolean removeItem(String productId) {
        if (confirmed || productId == null) {
            return false;
        }
        return items.remove(productId.trim()) != null;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    /** Trả về view chỉ đọc để bên ngoài không sửa trực tiếp giỏ hàng. */
    public Map<String, CartItem> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    /** Số loại sản phẩm (số dòng) trong giỏ. */
    public int getItemCount() {
        return items.size();
    }
}