import java.time.LocalDate;
public abstract class Product {

    private String id;
    private String name;
    private long price;
    private int inventory;

    public Product(String id, String name, long price, int inventory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }

    public abstract void validateSale(LocalDate saleDate, int quantity);

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    
    public boolean addInventory(int amount) {
        if (amount <= 0) {
            return false;
        }
        inventory += amount;
        return true;
    }

    public boolean deductInventory(int amount) {
        if (amount <= 0 || amount > inventory) {
            return false;
        }
        inventory -= amount;
        return true;
    }
}
