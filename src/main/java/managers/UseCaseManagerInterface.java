package managers;

import constants.ManagementSystemException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the Interface that a management system may use.
 * Includes methods for calling sub-use cases
 */

public interface UseCaseManagerInterface {
    List<Integer> getClinicIds();

    List<Integer> getBookableClinicIds();

    void addClinic(int clinicId, String location) throws ManagementSystemException;

    void addBookableClinic(int clinicId, String location) throws ManagementSystemException;

    void addClient(String name, String healthCardNumber) throws ManagementSystemException;

    boolean loadInitialData();

    boolean loadClinicData(int clinicID);

    String setEmployees(int clinicId, LocalDate date, int employees);

    String addTimePeriod(int clinicId, LocalDateTime dateTime) throws ManagementSystemException;

    String removeTimePeriod(int clinicId, LocalDateTime dateTime) throws ManagementSystemException;

    int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval) throws ManagementSystemException;

    String addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId) throws ManagementSystemException;

    String getSupplyStringByClinic(int clinicId);

    String logAppointment(int clinicId, int appointmentId) throws ManagementSystemException;

    String logWalkIn(int clinicId, String vaccinationID, String clientHCN, LocalDateTime dateTime, String brand) throws ManagementSystemException;

    StringBuilder logByDateTime(int clinicId, LocalDateTime dateTime) throws ManagementSystemException;

    StringBuilder logByDate(int clinicId, LocalDate date) throws ManagementSystemException;

    String bookAppointment(int clinicId, String healthCareNumber,
                            LocalDateTime appointmentTime, String vaccineBrand, int appointmentId) throws ManagementSystemException;

    String cancelAppointment(int clinicId, int appointmentId) throws ManagementSystemException;

    String viewAppointment(int clinicId, int appointmentId) throws ManagementSystemException;

}
