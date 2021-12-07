package databaseintegration;

import javax.json.JsonArray;
import java.sql.*;

/**
 * This is the driver relating to the database batch table
 */

public class DatabaseBatch implements DatabaseBatchInterface{
    private final Connection connection;

    public DatabaseBatch(Connection connection) {
        this.connection = connection;
    }

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
    public void addBatch (int batchID, int clinicID, String brand, Date expiryDate, int reserved, int quantity)
            throws SQLException {
        String query = "INSERT INTO vaccineBatch VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement state =  connection.prepareStatement(query);
        state.setInt(1, batchID);
        state.setInt(2, clinicID);
        state.setString(3, brand);
        state.setDate(4, expiryDate);
        state.setInt(5, reserved);
        state.setInt(6, quantity);

        state.executeUpdate();
        System.out.println("Added a batch");
    }

    /** Load all the batches from a given clinic
     *
     * @param clinicID the ID of the clinic whose batches we want to load
     * @return a JsonArray of the batches for this clinic
     * @throws SQLException if the batches cannot be loaded
     */
    public JsonArray loadBatches(int clinicID) throws SQLException {
        String query = "SELECT * FROM vaccineBatch WHERE clinicID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, clinicID);
        ResultSet resultSet = state.executeQuery();
        return ResultSetToJSON.toJSON(resultSet);
    }

    /** Update a batch's reserved quantity
     *
     * @param batchID the ID of this batch
     * @param reserved the new Reserved quantity for this batch
     * @throws SQLException if the batch cannot be updated
     */
    public void updateReservedBatch(int batchID, int reserved) throws SQLException {
        connection.setAutoCommit(false);
        String query = "UPDATE vaccineBatch SET reserved = ? WHERE batchID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, reserved);
        state.setInt(2, batchID);
        state.executeUpdate();
        connection.commit();
    }
}
