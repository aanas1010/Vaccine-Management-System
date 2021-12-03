package databaseintegration;

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

    public ArrayList<Object> loadAllBatches () throws SQLException {
        String query = "SELECT * FROM vaccineBatch";
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Object> results = new ArrayList<>();
        while(resultSet.next()) {
            results.add(resultSet.getInt("batchID"));
            results.add(resultSet.getInt("clinicID"));
            results.add(resultSet.getString("brand"));
            results.add(resultSet.getDate("expiryDate"));
            results.add(resultSet.getInt("reserved"));
            results.add(resultSet.getInt("quantity"));
        }
        return results;
    }

    public void updateReservedBatch (int batchID, int reserved) throws SQLException {
        String query = "UPDATE vaccineBatch SET reserved = ? WHERE batchID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, reserved);
        state.setInt(2, batchID);
        statement.executeQuery(query);
    }
}
