package databaseintegration;

import javax.json.JsonArray;
import java.sql.SQLException;

public interface DatabaseAppointmentInterface {
    void addAppointment (int appointmentID, int clinicID, String clientID, int periodID, int batchID, String brand) throws SQLException;

    JsonArray loadAppointments(int clinicID) throws SQLException;

    void deleteAppointment (int clinicID, int appointmentID) throws SQLException;
}
