package databaseintegration;

import javax.json.*;
import java.sql.*;
import java.util.Date;

public class ResultSetToJSON {
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
                    jsonObject.add(column, ((Date) value).getTime());
                } else {
                    throw new IllegalArgumentException("Unmappable object type: " + value.getClass());
                }
            }
            jArray.add(jsonObject);
        }while(rs.next());

        return jArray.build();
    }
}