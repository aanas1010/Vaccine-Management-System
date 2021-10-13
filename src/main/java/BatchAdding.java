import java.time.LocalDate;

public class BatchAdding {
    private Clinic clinic;
    private VaccineBatch batch;

    // Constructor
    public BatchAdding(Clinic clinic, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId){
        this.clinic = clinic;
        this.batch = new VaccineBatch(batchBrand, batchQuantity, batchExpiry, batchId);
    }

    public BatchAdding(Clinic clinic, VaccineBatch batch){
        this.clinic = clinic;
        this.batch = batch;
    }

    // Adding a vaccine batch to the supply of a clinic if it is not expired
    public boolean addBatch(){
        if (batch.isExpired()){
            return false;
        }
        else{
            this.clinic.getSupply().add(batch);
            return true;
        }
    }

}