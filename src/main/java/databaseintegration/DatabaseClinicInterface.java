package databaseintegration;

import javax.json.JsonArray;
import java.sql.SQLException;

/**
 * This is the interface relating to the database Clinic table
 */

public interface DatabaseClinicInterface {

    /** Load all clinics in the system
     *
     * @return a JsonArray of all clinics
     * @throws SQLException if the data could not be retrieved
     */
    JsonArray loadAllClinics () throws SQLException;
}
