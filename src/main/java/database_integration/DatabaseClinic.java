package database_integration;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseClinic {
    private final Connection connection;
    private final Statement statement;

    public DatabaseClinic (Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public boolean addClinic (int clinicID) throws SQLException {
        if (isExistingClinic(clinicID)) {
            String query = "INSERT INTO clinic VALUES (?)";
            PreparedStatement state =  connection.prepareStatement(query);
            state.setInt(1, clinicID);
            state.executeUpdate();
            System.out.println("Upload Successful");
            return true;
        }
        System.out.println("Upload Unsuccessful");
        return false;
    }

    public ArrayList<Integer> loadAllClinics () throws SQLException {
        String query = "SELECT clinicID FROM clinic";
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Integer> results = new ArrayList<>();
        while(resultSet.next()) {
            results.add(resultSet.getInt("clinicID"));
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
