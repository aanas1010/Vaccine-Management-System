package entities;

import java.time.LocalDate;

/**
 * This is the Entity for a vaccine batch, which includes the
 * brand, quantity, expiry, and other data relevant to a specific batch
 */

public class VaccineBatch {
    private final String brand;
    private final int quantity;
    private final LocalDate expiry;
    private final int id;
    private int reserve;

    // Constructor
    public VaccineBatch(String brand, int quantity, LocalDate expiry, int id){
        this.brand = brand;
        this.quantity = quantity;
        this.expiry = expiry;
        this.id = id;
        this.reserve = 0;
    }

    // Overloaded constructor for testing
    public VaccineBatch(String brand, int quantity, LocalDate expiry, int id, int reserve){
        this.brand = brand;
        this.quantity = quantity;
        this.expiry = expiry;
        this.id = id;
        this.reserve = reserve;
    }

    // Return whether the batch is expired
    public boolean isExpired(){
        LocalDate today = LocalDate.now();
        return (today.isAfter(this.expiry) || today.equals(this.expiry));
    }

    // Set the reserve quantity
    public void setReserve(int num) {
        this.reserve = num;
    }

    // Change the reserve quantity
    public void changeReserve(int num) {
        this.reserve += num;
    }

    // Return the number of available vaccines in a batch
    public int getAvailable(){
        return this.quantity - this.reserve;
    }

    // Getters
    public String getBrand(){
        return this.brand;
    }

    public int getQuantity() {return this.quantity;}

    public LocalDate getExpiry(){return this.expiry;}

    public int getId(){return this.id;}

    public int getReserve() {return this.reserve;}
}
