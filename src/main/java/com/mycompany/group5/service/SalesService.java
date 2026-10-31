package com.mycompany.group5.service;

import com.mycompany.group5.model.Cart;
import com.mycompany.group5.model.CartItem;
import com.mycompany.group5.model.Invoice;
import com.mycompany.group5.model.InvoiceLine;
import com.mycompany.group5.model.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SalesService {

    private ProductService productService;
    private List<Invoice> invoices;
    private int invoiceCounter;

    public SalesService(ProductService productService) {
        if (productService == null) {
            throw new IllegalArgumentException("ProductService must not be null.");
        }
        this.productService = productService;
        this.invoices = new ArrayList<>();
        this.invoiceCounter = 0;
    }

    public long[] preview(Cart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart must not be null.");
        }
        if (cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart must not be empty.");
        }

        LocalDate saleDate = cart.getSaleDate();
        List<InvoiceLine> tempLines = new ArrayList<>();

        for (CartItem item : cart.getItems().values()) {
            Product product = this.productService.findById(item.getProductId());
            if (product == null) {
                throw new IllegalArgumentException("Product not found: " + item.getProductId());
            }
   
            product.validateSale(saleDate, item.getQuantity());
            tempLines.add(new InvoiceLine(product.getId(), product.getName(), product.getPrice(), item.getQuantity()));
        }

        Invoice tempInvoice = new Invoice("PREVIEW", saleDate, tempLines);
        return new long[]{tempInvoice.getSubtotal(), tempInvoice.getDiscount(), tempInvoice.getTotal()};
    }

   
    public Invoice checkout(Cart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart must not be null.");
        }
        if (cart.isConfirmed()) {
            throw new IllegalArgumentException("Cart is already confirmed.");
        }
        if (cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart must not be empty.");
        }

        LocalDate saleDate = cart.getSaleDate();


        for (CartItem item : cart.getItems().values()) {
            Product product = this.productService.findById(item.getProductId());
            if (product == null) {
                throw new IllegalArgumentException("Product not found: " + item.getProductId());
            }
            product.validateSale(saleDate, item.getQuantity());
        }

       
        List<InvoiceLine> lines = new ArrayList<>();
        for (CartItem item : cart.getItems().values()) {
            Product product = this.productService.findById(item.getProductId());
            this.productService.deductInventory(product.getId(), item.getQuantity());
            lines.add(new InvoiceLine(product.getId(), product.getName(), product.getPrice(), item.getQuantity()));
        }

 
        this.invoiceCounter++;
        String invoiceId = String.format("INV%03d", this.invoiceCounter);
        Invoice invoice = new Invoice(invoiceId, saleDate, lines);

        cart.setConfirmed(true);
        this.invoices.add(invoice);

        return invoice;
    }

   
    public void cancel(Cart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart must not be null.");
        }
        if (cart.isConfirmed()) {
            throw new IllegalArgumentException("Cannot cancel a confirmed cart.");
        }
        List<String> productIds = new ArrayList<>(cart.getItems().keySet());
        for (String id : productIds) {
            cart.removeItem(id);
        }
        cart.setConfirmed(true);
    }

    public Invoice findInvoice(String invoiceId) {
        if (invoiceId == null || invoiceId.trim().isEmpty()) {
            return null;
        }
        String targetId = invoiceId.trim().toUpperCase();
        for (Invoice inv : this.invoices) {
            if (inv.getInvoiceId().equals(targetId)) {
                return inv;
            }
        }
        return null;
    }


    public List<Invoice> getAllInvoices() {
        return Collections.unmodifiableList(this.invoices);
    }
}