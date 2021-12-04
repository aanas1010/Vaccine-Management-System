package databaseintegration;

import javax.json.JsonArray;
import java.sql.*;

public class DatabaseClinic {
    private final Connection connection;
    private final Statement statement;

    public DatabaseClinic (Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public boolean addClinic (int clinicID, String location, boolean isBookable) throws SQLException {
        if (isExistingClinic(clinicID)) {
            String query = "INSERT INTO clinic VALUES (?, ?, ?)";
            PreparedStatement state =  connection.prepareStatement(query);
            state.setInt(1, clinicID);
            state.setString(2, location);
            state.setBoolean(3, isBookable);

            state.executeUpdate();
            System.out.println("Upload Successful");
            return true;
        }
        System.out.println("Upload Unsuccessful");
        return false;
    }

    public JsonArray loadAllClinics () throws SQLException {
        String query = "SELECT * FROM clinic";
        ResultSet resultSet = statement.executeQuery(query);
        return ResultSetToJSON.toJSON(resultSet);
    }

    private boolean isExistingClinic (int clinicID) throws SQLException {
        String query = "SELECT * FROM clinic WHERE clinicID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, clinicID);
        ResultSet results = state.executeQuery();
        return !results.isBeforeFirst();

    }

}
