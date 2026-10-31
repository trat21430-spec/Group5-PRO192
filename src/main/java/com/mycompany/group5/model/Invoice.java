package com.mycompany.group5.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {

    private String invoiceId;
    private LocalDate saleDate;
    private List<InvoiceLine> lines;
    private long toatalAmount;

    public Invoice(String invoiceId, LocalDate saleDate, List<InvoiceLine> lines, long toatalAmount) {
        if (this.invoiceId == null || this.invoiceId.trim().isEmpty()) {
            throw new IllegalArgumentException("Invoice id must not be null or empty.");

        }
        if (this.saleDate == null) {
            throw new IllegalArgumentException("Sale date must not be null.");
        }
        if (this.lines == null || this.lines.isEmpty()) {
            throw new IllegalArgumentException("Invoice must contain at least one line.");
        }
        if (this.lines.size() > 20) {
            throw new IllegalArgumentException("Invoice must not exceed 20 lines.");
        }
        this.invoiceId = invoiceId.trim().toUpperCase();
        this.saleDate = saleDate;
        this.lines = new ArrayList<>();
        long sum = 0;
        for (InvoiceLine line : this.lines) {
            if (line == null) {
                throw new IllegalArgumentException("Invoice line must not be null.");
            }
            this.lines.add(line);
            sum += line.getLineTotal();

        }
        this.toatalAmount = sum;
    }

    public String getInvoiceId() {
        return this.invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDate getSaleDate() {
        return this.saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public List<InvoiceLine> getLines() {
        return this.lines;
    }

    public void setLines(List<InvoiceLine> lines) {
        this.lines = lines;
    }

    public long getToatalAmount() {
        return this.toatalAmount;
    }

    public void setToatalAmount(long toatalAmount) {
        this.toatalAmount = toatalAmount;
    }

    public int getLineCount() {
        return this.lines.size();
    }

}
