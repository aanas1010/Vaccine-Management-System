package databaseintegration;

import javax.json.JsonArray;
import java.sql.*;

public class DatabaseBatch implements DatabaseBatchInterface{
    private final Connection connection;
    private final Statement statement;

    public DatabaseBatch(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

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

    public JsonArray loadBatches(int clinicID) throws SQLException {
        String query = "SELECT * FROM vaccineBatch WHERE clinicID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, clinicID);
        ResultSet resultSet = statement.executeQuery(query);
        return ResultSetToJSON.toJSON(resultSet);
    }

    public void updateReservedBatch (int clinicID, int batchID, int reserved) throws SQLException {
        String query = "UPDATE vaccineBatch SET reserved = ? WHERE batchID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, reserved);
        state.setInt(2, batchID);
        statement.executeQuery(query);
    }
}
