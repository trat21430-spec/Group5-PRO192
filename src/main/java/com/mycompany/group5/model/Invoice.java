package com.mycompany.group5.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Invoice {

    private String invoiceId;
    private LocalDate saleDate;
    private List<InvoiceLine> lines;
    private long subtotal;
    private long discount;
    private long total;

    public Invoice(String invoiceId, LocalDate saleDate, List<InvoiceLine> lines) {
        if (invoiceId == null || invoiceId.trim().isEmpty()) {
            throw new IllegalArgumentException("Invoice id must not be null or empty.");
        }
        if (saleDate == null) {
            throw new IllegalArgumentException("Sale date must not be null.");
        }
        if (lines == null || lines.isEmpty()) {
            throw new IllegalArgumentException("Invoice must contain at least one line.");
        }
        if (lines.size() > 20) {
            throw new IllegalArgumentException("Invoice must not exceed 20 lines.");
        }

        this.invoiceId = invoiceId.trim().toUpperCase();
        this.saleDate = saleDate;
        this.lines = new ArrayList<>();
        for (InvoiceLine line : lines) {
            if (line == null) {
                throw new IllegalArgumentException("Invoice line must not be null.");
            }
            this.lines.add(line);
        }

        this.calculateAmounts();
    }

    private void calculateAmounts() {
        long sum = 0;
        for (InvoiceLine line : this.lines) {
            sum += line.getLineTotal();
        }
        this.subtotal = sum;

        if (this.subtotal >= 1000000) {
            this.discount = (this.subtotal * 10) / 100;
        } else if (this.subtotal >= 500000) {
            this.discount = (this.subtotal * 5) / 100;
        } else {
            this.discount = 0;
        }

        this.total = this.subtotal - this.discount;
    }

    public String getInvoiceId() {
        return this.invoiceId;
    }

    public LocalDate getSaleDate() {
        return this.saleDate;
    }

    public List<InvoiceLine> getLines() {
        return Collections.unmodifiableList(this.lines);
    }

    public long getSubtotal() {
        return this.subtotal;
    }

    public long getDiscount() {
        return this.discount;
    }

    public long getTotal() {
        return this.total;
    }
}