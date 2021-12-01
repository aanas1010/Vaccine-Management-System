package managers;

import constants.ManagementSystemException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This is the Interface that the Command Line uses.
 * Includes methods for calling use cases
 */

public interface ManagementSystem {

    /**
     * Load the initial data from the retriever
     *
     */
    void loadInitialData();

    /**
     * Load the clinic data from the retriever
     *
     * @param clinicID The ID of the clinic whose info we want to load
     */
    void loadClinicData(int clinicID);

    /**
     * Setting the number of shifts for the given for a certain date
     *
     * @param clinicId The ID of the clinic for which the employees are being set for
     * @param date The date for when the employees are being set
     * @param employees The number of employees being set for the given date
     * @return a string that indicates the number of employees assigned for the chosen date
     */
    String setEmployees(int clinicId, LocalDate date, int employees);

    /**
     * Adding a time period to a clinic if it is not already there
     *
     * @param clinicId The ID of the clinic for which the time period is being added for
     * @param dateTime The date and time for which the timePeriod is being added for
     * @return a string of the timePeriod added
     * @throws ManagementSystemException if no employees have been set for the given date or a time period
     * already exists for the given time
     */
    String addTimePeriod(int clinicId, LocalDateTime dateTime) throws ManagementSystemException;

    /**
     * Removing a time period from a clinic if there exists a time period at the specified date and time.
     *
     * @param clinicId The ID of the clinic for which the time period is being removed
     * @param dateTime The date and time for which the time period is being removed for
     * @return a string that indicates the date and time of the time period that has been removed
     * @throws ManagementSystemException if there is no time period that exists for the chosen date and time
     */
    String removeTimePeriod(int clinicId, LocalDateTime dateTime) throws ManagementSystemException;

    /**
     * Adding multiple time periods from a starting time until the end based on interval inputted in
     * the form of minutes
     *
     * @param clinicId The ID of the clinic for which multiple time periods are being added
     * @param start starting date and time of the first time period
     * @param end ending date time of the last time period
     * @param interval the length (in minutes) of each timePeriod
     * @return the number of timePeriods that have been added
     * @throws ManagementSystemException if the start date and end date are NOT the same, or the interval is
     * less than 1 minute
     */
    int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval)
            throws ManagementSystemException;

    /**
     * Adds the batch to the clinic
     *
     * @param clinicId The ID of the clinic for which the batch being added
     * @param batchBrand The brand of the vaccines in this batch
     * @param batchQuantity The number of vaccine doses in the batch
     * @param batchExpiry The date for which the doses in this batch expire
     * @param batchId The ID of the batch
     * @return the details of the added batch, as a string
     * @throws ManagementSystemException if the batch is expired or the clinic already has a batch with
     * the same ID
     */
    String addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId)
            throws ManagementSystemException;

    /**
     * Gets the IDs of all the clinics
     *
     * @return a list of the clinic IDs
     */
    List<Integer> getClinicIds();

    /**
     * Gets the IDs of all the bookable clinics
     *
     * @return a list of the bookable clinic IDs
     */
    List<Integer> getBookableClinicIds();

    /**
     * Gets the details of the supply (i.e., all the batches) of the specified clinic
     *
     * @param clinicId The clinic for which the details of the supply are wanted for
     * @return a string of all the vaccine batches (and their details) in the clinic's supply
     */
    String getSupplyByClinic(int clinicId);

    /**
     * Log a given appointment to the indicated clinic based on an appointment ID.
     *
     * @param clinicId id of the clinic for which the appointment is being added.
     * @param appointmentId id of the appointment being added
     * @return string description of the logged appointment.
     * @throws ManagementSystemException if the appointment doesn't exist in the location's record,
     * or the appointment time hasn't happened yet.
     */
    String logAppointment(int clinicId, int appointmentId) throws ManagementSystemException;

    /**
     * Log a walk-in appointment for the indicated clinic given the client's details.
     *
     * @param clinicId id of the clinic for which the Walk In appointment is being added.
     * @param vaccinationID id of the walk in vaccination event.
     * @param clientHCN Health Card number of the client who got the vaccine
     * @param dateTime when the walk-in vaccination happened.
     * @param brand used in the walk in event.
     * @return string of appointment description.
     * @throws ManagementSystemException if the date time hasn't happened yet.
     */
    String logWalkIn(int clinicId, String vaccinationID, String clientHCN, LocalDateTime dateTime, String brand)
            throws ManagementSystemException;

    /**
     * Log all appointment on a certain date and time.
     *
     * @param clinicId id of the clinic for which the appointments are being added based on the date and time.
     * @param dateTime for which the appointments are added.
     * @return StringBuilder of string appointments.
     * @throws ManagementSystemException if the time hasn't passed yet or does not exist in the clinic.
     */
    StringBuilder logByDateTime(int clinicId, LocalDateTime dateTime) throws ManagementSystemException;

    /**
     * Log all appointments on a given date.
     *
     * @param clinicId id of the clinic for which the appointments are being added based on the date.
     * @param date for which the appointments are added.
     * @return StringBuilder of date time in a time period on a date in a location/
     * @throws ManagementSystemException if location does not have time slot that day
     */
    StringBuilder logByDate(int clinicId, LocalDate date) throws ManagementSystemException;

    /**
     * Book an appointment given the chosen clinic's ID and the details of the client
     *
     * @param clinicId id of the clinic for which the appointment is being booked for
     * @param healthCareNumber the health card number of the
     * @param appointmentTime the date and time of which the appointment will take place, should the
     *                        appointment be booked successfully
     * @param vaccineBrand the vaccine brand for the appointment
     * @param appointmentId the ID of the appointment
     * @return a string of the details of the booked appointment
     * @throws ManagementSystemException if there are no appointments available for the selected date and time,
     * or an appointment with the chosen ID already exists for this clinic
     */
    String bookAppointment(int clinicId, String healthCareNumber, LocalDateTime appointmentTime,
                           String vaccineBrand, int appointmentId) throws ManagementSystemException;

    /**
     * Cancel an appointment given the chosen clinic's ID and the appointment ID
     *
     * @param clinicId id of the clinic from which the appointment is being cancelled
     * @param appointmentId the ID of the appointment
     * @return the details of the appointment that had just been cancelled
     * @throws ManagementSystemException if an appointment with the specified appointment ID does not exist in
     * the clinic's log
     */
    String cancelAppointment(int clinicId, int appointmentId) throws ManagementSystemException;

    /**
     * View a previously booked appointment given the chosen clinic's ID and appointment ID
     *
     * @param clinicId id of the clinic for which the appointment is being viewed
     * @param appointmentId the ID of the appointment
     * @return the details of the appointment that you've chosen to view
     * @throws ManagementSystemException if an appointment with the specified appointment ID does not exist in
     * the clinic's log
     */
    String viewAppointment(int clinicId, int appointmentId) throws ManagementSystemException;
}
