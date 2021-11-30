package managers;

import constants.ManagementSystemException;
import client_booking.*;
import clinic_management.*;
import entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;


/**
 * This is the Use Case manager that stores the clinics and manages the other use cases
 */

public class UseCaseManager implements UseCaseManagerInterface {
    private final ArrayList<ServiceLocation> clinics;
    private final ArrayList<User> clients;

    /**
     * Stores clinics and manages other use cases. This constructor creates an empty list of service locations
     * and an empty list of clients.
     */
    public UseCaseManager(){
        this.clinics = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    /**
     * Adding a basic clinic. Note that adding a regular clinic doesn't mean that it will be accepting
     * vaccine appointments.
     *
     * @param clinicId The ID of the clinic that is going to be added
     * @param location Where this clinic is located i.e., address
     * @throws ManagementSystemException if a clinic with the specified clinic ID already exists
     */
    public void addClinic(int clinicId, String location) throws ManagementSystemException {
        if(containsClinicWithId(clinicId)) {
            throw new ManagementSystemException(ManagementSystemException.CLINIC_ID_ALREADY_EXISTS);
        }

        clinics.add(new Clinic.ClinicBuilder(clinicId, location).build());
    }

    /**
     * Adding a bookable clinic. Bookable clinics allow for vaccine appointments to be booked.
     *
     * @param clinicId The ID of the clinic that is going to be added
     * @param location Where this clinic is located i.e., address
     * @throws ManagementSystemException if a clinic with the specified clinic ID already exists
     */
    public void addBookableClinic(int clinicId, String location) throws ManagementSystemException {
        if(containsClinicWithId(clinicId)) {
            throw new ManagementSystemException(ManagementSystemException.CLINIC_ID_ALREADY_EXISTS);
        }

        clinics.add(new BookableClinic(new Clinic.ClinicBuilder(clinicId, location).build()));
    }

    private boolean containsClinicWithId(int clinicId) {
        for(ServiceLocation location : clinics) {
            if(location.getServiceLocationId() == clinicId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adding a client to the system given the name and health card number
     *
     * @param name The full name of the client who wants to get vaccinated
     * @param healthCareNumber The client's health card number
     * @throws ManagementSystemException if a client with the specified health card number already exists in
     * the system
     */
    public void addClient(String name, String healthCareNumber) throws ManagementSystemException {
        if(containsClientWithHCN(healthCareNumber)) {
            throw new ManagementSystemException(ManagementSystemException.HCN_ALREADY_EXISTS);
        }

        clients.add(new Client(name, healthCareNumber));
    }

    private boolean containsClientWithHCN(String healthCardNumber) {
        for(User client : clients) {
            if(client.getHealthCareNumber().equals(healthCardNumber)) {
                return true;
            }
        }
        return false;
    }

    private User getClientByHCN(String healthCardNumber) throws ManagementSystemException {
        for(User client : clients) {
            if(client.getHealthCareNumber().equals(healthCardNumber)) {
                return client;
            }
        }
        throw new ManagementSystemException(ManagementSystemException.HCN_DOES_NOT_EXIST);
    }

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
    public String addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry,
                           int batchId) throws ManagementSystemException {
        ServiceLocation clinicById = getClinicById(clinicId);
        VaccineBatch batch = new VaccineBatch.BatchBuilder(batchBrand, batchQuantity, batchExpiry, batchId).build();
        BatchAdding newBatch = new BatchAdding(clinicById, batch);
        return newBatch.addBatch();
    }

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
    public String bookAppointment(int clinicId, String healthCareNumber, LocalDateTime appointmentTime,
                                  String vaccineBrand, int appointmentId) throws ManagementSystemException {
        ClinicDecorator clinic = (ClinicDecorator) getClinicById(clinicId);
        User client = getClientByHCN(healthCareNumber);
        TimePeriod timePeriod = new TimePeriod(appointmentTime, 0);
        AppointmentBooking appointmentBooking = new AppointmentBooking(client, clinic, timePeriod,
                vaccineBrand, appointmentId);
        return appointmentBooking.createAppointment();
    }

    /**
     * Cancel an appointment given the chosen clinic's ID and the appointment ID
     *
     * @param clinicId id of the clinic from which the appointment is being cancelled
     * @param appointmentId the ID of the appointment
     * @return the details of the appointment that had just been cancelled
     * @throws ManagementSystemException if an appointment with the specified appointment ID does not exist in
     * the clinic's log
     */
    public String cancelAppointment(int clinicId, int appointmentId) throws ManagementSystemException {
        ClinicDecorator clinic = (ClinicDecorator) getClinicById(clinicId);
        AppointmentCancellation appointmentCancellation = new AppointmentCancellation(appointmentId, clinic);

        return appointmentCancellation.deleteAppointment();
    }

    /**
     * View a previously booked appointment given the chosen clinic's ID and appointment ID
     *
     * @param clinicId id of the clinic for which the appointment is being viewed
     * @param appointmentId the ID of the appointment
     * @return the details of the appointment that you've chosen to view
     * @throws ManagementSystemException if an appointment with the specified appointment ID does not exist in
     * the clinic's log
     */
    public String viewAppointment(int clinicId, int appointmentId) throws ManagementSystemException {
        ClinicDecorator clinic = (ClinicDecorator) getClinicById(clinicId);
        AppointmentViewing appointmentViewing = new AppointmentViewing(appointmentId, clinic);

        return appointmentViewing.appointmentDetails();
    }

    /**
     * Setting the number of shifts for the given for a certain date
     *
     * @param clinicId The ID of the clinic for which the employees are being set for
     * @param date The date for when the employees are being set
     * @param employees The number of employees being set for the given date
     * @return a string that indicates the number of employees assigned for the chosen date
     */
    public String setEmployees(int clinicId, LocalDate date, int employees) {
        return createSetTimePeriod(clinicId).setEmployees(date, employees);
    }

    /**
     * Adding a time period to a clinic if it is not already there
     *
     * @param clinicId The ID of the clinic for which the time period is being added for
     * @param dateTime The date and time for which the timePeriod is being added for
     * @return a string of the timePeriod added
     * @throws ManagementSystemException if no employees have been set for the given date or a time period
     * already exists for the given time
     */
    public String addTimePeriod(int clinicId, LocalDateTime dateTime) throws ManagementSystemException {
        return createSetTimePeriod(clinicId).addTimePeriod(dateTime);
    }

    /**
     * Removing a time period from a clinic if there exists a time period at the specified date and time.
     *
     * @param clinicId The ID of the clinic for which the time period is being removed
     * @param dateTime The date and time for which the time period is being removed for
     * @return a string that indicates the date and time of the time period that has been removed
     * @throws ManagementSystemException if there is no time period that exists for the chosen date and time
     */
    public String removeTimePeriod(int clinicId, LocalDateTime dateTime) throws ManagementSystemException {
        return createSetTimePeriod(clinicId).removeTimePeriod(dateTime);
    }

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
    public int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval)
            throws ManagementSystemException {
        return createSetTimePeriod(clinicId).addMultipleTimePeriods(start, end, interval);
    }

    private SetTimePeriod createSetTimePeriod(int clinicId) {
        ServiceLocation clinic = getClinicById(clinicId);
        return new SetTimePeriod(clinic);
    }

    /**
     * Log a given appointment to the indicated clinic based on an appointment ID.
     *
     * @param clinicId id of the clinic for which the appointment is being added.
     * @param appointmentId id of the appointment being added
     * @return string description of the logged appointment.
     * @throws ManagementSystemException if the appointment doesn't exist in the location's record,
     * or the appointment time hasn't happened yet.
     */
    public String logAppointment(int clinicId, int appointmentId) throws ManagementSystemException {
        ClinicDecorator clinic = (ClinicDecorator) getClinicById(clinicId);
        return new RecordAdding(clinic).logAppointment(appointmentId);
    }

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
    public String logWalkIn(int clinicId, String vaccinationID, String clientHCN, LocalDateTime dateTime,
                            String brand) throws ManagementSystemException {
        ClinicDecorator clinic = (ClinicDecorator) getClinicById(clinicId);
        return new RecordAdding(clinic).logWalkIn(vaccinationID, getClientByHCN(clientHCN), dateTime, brand);
    }

    /**
     * Log all appointment on a certain date and time.
     *
     * @param clinicId id of the clinic for which the appointments are being added based on the date and time.
     * @param dateTime for which the appointments are added.
     * @return StringBuilder of string appointments.
     * @throws ManagementSystemException if the time hasn't passed yet or does not exist in the clinic.
     */
    public StringBuilder logByDateTime(int clinicId, LocalDateTime dateTime) throws ManagementSystemException {
        ClinicDecorator clinic = (ClinicDecorator) getClinicById(clinicId);
        return new RecordAdding(clinic).logByDateTime(dateTime);
    }

    /**
     * Log all appointments on a given date.
     *
     * @param clinicId id of the clinic for which the appointments are being added based on the date.
     * @param date for which the appointments are added.
     * @return StringBuilder of date time in a time period on a date in a location/
     * @throws ManagementSystemException if location does not have time slot that day
     */
    public StringBuilder logByDate(int clinicId, LocalDate date) throws ManagementSystemException {
        ClinicDecorator clinic = (ClinicDecorator) getClinicById(clinicId);
        return new RecordAdding(clinic).logByDate(date);
    }

   /**
     * Gets the IDs of all the clinics
     *
     * @return a list of the clinic IDs
     */
    public ArrayList<Integer> getClinicIds() {
        int arraySize = clinics.size();
        ArrayList<Integer> clinicNums = new ArrayList<>(arraySize);
        for (ServiceLocation clinic : clinics) {
            //Call the getClinicId method for all clinics
            clinicNums.add(clinic.getServiceLocationId());
        }
        return clinicNums;
    }

    /**
     * Gets the IDs of all the bookable clinics
     *
     * @return a list of the bookable clinic IDs
     */
    public ArrayList<Integer> getBookableClinicIds() {
        int arraySize = clinics.size();
        ArrayList<Integer> clinicNums = new ArrayList<>(arraySize);
        for (ServiceLocation clinic : clinics) {
            if(clinic instanceof ClinicDecorator) {
                //Call the getClinicId method for all clinics
                clinicNums.add(clinic.getServiceLocationId());
            }
        }
        return clinicNums;
    }

    private ServiceLocation getClinicById(int clinicId) {
        for (ServiceLocation clinic : clinics) {
            //Call the getClinicId method for all clinics
            if(clinic.getServiceLocationId() == clinicId) {
                return clinic;
            }
        }
        return null;
    }

    /**
     * Gets the details of the supply (i.e., all the batches) of the specified clinic
     *
     * @param clinicId The clinic for which the details of the supply are wanted for
     * @return a string of all the vaccine batches (and their details) in the clinic's supply
     */
    public String getSupplyStringByClinic(int clinicId) {
        ServiceLocation clinic = getClinicById(clinicId);
        if(clinic != null) {
            return Objects.requireNonNull(getClinicById(clinicId)).getSupplyObj().toString();
        }else {
            return "";
        }
    }
}
