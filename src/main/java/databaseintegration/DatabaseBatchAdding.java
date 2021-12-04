package databaseintegration;

import javax.json.JsonArray;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseBatchAdding {
    private final Connection connection;
    private final Statement statement;

    public DatabaseBatchAdding (Connection connection, Statement statement) {
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
    }

    public JsonArray loadAllBatches () throws SQLException {
        String query = "SELECT * FROM vaccineBatch";
        ResultSet resultSet = statement.executeQuery(query);
        return ResultSetToJSON.toJSON(resultSet);
    }

    public void updateReservedBatch (int batchID, int reserved) throws SQLException {
        String query = "UPDATE vaccineBatch SET reserved = ? WHERE batchID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, reserved);
        state.setInt(2, batchID);
        statement.executeQuery(query);
    }
}
