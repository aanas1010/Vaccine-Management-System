package databaseintegration;

import javax.json.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * This is the class for converting a ResultSet to a JsonArray
 */

public class ResultSetToJSON {

    /** Convert a resultSet into a JsonArray
     *
     * @param rs a ResultSet object
     * @return a JsonArray containing the same informatino as the ResultSet
     * @throws SQLException if the data could not be retrieved
     * @throws JsonException if the data could not be converted to a JsonArray
     */
    public static JsonArray toJSON(ResultSet rs) throws SQLException, JsonException {
        // If rs is empty, return an empty JsonArray
        if (!rs.isBeforeFirst() ) {
            return Json.createArrayBuilder().build();
        }

        // Initialize Json array and objects to be added
        JsonArrayBuilder jArray = Json.createArrayBuilder();
        JsonObjectBuilder jsonObject = Json.createObjectBuilder();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        rs.next();

        addToJsonObject(rs, jArray, jsonObject, rsmd, columnCount);

        return jArray.build();
    }

    /** Add the elements of a ResultSet to a JsonArrayBuilder
     *
     * @param rs a ResultSet instance
     * @param jArray the JsonArrayBuilder that we are modifying
     * @param jsonObject the temporary JsonObjectBuilder
     * @param rsmd the metadata of the ResultSet
     * @param columnCount the number of columns in this ResultSet
     * @throws SQLException if the data could not be retrieved
     */
    private static void addToJsonObject(
            ResultSet rs, JsonArrayBuilder jArray,
            JsonObjectBuilder jsonObject, ResultSetMetaData rsmd, int columnCount) throws SQLException {
        do {
            for (int i = 1; i <= columnCount; i++) {
                String column = rsmd.getColumnName(i);
                Object value = rs.getObject(column);

                if (value == null) {
                    jsonObject.add(column, "");
                } else if (value instanceof Integer) {
                    jsonObject.add(column, (Integer) value);
                } else if (value instanceof String) {
                    jsonObject.add(column, (String) value);
                } else if (value instanceof Boolean) {
                    jsonObject.add(column, (Boolean) value);
                } else if (value instanceof Date) {
                    jsonObject.add(column, value.toString());
                } else if (value instanceof LocalDateTime) {
                    jsonObject.add(column, value.toString());
                } else {
                    throw new IllegalArgumentException("Unmappable object type: " + value.getClass());
                }
            }
            jArray.add(jsonObject);
        }while(rs.next());
    }
}