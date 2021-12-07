package databaseintegration;

import javax.json.JsonArray;
import java.sql.SQLException;

/**
 * This is the interface relating to the database Client table
 */

public interface DatabaseClientInterface {
    /** Load all clients in the system
     *
     * @return JsonArray of the clients
     * @throws SQLException if the data could not be retrieved
     */
    JsonArray loadAllClients() throws SQLException;

    /** Update a client's 'hasAppointment' property to false
     *
     * @param healthCareID the HCN of the client that we want to update
     * @throws SQLException if the data could not be written
     */
    void updateToNoAppointment(String healthCareID) throws SQLException;

    /** Update a client's 'hasAppointment' property to true
     *
     * @param healthCareID the HCN of the client that we want to update
     * @throws SQLException if the data could not be written
     */
    void updateToHasAppointment(String healthCareID) throws SQLException;
}
