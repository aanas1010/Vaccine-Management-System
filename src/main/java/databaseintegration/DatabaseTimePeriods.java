package databaseintegration;

import javax.json.JsonArray;
import java.sql.*;

public class DatabaseTimePeriods implements DatabaseTimePeriodsInterface {
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

    public JsonArray loadTimePeriods(int clinicID) throws SQLException {
        String query = "SELECT * FROM timePeriods WHERE clinicID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, clinicID);
        ResultSet resultSet = state.executeQuery();
        return ResultSetToJSON.toJSON(resultSet);
    }

    public void updateTimePeriods(int clinicID, int periodID, int availableSlots, int bookedSlots) throws SQLException {
        connection.setAutoCommit(false);
        String query = "UPDATE timePeriods SET availableSlots = ?, bookedSlots = ? WHERE periodID = ? AND clinicID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, availableSlots);
        state.setInt(2, bookedSlots);
        state.setInt(3, periodID);
        state.setInt(4, clinicID);
        state.executeUpdate();
        connection.commit();
    }
}
