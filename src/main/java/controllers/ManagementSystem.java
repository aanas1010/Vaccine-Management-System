package controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the Interface that the Command Line uses.
 * Includes methods for calling use cases
 */

public interface ManagementSystem {

    boolean setEmployees(int clinicId, LocalDate date, int employees);

    boolean addTimePeriod(int clinicId, LocalDateTime dateTime);

    boolean removeTimePeriod(int clinicId, LocalDateTime dateTime);

    int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval);

    boolean addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId);

    ArrayList<Integer> getClinicIds();

    String getSupplyByClinic(int clinicId);
}
