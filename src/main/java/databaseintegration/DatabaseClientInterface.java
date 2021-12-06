package databaseintegration;

import javax.json.JsonArray;
import java.sql.SQLException;

public interface DatabaseClientInterface {
    void addClient(String healthCareID, String name, boolean hasAppointment) throws SQLException;

    JsonArray loadAllClients() throws SQLException;

    void updateToNoAppointment(String healthCareID) throws SQLException;

    void updateToHasAppointment(String healthCareID) throws SQLException;
}
