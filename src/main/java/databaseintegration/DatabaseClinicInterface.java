package databaseintegration;

import javax.json.JsonArray;
import java.sql.SQLException;

public interface DatabaseClinicInterface {
    JsonArray loadAllClinics () throws SQLException;
}
