package databaseintegration;

import javax.json.JsonArray;
import java.sql.*;

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

    public JsonArray loadAllTimePeriods () throws SQLException {
        //TODO need to only get the time periods from a specific clinic
        String query = "SELECT * FROM timePeriods";
        ResultSet resultSet = statement.executeQuery(query);
        return ResultSetToJSON.toJSON(resultSet);
    }

    public void updateTimePeriods (int periodID, int availableSlots, int bookedSlots) throws SQLException {
        String query = "UPDATE timePeriods SET availableSlots = ? AND bookedSlots = ? WHERE periodID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, availableSlots);
        state.setInt(2, bookedSlots);
        state.setInt(3, periodID);
        statement.executeQuery(query);
    }
}
