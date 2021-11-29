package databaseintegration;

import java.sql.ResultSet;

/**
 * This is the interface classes that read and write data to and from a database
 * Each method only requires a query for versatility
 * This interface corresponds to classes that lie on the "Drivers" layer of clean architecture
 */

//TODO: Are these methods sufficient for driver-level classes? Do we even need all of them?

public interface DatabaseTable {
    ResultSet getFromTable(String query);

    void setValueInTable(String query);

    void deleteValueInTable(String query);

    void modifyValueInTable(String query);
}
