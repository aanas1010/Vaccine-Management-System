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

    /**
     * construct a vaccine batch object.
     *
     * @param builder the builder of the batch.
     */
    private VaccineBatch(BatchBuilder builder){
        assert(builder.brand != null);
        this.brand = builder.brand;
        assert(builder.id != -1);
        this.id = builder.id;
        assert(builder.expiry != null);
        this.expiry = builder.expiry;
        assert(builder.quantity != -1);
        this.quantity = builder.quantity;
        this.reserve = builder.reserve;
    }

    /**
     * Return whether the batch is expired.
     *
     * @return true if the batch has expired; false otherwise.
     */
    public boolean isExpired(){
        LocalDate today = LocalDate.now();
        return (today.isAfter(this.expiry) || today.equals(this.expiry));
    }

    /**
     * Set the reserve quantity.
     *
     * @param num number of reserved vaccines.
     */
    public void setReserve(int num) {this.reserve = num;}

    /**
     * Change the reserve quantity.
     *
     * @param num number of added reserved vaccines.
     */
    public void changeReserve(int num) {this.reserve += num;}

    /**
     * Return the number of available vaccines in a batch.
     *
     * @return number of available vaccines.
     */
    public int getAvailable(){return this.quantity - this.reserve;}

    /**
     * Return a string of the information of this batch.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return  "Batch ID: " + this.id +
                "\nBrand: " + this.brand +
                "\nExpiry: " + this.expiry +
                "\nTotal Doses: " + this.quantity +
                "\nDoses Reserved: " + this.reserve;
    }

    // Getters

    /**
     * getter.
     *
     * @return the brand of the batch.
     */
    public String getBrand(){return this.brand;}

    /**
     * getter.
     *
     * @return the expiry date of the batch.
     */
    public LocalDate getExpiry(){return this.expiry;}

    /**
     * getter.
     *
     * @return id if the batch.
     */
    public int getId(){return this.id;}

    /**
     * getter.
     *
     * @return number of reserved vaccines.
     */
    public int getReserve() {return this.reserve;}

    /**
     * The builder class for a batch.
     */
    public static  class BatchBuilder {
        private String brand;
        private int quantity;
        private LocalDate expiry;
        private int id;
        private int reserve;

        /**
         * Constructor for a vaccine batch.
         */
        public BatchBuilder() {
            this.quantity = -1;
            this.id = -1;
        }
        /**
         * assigns the brand of vaccines to the batch.
         *
         * @param brand the brand of the vaccine batch.
         * @return the builder of the vaccine batch.
         */
        public BatchBuilder brand(String brand){
            this.brand = brand;
            return this;
        }
        /**
         * assigns the number of vaccines to the batch.
         *
         * @param quantity the number of vaccines in the vaccine batch.
         * @return the builder of the vaccine batch.
         */
        public BatchBuilder quantity(int quantity){
            this.quantity = quantity;
            return this;
        }
        /**
         * assigns the expiry of the vaccines to the batch.
         *
         * @param expiry the expiry of the vaccine batch.
         * @return the builder of the vaccine batch.
         */
        public BatchBuilder expiry(LocalDate expiry){
            this.expiry = expiry;
            return this;
        }
        /**
         * assigns an id to the batch.
         *
         * @param id id of the vaccine batch.
         * @return the builder of the vaccine batch.
         */
        public BatchBuilder id(int id){
            this.id = id;
            return this;
        }
        /**
         * assigns the number of reserved vaccines to the batch.
         *
         * @param reserved number of reserved vaccines.
         * @return the builder of the vaccine batch.
         */
        public BatchBuilder reserve(int reserved){
            this.reserve = reserved;
            return this;
        }

        /**
         * Building the batch.
         *
         * @return returns the batch.
         */
        public VaccineBatch build(){return new VaccineBatch(this);}
    }
}


