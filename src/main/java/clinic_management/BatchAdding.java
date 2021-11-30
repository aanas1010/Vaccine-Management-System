package clinic_management;

import constants.ManagementSystemException;
import entities.ServiceLocation;
import entities.VaccineBatch;

/**
 * This is the Use Case for adding batches.
 * Every time the use case is needed, a new clinic_management.BatchAdding instance is created with the
 * parameters of the batch
 */

public class BatchAdding {
    private final ServiceLocation clinic;
    private final VaccineBatch batch;

    /**
     * This is the Use Case for adding batches.
     *
     * @param clinic The clinic for which the batch is added
     * @param batch The batch that is being added for the clinic
     */
    public BatchAdding(ServiceLocation clinic, VaccineBatch batch){
        this.clinic = clinic;
        this.batch = batch;
    }

    /**
     * Adds the batch to the clinic
     *
     * @return the details of the added batch, as a string
     * @throws ManagementSystemException if the batch is expired or the clinic already has a batch with the same ID
     */
    public String addBatch() throws ManagementSystemException {
        if (batch.isExpired()){
            throw new ManagementSystemException(ManagementSystemException.BATCH_EXPIRED);
        }else if(this.clinic.supplyContainsBatchId(batch.getId())) {
            throw new ManagementSystemException(ManagementSystemException.BATCH_ID_ALREADY_EXISTS);
        }
        else{
            this.clinic.addBatch(batch);
            return batch.toString();
        }
    }

}