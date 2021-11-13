package clinic_management;

import Constants.ExceptionConstants;
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
    public String addBatch() throws Exception {
        if (batch.isExpired()){
            throw new Exception(ExceptionConstants.BATCH_EXPIRED);
        }else if(this.clinic.supplyContainsBatchId(batch.getId())) {
            throw new Exception(ExceptionConstants.BATCH_ID_ALREADY_EXISTS);
        }
        else{
            this.clinic.addBatch(batch);
            return batch.toString();
        }
    }

}