package databaseintegration;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseTimePeriods {
    private final Connection connection;
    private final Statement statement;

    public DatabaseTimePeriods (Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public void addTimePeriod (int periodID, int clinicID, int availableSlots, int bookedSlots, Timestamp datetime)
            throws SQLException {
        String query = "INSERT INTO timePeriods VALUES (?, ?, ?, ?, ?)";
        PreparedStatement state =  connection.prepareStatement(query);
        state.setInt(1, periodID);
        state.setInt(2, clinicID);
        state.setInt(3, availableSlots);
        state.setInt(4, bookedSlots);
        state.setTimestamp(5, datetime);

        state.executeUpdate();
    }

    public ArrayList<Object> loadAllTimePeriods () throws SQLException {
        String query = "SELECT * FROM timePeriods";
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Object> results = new ArrayList<>();
        while(resultSet.next()) {
            results.add(resultSet.getInt("periodID"));
            results.add(resultSet.getInt("clinicID"));
            results.add(resultSet.getInt("availableSlots"));
            results.add(resultSet.getInt("bookedSlots"));
            results.add(resultSet.getTimestamp("datetime"));
        }
        System.out.println("All stored clinic IDs: " + results);
        return results;
    }
}
