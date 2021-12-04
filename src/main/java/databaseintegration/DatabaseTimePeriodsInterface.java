package databaseintegration;

import javax.json.JsonArray;
import java.sql.SQLException;
import java.sql.Timestamp;

public interface DatabaseTimePeriodsInterface {
    JsonArray loadTimePeriods(int clinicID) throws SQLException;

    void addTimePeriod (int periodID, int clinicID, int availableSlots, int bookedSlots, Timestamp datetime) throws SQLException;

    void updateTimePeriods(int clinicID, int periodID, int availableSlots, int bookedSlots) throws SQLException;
}
