package databaseintegration;

import javax.json.JsonArray;
import java.sql.*;

/**
 * This is the driver relating to the database Clinic table
 */

public class DatabaseClinic implements DatabaseClinicInterface{
    private final Statement statement;

    public DatabaseClinic(Statement statement) {
        this.statement = statement;
    }

    /** Load all clinics in the system
     *
     * @return a JsonArray of all clinics
     * @throws SQLException if the data could not be retrieved
     */
    public JsonArray loadAllClinics () throws SQLException {
        String query = "SELECT * FROM clinic";
        ResultSet resultSet = statement.executeQuery(query);
        return ResultSetToJSON.toJSON(resultSet);
    }
}
