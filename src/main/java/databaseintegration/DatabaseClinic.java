package databaseintegration;

import javax.json.JsonArray;
import java.sql.*;

public class DatabaseClinic implements DatabaseClinicInterface{
    private final Connection connection;
    private final Statement statement;

    public DatabaseClinic (Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public JsonArray loadAllClinics () throws SQLException {
        String query = "SELECT * FROM clinic";
        ResultSet resultSet = statement.executeQuery(query);
        return ResultSetToJSON.toJSON(resultSet);
    }
}
