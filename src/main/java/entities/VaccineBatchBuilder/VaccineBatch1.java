package entities.VaccineBatchBuilder;

import java.time.LocalDate;

public class VaccineBatch1 implements Vaccine{

    private String brand;
    private int quantity;
    private LocalDate expiry;
    private int id;
    private int reserve;

    @Override
    public void setBrand(String brand){
        this.brand = brand;
    }

    @Override
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    @Override
    public void setExpiry(LocalDate expiry){
        this.expiry = expiry;
    }

    @Override
    public void setId(int id){
        this.id = id;
    }

    @Override
    public void setReserved(int reserved){
        this.reserve = reserved;
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

    // Return a string of the information of this batch
    @Override
    public String toString() {
        return  "Batch ID: " + this.id +
                "\nBrand: " + this.brand +
                "\nExpiry: " + this.expiry +
                "\nTotal Doses: " + this.quantity +
                "\nDoses Reserved: " + this.reserve;
    }

    // Getters
    public String getBrand(){
        return this.brand;
    }

    public LocalDate getExpiry(){return this.expiry;}

    public int getId(){return this.id;}

    public int getReserve() {return this.reserve;}
}

