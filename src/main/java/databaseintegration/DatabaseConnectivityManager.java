package databaseintegration;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnectivityManager {
    private Connection myConn;
    private Statement statement;

    public DatabaseConnectivityManager() throws SQLException {

        String url = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5447070";
        myConn = DriverManager.getConnection(url, "sql5447070", "AxQ1YdG6MP");
        statement = myConn.createStatement();
    }

    public ArrayList<Integer> clinicTable (String command, int clinicID) throws SQLException {
        DatabaseClinic clinic = new DatabaseClinic(myConn, statement);
        ArrayList<Integer> clinicIDs = new ArrayList<>();
        if (command.equals("ADD")) {
            clinic.addClinic(clinicID);
        } else if (command.equals("LOAD")) {
            clinicIDs = clinic.loadAllClinics();
        }
        return clinicIDs;
    }
}
