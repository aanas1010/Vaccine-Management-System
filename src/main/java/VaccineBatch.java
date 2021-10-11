import java.time.LocalDate;

public class VaccineBatch {
    private String brand;
    private int quantity;
    private LocalDate expiry;
    private int id;
    private int reserve;

    public VaccineBatch(String brand, int quantity, LocalDate expiry){
        this.brand = brand;
        this.quantity = quantity;
        this.expiry = expiry;
    }
    public boolean isExpired(){
        LocalDate today = LocalDate.now();
        return (today.isAfter(this.expiry) || today.equals(this.expiry));
    }
    public String getBrand(){
        return this.brand;
    }
    public int getId(){
        return this.id;
    }
    public int getAvailable(){
        return this.quantity - this.reserve;
    }
}
