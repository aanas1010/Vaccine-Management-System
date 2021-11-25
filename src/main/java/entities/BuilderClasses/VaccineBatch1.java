package entities.BuilderClasses;

import java.time.LocalDate;
import java.util.Objects;

public class VaccineBatch1{

    private final String brand;
    private final int quantity;
    private final LocalDate expiry;
    private final int id;
    private int reserve;

    private VaccineBatch1(BatchBuilder builder){
        this.brand = builder.brand;
        this.id = builder.id;
        this.expiry = builder.expiry;
        this.quantity = builder.quantity;
        this.reserve = builder.reserve;

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

    public static  class BatchBuilder {
        private final String brand;
        private final int quantity;
        private final LocalDate expiry;
        private final int id;
        private int reserve;

        public BatchBuilder(String brand, int quantity, LocalDate expiry, int id) {
            this.brand = brand;
            this.quantity = quantity;
            this.expiry = expiry;
            this.id = id;
            this.reserve = 0;
        }

        public void setReserved(int reserved){
            this.reserve = reserved;
        }

        public VaccineBatch1 build(){
            VaccineBatch1 batch = new VaccineBatch1(this);
            validateBatch(batch);
            return batch;
        }
        private void validateBatch(VaccineBatch1 batch){
            assert(this.expiry.isAfter(LocalDate.now()));
            assert(Objects.equals(this.brand, "Pfizer") || Objects.equals(this.brand, "Moderna"));
        }
    }
}


