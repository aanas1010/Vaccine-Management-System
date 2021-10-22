package clinic_management;

import entities.ServiceLocation;
import entities.VaccineBatch;

/**
 * This is the Use Case for adding batches.
 * Every time the use case is needed, a new clinic_management.BatchAdding instance is created
 * with the parameters of the batch
 */

public class BatchAdding {
    private ServiceLocation clinic;
    private VaccineBatch batch;

//    // Constructor
//    public clinic_management.BatchAdding(entities.ServiceLocation clinic, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId){
//        this.clinic = clinic;
//        this.batch = new entities.VaccineBatch(batchBrand, batchQuantity, batchExpiry, batchId);
//    }

    //Constructor
    public BatchAdding(ServiceLocation clinic, VaccineBatch batch){
        this.clinic = clinic;
        this.batch = batch;
    }

    // Adding a vaccine batch to the supply of a clinic if it is not expired
    // Return whether the batch is added
    public boolean addBatch(){
        if (batch.isExpired()){
            return false;
        }
        else{
            this.clinic.getSupply().add(batch);
            System.out.println(this.clinic.getSupplyObj().toString());
            return true;
        }
    }

}