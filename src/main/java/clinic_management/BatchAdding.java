package clinic_management;

import entities.ServiceLocation;
import entities.VaccineBatch;

/**
 * This is the Use Case for adding batches.
 * Every time the use case is needed, a new clinic_management.BatchAdding instance is created
 * with the parameters of the batch
 */

public class BatchAdding {
    private final ServiceLocation clinic;
    private final VaccineBatch batch;

    // Constructor
    public BatchAdding(ServiceLocation clinic, VaccineBatch batch){
        this.clinic = clinic;
        this.batch = batch;
    }

    // Adding a vaccine batch to the supply of a clinic if it is not expired
    // Return whether the batch is added
    public boolean addBatch(){
        if (batch.isExpired() && !this.clinic.supplyContainsBatchId(batch.getId())){
            return false;
        }
        else{
            this.clinic.addBatch(batch);
            return true;
        }
    }

}