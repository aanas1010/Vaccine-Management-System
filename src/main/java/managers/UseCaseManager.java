package managers;

import constants.ManagementSystemException;
import client_booking.*;
import clinic_management.*;
import entities.*;
import entities.Clinic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;


/**
 * This is the Use Case manager that stores the clinics and manages the other use cases
 */

public class UseCaseManager implements UseCaseManagerInterface {
    //List of clinics, looking to change this into a database
    private final ArrayList<ServiceLocation> clinics;
    private final ArrayList<User> clients;

    //Constructor with empty list of service locations
    public UseCaseManager(){
        this.clinics = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    /** ADDING CLINICS */

    // Add a basic clinic. Return whether the clinic could be added
    public void addClinic(int clinicId, String location) throws ManagementSystemException {
        if(containsClinicWithId(clinicId)) {
            throw new ManagementSystemException(ManagementSystemException.CLINIC_ID_ALREADY_EXISTS);
        }

        clinics.add(new Clinic.ClinicBuilder(clinicId, location).build());
    }

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

    /** Adding Clients **/
    // Add a basic clinic. Return whether the clinic could be added
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

    /** ADDING BATCHES */

    // Call the addBatch function to add a vaccine batch to the selected clinic
    public String addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId) throws ManagementSystemException {
        ServiceLocation clinicById = getClinicById(clinicId);
        VaccineBatch batch = new VaccineBatch(batchBrand, batchQuantity, batchExpiry, batchId);
        BatchAdding newBatch = new BatchAdding(clinicById, batch);
       return newBatch.addBatch();
    }

    /** BOOKING APPOINTMENTS */
    public String bookAppointment(int clinicId, String healthCareNumber,
                                   LocalDateTime appointmentTime, String vaccineBrand, int appointmentId) throws ManagementSystemException {
        ClinicDecorator clinic = (ClinicDecorator) getClinicById(clinicId);
        User client = getClientByHCN(healthCareNumber);
        TimePeriod timePeriod = new TimePeriod(appointmentTime, 0);
        AppointmentBooking appointmentBooking = new AppointmentBooking(client, clinic, timePeriod,
                vaccineBrand, appointmentId);
        return appointmentBooking.createAppointment();
    }

    /** CANCELLING APPOINTMENTS */
    public String cancelAppointment(int clinicId, int appointmentId) throws ManagementSystemException {
        ClinicDecorator clinic = (ClinicDecorator) getClinicById(clinicId);
        AppointmentCancellation appointmentCancellation = new AppointmentCancellation(appointmentId, clinic);

        return appointmentCancellation.deleteAppointment();
    }

    /** VIEWING APPOINTMENTS */
    public String viewAppointment(int clinicId, int appointmentId) throws ManagementSystemException {
        ClinicDecorator clinic = (ClinicDecorator) getClinicById(clinicId);
        AppointmentViewing appointmentViewing = new AppointmentViewing(appointmentId, clinic);

        return appointmentViewing.appointmentDetails();
    }

    /** TIME PERIOD */

    public String setEmployees(int clinicId, LocalDate date, int employees) {
        return createSetTimePeriod(clinicId).setEmployees(date, employees);
    }

    public String addTimePeriod(int clinicId, LocalDateTime dateTime) throws ManagementSystemException {
        return createSetTimePeriod(clinicId).addTimePeriod(dateTime);
    }

    public String removeTimePeriod(int clinicId, LocalDateTime dateTime) throws ManagementSystemException {
        return createSetTimePeriod(clinicId).removeTimePeriod(dateTime);
    }

    public int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval) throws ManagementSystemException {
        return createSetTimePeriod(clinicId).addMultipleTimePeriods(start, end, interval);
    }

    private SetTimePeriod createSetTimePeriod(int clinicId) {
        ServiceLocation clinic = getClinicById(clinicId);
        return new SetTimePeriod(clinic);
    }

    /** RECORD ADDING **/

    public String logAppointment(int clinicId, int appointmentId) throws ManagementSystemException {
        ClinicDecorator clinic = (ClinicDecorator) getClinicById(clinicId);
        return new RecordAdding(clinic).logAppointment(appointmentId);
    }

    public String logWalkIn(int clinicId, String vaccinationID, String clientHCN, LocalDateTime dateTime, String brand) throws ManagementSystemException {
        ClinicDecorator clinic = (ClinicDecorator) getClinicById(clinicId);
        return new RecordAdding(clinic).logWalkIn(vaccinationID, getClientByHCN(clientHCN), dateTime, brand);
    }

    public StringBuilder logByDateTime(int clinicId, LocalDateTime dateTime) throws ManagementSystemException {
        ClinicDecorator clinic = (ClinicDecorator) getClinicById(clinicId);
        return new RecordAdding(clinic).logByDateTime(dateTime);
    }

    public StringBuilder logByDate(int clinicId, LocalDate date) throws ManagementSystemException {
        ClinicDecorator clinic = (ClinicDecorator) getClinicById(clinicId);
        return new RecordAdding(clinic).logByDate(date);
    }

    /** GETTERS */

    //Return a list of the clinic IDs
    public ArrayList<Integer> getClinicIds() {
        int arraySize = clinics.size();
        ArrayList<Integer> clinicNums = new ArrayList<>(arraySize);
        for (ServiceLocation clinic : clinics) {
            //Call the getClinicId method for all clinics
            clinicNums.add(clinic.getServiceLocationId());
        }
        return clinicNums;
    }

    //Return a list of the bookable clinic IDs
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

    public String getSupplyStringByClinic(int clinicId) {
        ServiceLocation clinic = getClinicById(clinicId);
        if(clinic != null) {
            return Objects.requireNonNull(getClinicById(clinicId)).getSupplyObj().toString();
        }else {
            return "";
        }
    }
}
