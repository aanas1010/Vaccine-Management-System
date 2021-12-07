package databaseintegration;

import javax.json.JsonArray;
import java.sql.Date;
import java.sql.SQLException;

/**
 * This is the interface relating to the database batch table
 */

public interface DatabaseBatchInterface {
    /** Add a new batch to the batch table
     *
     * @param batchID the ID of the batch
     * @param clinicID the ID of the clinic
     * @param brand the brand of this batch
     * @param expiryDate the expiry date of this batch
     * @param reserved the number of reserved doses
     * @param quantity the number of available doses
     * @throws SQLException if the data cannot be added
     */
    void addBatch (int batchID, int clinicID, String brand, Date expiryDate, int reserved, int quantity) throws SQLException;

    /** Load all the batches from a given clinic
     *
     * @param clinicID the ID of the clinic whose batches we want to load
     * @return a JsonArray of the batches for this clinic
     * @throws SQLException if the batches cannot be loaded
     */
    JsonArray loadBatches(int clinicID) throws SQLException;

    /** Update a batch's reserved quantity
     *
     * @param batchID the ID of this batch
     * @param reserved the new Reserved quantity for this batch
     * @throws SQLException if the batch cannot be updated
     */
    void updateReservedBatch(int batchID, int reserved) throws SQLException;
}
