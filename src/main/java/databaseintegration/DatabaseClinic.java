package databaseintegration;

import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Object> loadAllClinics () throws SQLException {
        String query = "SELECT * FROM clinic";
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Object> results = new ArrayList<>();
        while(resultSet.next()) {
            results.add(resultSet.getInt("clinicID"));
            results.add(resultSet.getString("location"));
            results.add(resultSet.getBoolean("isBookable"));
        }
        System.out.println("All stored clinic IDs: " + results);
        return results;
    }

    private boolean isExistingClinic (int clinicID) throws SQLException {
        String query = "SELECT * FROM clinic WHERE clinicID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, clinicID);
        ResultSet results = state.executeQuery();
        return !results.isBeforeFirst();

    }

}
