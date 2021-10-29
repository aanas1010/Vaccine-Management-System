package clinic_management;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the Interface that the Vaccine Management System uses.
 * Includes methods for managing clinics
 */

public interface ClinicManagerInterface {

    ArrayList<Integer> getClinicIds();

    boolean setEmployees(int clinicId, LocalDate date, int employees);

    boolean addTimePeriod(int clinicId, LocalDateTime dateTime);

    boolean removeTimePeriod(int clinicId, LocalDateTime dateTime);

    int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval);

    boolean addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId);

    String getSupplyStringByClinic(int clinicId);

}
