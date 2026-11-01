/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.group5;

import com.mycompany.group5.model.Cart;
import com.mycompany.group5.model.CartItem;
import com.mycompany.group5.model.ExpiringProduct;
import com.mycompany.group5.model.Invoice;
import com.mycompany.group5.model.InvoiceLine;
import com.mycompany.group5.model.Product;
import com.mycompany.group5.model.RegularProduct;
import com.mycompany.group5.service.ProductService;
import com.mycompany.group5.service.SalesService;
import com.mycompany.group5.storage.FileStorageManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Group5 {

    private final Scanner scanner;
    private FileStorageManager storage;
    private ProductService productService;
    private SalesService saleService;

    public Group5() {
        this.scanner = new Scanner(System.in);
        this.storage = new FileStorageManager("data/products.txt", "data/invoices.txt");
        // Load sản phẩm từ file
        Map<String, Product> products = this.storage.loadProducts();
        
        this.productService = new ProductService(products);
        this.saleService = new SalesService(this.productService);
        // Load hóa đơn cũ (nếu có)
        List<Invoice> oldInvoices = this.storage.loadInvoices();

    }

    public static void main(String[] args) {
        new Group5().run();
    }

    
     

    public void run() {
        int choice;
        do {
            printMainMenu();
            choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    menuProductManagement();
                    break;
                case 2:
                    menuInventoryManagement();
                    break;
                case 3:
                    menuSales();
                    break;
                case 4:
                    menuInvoiceSearch();
                    break;
                case 5:
                    saveAllData();
                    break;
                case 0:
                    saveAllData();
                    System.out.println("Goodbye! Thank you for using Mini Mart System.");
                    break;
                default:
                    System.out.println("Invalid option! Please select a valid number from the menu.");
            }
        } while (choice != 0);
    }

    private void printMainMenu() {
        System.out.println("\n==================================================");
        System.out.println("        System Management of MINI MART - group 5       ");
        System.out.println("==================================================");
        System.out.println("1. Product catalog management (View, Add, Edit))");
        System.out.println("2. Inventory Management (View Inventory, Add More Items)");
        System.out.println("3. Create a sales schedule & Sell (Preview, Confirm order))");
        System.out.println("4. Look up issued invoices");
        System.out.println("5. Save data to a file");
        System.out.println("0. exit");
        System.out.println("--------------------------------------------------");
    }
    //1. PRODUCT CATALOG MANAGEMENT
    // ==============================================================

    private void menuProductManagement() {
        
        System.out.println("\n--- [1] PRODUCT CATALOG MANAGEMENT ---");
        System.out.println("1. View all products");
        System.out.println("2. Search product by ID");
        System.out.println("3. Search product by name");
        System.out.println("4. Add new product");
        System.out.println("5. Edit product name and unit price");
        System.out.println("0. Back to Main Menu");
        int choice = readInt("Select an option: ");
        switch (choice) {
            case 1:
                displayProductList(this.productService.getAll());
                break;
            case 2:
                String id = readString("Enter product ID to search: ");
                Product p = this.productService.findById(id);
                if (p != null) {
                    displayProduct(p);
                } else {
                    System.out.println("-> Product not found with ID: " + id);
                }
                break;
            case 3:
                String name = readString("Enter product name keyword: ");
                List<Product> list = this.productService.findByName(name);
                displayProductList(list);
                break;
            case 4:
                addNewProduct();
                break;
            case 5:
                updateProduct();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid selection!");
            }
        
    }

    private void addNewProduct() {
        System.out.println("\n--- ADD NEW PRODUCT ---");
        System.out.println("1. Regular Product");
        System.out.println("2. Expiring Product");
        int type = readInt("Select product category: ");
        String id = readString("Enter product ID (3-10 alphanumeric characters): ");
        if (this.productService.findById(id) != null) {
            System.out.println("Error: Product ID already exists in catalog!");
            return;
        }
        String name = readString("Enter product name: ");
        long price = readLong("Enter unit price (1 - 10,000,000 VND): ");
        int inventory = readInt("Enter initial inventory (0 - 100,000): ");
        try {
            Product newP;
            if (type == 2) {
                LocalDate expiry = readDate("Enter expiration date (yyyy-MM-dd): ");
                newP = new ExpiringProduct(id, name, price, inventory, expiry);
            } else {
                newP = new RegularProduct(id, name, price, inventory);
            }
            if (this.productService.addProduct(newP)) {
                System.out.println("-> Product added successfully!");
            } else {
                System.out.println("-> Failed to add product!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    private void updateProduct() {
        String id = readString("Enter product ID to modify: ");
        Product p = this.productService.findById(id);
        if (p == null) {
            System.out.println("Error: Product not found!");
            return;
        }
        System.out.println("Modifying: " + p.getName() + " (Current price: " + p.getPrice() + " VND)");
        String newName = readString("Enter new product name: ");
        long newPrice = readLong("Enter new unit price (1 - 10,000,000 VND): ");
        try {
            if (this.productService.updateProduct(id, newName, newPrice)) {
                System.out.println("-> Product updated successfully!");
            } else {
                System.out.println("-> Update failed!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }
    // 2. INVENTORY MANAGEMENT
    // ==============================================================

    private void menuInventoryManagement() {
        System.out.println("\n--- [2] INVENTORY MANAGEMENT ---");
        System.out.println("1. View stock levels for all products");
        System.out.println("2. Restock product (Add inventory)");
        System.out.println("0. Back to Main Menu");
        int choice = readInt("Select an option: ");
        switch (choice) {
            case 1:
                displayProductList(this.productService.getAll());
                break;
            case 2:
                String id = readString("Enter product ID to restock: ");
                Product p = this.productService.findById(id);
                if (p == null) {
                    System.out.println("Error: Product not found!");
                    return;
                }
                System.out.println("Product: " + p.getName() + " | Current stock: " + p.getInventory());
                int amount = readInt("Enter restock quantity (positive integer): ");
                if (this.productService.addInventory(id, amount)) {
                    System.out.println("-> Restocked successfully! Updated stock: " + p.getInventory());
                } else {
                    System.out.println("-> Rejected: Restock quantity must be positive and total inventory cannot exceed 100,000!");
                }
                break;
            case 0:
                break;
        }
    }
        // 3. SHOPPING CART & SALES WORKFLOW
        // ==============================================================
    private void menuSales() {
        System.out.println("\n==================================================");
        System.out.println("                 SALES & CHECKOUT                 ");
        System.out.println("==================================================");
        LocalDate saleDate = readDate("Enter sale date for this order (yyyy-MM-dd): ");
        Cart cart = new Cart(saleDate);
        int action;
        do {
            System.out.println("\n--- ACTIVE SHOPPING CART (" + cart.getItemCount() + " unique item(s)) ---");
            displayCart(cart);
            System.out.println("\n1. Add product to cart");
            System.out.println("2. Update item quantity in cart");
            System.out.println("3. Remove product from cart");
            System.out.println("4. Preview Invoice (Amounts & Discounts)");
            System.out.println("5. CONFIRM CHECKOUT");
            System.out.println("0. CANCEL CART");
            action = readInt("Select action: ");
            switch (action) {
                case 1:
                    String pId = readString("Enter product ID to purchase: ");
                    Product prod = this.productService.findById(pId);
                    if (prod == null) {
                        System.out.println("Error: Product ID does not exist!");
                        break;
                    }
                    int qty = readInt("Enter purchase quantity: ");
                    if (cart.addItem(prod.getId(), qty)) {
                        System.out.println("-> Added to cart successfully!");
                    } else {
                        System.out.println("-> Rejected: Line quantity must be 1-1,000 and cart cannot exceed 20 unique products!");
                    }
                    break;
                case 2:
                    String editId = readString("Enter product ID to update quantity: ");
                    int newQty = readInt("Enter new quantity (1 - 1,000): ");
                    if (cart.updateQuantity(editId, newQty)) {
                        System.out.println("-> Quantity updated successfully!");
                    } else {
                        System.out.println("-> Failed to update: Product not found in cart or invalid quantity!");
                    }
                    break;
                case 3:
                    String removeId = readString("Enter product ID to remove from cart: ");
                    if (cart.removeItem(removeId)) {
                        System.out.println("-> Item removed from cart!");
                    } else {
                        System.out.println("-> Product is not present in cart!");
                    }
                    break;
                case 4:
                    // Preview calculation
                    try {
                    long[] amounts = this.saleService.preview(cart);
                    System.out.println("\n--- INVOICE PREVIEW ---");
                    System.out.printf("Subtotal before discount (S): %,d VND\n", amounts[0]);
                    System.out.printf("Discount amount          (D): %,d VND (5%% discount applied if S >= 200,000)\n", amounts[1]);
                    System.out.printf("TOTAL PAYABLE            (T): %,d VND\n", amounts[2]);
                } catch (Exception e) {
                    System.out.println("Preview Error: " + e.getMessage());
                }
                break;
                case 5:
                    // Checkout validation & execution
                    try {
                    Invoice inv = this.saleService.checkout(cart);
                    System.out.println("\n CHECKOUT SUCCESSFUL! INVOICE ISSUED:");
                    displayInvoiceDetail(inv);
                    saveAllData(); // Auto-save on successful sale
                    return; // Terminate checkout session
                } catch (Exception e) {
                    System.out.println(" CHECKOUT REJECTED: " + e.getMessage());
                    System.out.println("-> Transaction aborted. Inventory remains untouched. Please adjust your cart!");
                }
                break;
                case 0:
                    // Cancel cart
                    this.saleService.cancel(cart);
                    System.out.println("-> Shopping cart cancelled! No stock deducted and no invoice created.");
                    return;
                default:
                    System.out.println("Invalid selection!");
            }
        } while (action != 0);
    }

    //4. INVOICE INQUIRY
    // ==============================================================
    private void menuInvoiceSearch() {
        System.out.println("\n--- [4] INVOICE INQUIRY ---");
        List<Invoice> invoices = this.saleService.getAllInvoices();
        if (invoices.isEmpty()) {
            System.out.println("No invoices have been recorded yet.");
            return;
        }
        System.out.println("Recorded Invoices List:");
        for (Invoice inv : invoices) {
            System.out.printf("- ID: %s | Date: %s | Total: %,d VND\n",
                    inv.getInvoiceId(), inv.getSaleDate(), inv.getTotal());
        }
        String searchId = readString("\nEnter Invoice ID to view full details (or '0' to back): ");
        if ("0".equals(searchId)) {
            return;
        }
        Invoice found = this.saleService.findInvoice(searchId);
        if (found != null) {
            displayInvoiceDetail(found);
        } else {
            System.out.println("No invoice found with ID: " + searchId);
        }
    }
    //5. STORAGE PERSISTENCE
    // ==============================================================

    private void saveAllData() {
        boolean pOk = this.storage.saveProducts(this.productService.getAll());
        boolean iOk = this.storage.saveInvoices(this.saleService.getAllInvoices());
        if (pOk && iOk) {
            System.out.println("-> All data saved successfully to 'data/' directory!");
        } else {
            System.out.println("-> Error saving data to files!");
        }
    }
    // FORMATTED TABLE PRINTERS
    // ==============================================================

    private void displayProductList(List<Product> list) {
        if (list.isEmpty()) {
            System.out.println("Product catalog is empty.");
            return;
        }
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.printf("%-6s | %-32s | %-14s | %-10s | %-14s\n", "ID", "PRODUCT NAME", "UNIT PRICE", "STOCK", "EXPIRY DATE");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (Product p : list) {
            String expiry = "—";
            if (p instanceof ExpiringProduct) {
                expiry = ((ExpiringProduct) p).getExpiryDate().toString();
            }
            System.out.printf("%-6s | %-32s | %,10d VND | %,10d | %-14s\n",
                    p.getId(), p.getName(), p.getPrice(), p.getInventory(), expiry);
        }
        System.out.println("-------------------------------------------------------------------------------------------------");
    }

    private void displayProduct(Product p) {
        String expiry = (p instanceof ExpiringProduct) ? ((ExpiringProduct) p).getExpiryDate().toString() : "None";
        System.out.println("-> ID: " + p.getId() + " | Name: " + p.getName() + " | Price: " + p.getPrice()
                + " VND | Stock: " + p.getInventory() + " | Expiration: " + expiry);
    }

    private void displayCart(Cart cart) {
        if (cart.getItems().isEmpty()) {
            System.out.println("(Cart is currently empty)");
            return;
        }
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("%-6s | %-30s | %-14s | %-10s | %-14s\n", "ID", "PRODUCT NAME", "UNIT PRICE", "QUANTITY", "LINE TOTAL");
        System.out.println("----------------------------------------------------------------------------------");
        for (CartItem item : cart.getItems().values()) {
            Product p = this.productService.findById(item.getProductId());
            long price = (p != null) ? p.getPrice() : 0L;
            String name = (p != null) ? p.getName() : "Unknown";
            long lineTotal = price * (long) item.getQuantity();
            System.out.printf("%-6s | %-30s | %,10d VND | %,10d | %,10d VND\n",
                    item.getProductId(), name, price, item.getQuantity(), lineTotal);
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    private void displayInvoiceDetail(Invoice inv) {
        System.out.println("\n========================================================");
        System.out.println("                    INVOICE RECEIPT                     ");
        System.out.println("Invoice ID: " + inv.getInvoiceId() + " | Sale Date: " + inv.getSaleDate());
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-6s | %-25s | %-10s | %-4s | %-12s\n", "ID", "PRODUCT NAME", "PRICE", "QTY", "LINE TOTAL");
        System.out.println("--------------------------------------------------------");
        for (InvoiceLine line : inv.getLines()) {
            System.out.printf("%-6s | %-25s | %,8d d | %4d | %,10d d\n",
                    line.getProductId(), line.getProductName(), line.getUnitPrice(), line.getQuantity(), line.getLineTotal());
        }
        System.out.println("--------------------------------------------------------");
        System.out.printf("Subtotal before discount (S): %,d VND\n", inv.getSubtotal());
        System.out.printf("Discount amount          (D): %,d VND\n", inv.getDiscount());
        System.out.printf("TOTAL PAYABLE            (T): %,d VND\n", inv.getTotal());
        System.out.println("========================================================");
    }

    //SAFE INPUT UTILITIES (PREVENTS CLI CRASHES & BUFFER ISSUES)
    // ==============================================================
    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(this.scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer!");
            }
        }
    }

    public long readLong(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Long.parseLong(this.scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number!");
            }
        }
    }

    public String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = this.scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Error: Input cannot be empty!");
        }
    }

    public LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(this.scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Error: Invalid date format! Please enter as yyyy-MM-dd (e.g., 2026-10-16)");
            }
        }
    }
}
