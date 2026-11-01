/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.group5.storage;

import com.mycompany.group5.model.ExpiringProduct;
import com.mycompany.group5.model.Invoice;
import com.mycompany.group5.model.InvoiceLine;
import com.mycompany.group5.model.Product;
import com.mycompany.group5.model.RegularProduct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class FileStorageManager {

    private String productFilePath;
    private String invoiceFilePath;

    public FileStorageManager(String productFilePath, String invoiceFilePath) {
        if (productFilePath == null || productFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Product file must not be null or empty");
        }
        if (invoiceFilePath == null || invoiceFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Invoice file must not be null or empty");
        }
        this.productFilePath = productFilePath.trim();
        this.invoiceFilePath = invoiceFilePath.trim();
    }

    public boolean saveProducts(List<Product> products) {
        if (products == null) {
            return false;
        }
        File file = new File(this.productFilePath);
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            for (Product p : products) {
                if (p instanceof ExpiringProduct) {
                    ExpiringProduct ep = (ExpiringProduct) p;
                    writer.write("EXPIRING|" + ep.getId() + "|" + ep.getName() + "|"
                            + ep.getPrice() + "|" + ep.getInventory() + "|" + ep.getExpiryDate() + "\n");
                } else {
                    writer.write("REGULAR|" + p.getId() + "|" + p.getName() + "|"
                            + p.getPrice() + "|" + p.getInventory() + "\n");
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error writes file of Product" + e.getMessage());
            return false;
        }
    }

    public Map<String, Product> loadProducts() {
        Map<String, Product> products = new LinkedHashMap<>();
        // đa hình
        File file = new File(this.productFilePath);
        if (!file.exists()) {
            return products;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|");
                String type = parts[0].trim();
                String id = parts[1].trim();
                String name = parts[2].trim();
                long price = Long.parseLong(parts[3].trim());
                int inventory = Integer.parseInt(parts[4].trim());

                if ("REGULAR".equalsIgnoreCase(type)) {
                    products.put(id.toUpperCase(), new RegularProduct(id, name, price, inventory));
                } else if ("EXPIRING".equalsIgnoreCase(type)) {
                    LocalDate expiryDate = LocalDate.parse(parts[5].trim());
                    products.put(id.toUpperCase(), new ExpiringProduct(id, name, price, inventory, expiryDate));
                }
            }
        } catch (Exception e) {
            System.err.println("Error reads file pf product" + e.getMessage());
            throw new RuntimeException("can not reading file of product"
                    + e.getMessage(), e);
        }
        return products;
    }

    public boolean saveInvoices(List<Invoice> invoices) {
        if (invoices == null) {
            return false;
        }
        File file = new File(this.invoiceFilePath);
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            for (Invoice p : invoices) {

                writer.write("INV|" + p.getInvoiceId() + "|" + p.getSaleDate() + "\n");
                for (InvoiceLine line : p.getLines()) {
                    writer.write("LINE|" + line.getProductId() + "|" + line.getProductName() + "|"
                            + line.getUnitPrice() + "|" + line.getQuantity() + "\n");
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error writes file of Product" + e.getMessage());
            return false;
        }
    }

    public List<Invoice> loadInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        // đa hình
        File file = new File(this.invoiceFilePath);
        if (!file.exists()) {
            return invoices;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            String currentInvoiceId = null;
            LocalDate currentSaleDate = null;
            List<InvoiceLine> currentLines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|");
                String recordType = parts[0].trim();
                if ("INV".equalsIgnoreCase(recordType)) {
                    if (currentInvoiceId != null && !currentLines.isEmpty()) {
                        invoices.add(new Invoice(currentInvoiceId, currentSaleDate, currentLines));
                        currentLines = new ArrayList<>();
                    }
                    currentInvoiceId = parts[1].trim();
                    currentSaleDate = LocalDate.parse(parts[2].trim());
                }else if("LINE".equalsIgnoreCase(recordType)){
                    String pId = parts[1].trim();
                    String pName = parts[2].trim();
                    long unitPrice = Long.parseLong(parts[3].trim());
                    int qty = Integer.parseInt(parts[4].trim());
                    currentLines.add(new InvoiceLine(pId, pName, unitPrice, qty));
                          }
                    }
            if(currentInvoiceId != null && !currentLines.isEmpty()){
                invoices.add(new Invoice(currentInvoiceId,currentSaleDate,currentLines));
            }
        } catch (Exception e) {
            System.err.println("Error reads file of invoices" + e.getMessage());
            throw new RuntimeException("can not reading file of invoices"
                    + e.getMessage(), e);
        }
        return invoices;
    }
}
