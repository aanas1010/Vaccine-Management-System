package databaseintegration;

import javax.json.JsonArray;
import java.sql.SQLException;

public interface DatabaseClinicInterface {
    boolean addClinic (int clinicID, String location, boolean isBookable) throws SQLException;

    JsonArray loadAllClinics () throws SQLException;
}
