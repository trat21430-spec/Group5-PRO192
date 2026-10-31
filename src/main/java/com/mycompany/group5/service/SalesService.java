/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.group5.service;

import com.mycompany.group5.model.Cart;
import com.mycompany.group5.model.CartItem;
import com.mycompany.group5.model.Invoice;
import com.mycompany.group5.model.InvoiceLine;
import com.mycompany.group5.model.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ACER
 */
public class SalesService {
    private ProductService productService;
    private Map<String, Invoice> invoices;
    private int invoiceCounter;

    public SalesService(ProductService productService) {
        this(productService, null);
    }

  
    public SalesService(ProductService productService, Map<String, Invoice> invoices) {
        if (productService == null) {
            throw new IllegalArgumentException("ProductService must not be null.");
        }

        this.productService = productService;
       
        if (invoices != null) {
            this.invoices = new LinkedHashMap<>(invoices);
            this.invoiceCounter = this.invoices.size();
        } else {
            this.invoices = new LinkedHashMap<>();
            this.invoiceCounter = 0;
        }
    
    }

    public Cart createCart(LocalDate saleDate) {
        return new Cart(saleDate);
    }

    public boolean addToCart(Cart cart, String productId, int quantity) {
        if (cart == null || cart.isConfirmed() || productId == null || quantity < 1 || quantity > 1000) {
            return false;
        }
        Product product = this.productService.findById(productId);
        if (product == null) {
            return false;
        }

        String id = product.getId();
        CartItem existing = cart.getItems().get(id);
        int totalQuantity = (existing != null) ? existing.getQuantity() + quantity : quantity;

        try {
            // Gọi đa hình validateSale
            product.validateSale(cart.getSaleDate(), totalQuantity);
            return cart.addItem(id, quantity);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean updateCartItem(Cart cart, String productId, int newQuantity) {
        if (cart == null || cart.isConfirmed() || productId == null || newQuantity < 1 || newQuantity > 1000) {
            return false;
        }
        Product product = this.productService.findById(productId);
        if (product == null) {
            return false;
        }
        try {
            product.validateSale(cart.getSaleDate(), newQuantity);
            return cart.updateQuantity(product.getId(), newQuantity);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean removeFromCart(Cart cart, String productId) {
        if (cart == null || cart.isConfirmed() || productId == null) {
            return false;
        }
        return cart.removeItem(productId);
    }

    public Invoice checkout(Cart cart) {
        String autoInvoiceId = String.format("INV%03d", this.invoiceCounter + 1);
        return this.checkout(cart, autoInvoiceId);
    }

    public Invoice checkout(Cart cart, String invoiceId) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart must not be null.");
        }
        if (cart.isConfirmed()) {
            throw new IllegalArgumentException("Cart is already confirmed.");
        }
        if (cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart must not be empty.");
        }
        if (invoiceId == null || invoiceId.trim().isEmpty()) {
            throw new IllegalArgumentException("Invoice id must not be null or empty.");
        }
        String cleanInvoiceId = invoiceId.trim().toUpperCase();
        if (this.invoices.containsKey(cleanInvoiceId)) {
            throw new IllegalArgumentException("Invoice id already exists: " + cleanInvoiceId);
        }

        LocalDate saleDate = cart.getSaleDate();

        // Bước 1: Kiểm tra đa hình validateSale cho tất cả sản phẩm trong giỏ
        for (CartItem item : cart.getItems().values()) {
            Product product = this.productService.findById(item.getProductId());
            if (product == null) {
                throw new IllegalArgumentException("Product not found: " + item.getProductId());
            }
            product.validateSale(saleDate, item.getQuantity());
        }

        // Bước 2: Trừ tồn kho và tạo danh sách InvoiceLine
        List<InvoiceLine> lines = new ArrayList<>();
        for (CartItem item : cart.getItems().values()) {
            Product product = this.productService.findById(item.getProductId());
            this.productService.deductInventory(product.getId(), item.getQuantity());
            lines.add(new InvoiceLine(product, item.getQuantity()));
        }

        // Bước 3: Tạo Invoice, khóa giỏ hàng và lưu vào Map
        Invoice invoice = new Invoice(cleanInvoiceId, saleDate, lines);
        cart.setConfirmed(true);
        this.invoices.put(invoice.getInvoiceId(), invoice);
        this.invoiceCounter++;

        return invoice;
    }

    public Invoice findInvoiceById(String invoiceId) {
        if (invoiceId == null) {
            return null;
        }
        return this.invoices.get(invoiceId.trim().toUpperCase());
    }

    public List<Invoice> getAllInvoices() {
        return new ArrayList<>(this.invoices.values());
    }

    public long getTotalRevenue() {
        long total = 0;
        for (Invoice inv : this.invoices.values()) {
            total += inv.getTotalAmount();
        }
        return total;
    }
}