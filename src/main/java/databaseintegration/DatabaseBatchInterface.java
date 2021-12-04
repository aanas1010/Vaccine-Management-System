package databaseintegration;

import javax.json.JsonArray;
import java.sql.Date;
import java.sql.SQLException;

public interface DatabaseBatchInterface {
    void addBatch (int batchID, int clinicID, String brand, Date expiryDate, int reserved, int quantity) throws SQLException;

    JsonArray loadBatches(int clinicID) throws SQLException;

    void updateReservedBatch (int clinicID, int batchID, int reserved) throws SQLException;
}
