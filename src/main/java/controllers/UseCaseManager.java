package controllers;

import client_booking.AppointmentBooking;
import client_booking.AppointmentCancellation;
import client_booking.AppointmentViewing;
import clinic_management.BatchAdding;
import clinic_management.SetTimePeriod;
import entities.*;

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

    //Constructor for a list of clinics
    public UseCaseManager(ArrayList<ServiceLocation> clinics){
        this.clinics = clinics;
    }

    //Constructor for num clinics with IDs 0 to num-1
    public UseCaseManager(int num) {
        clinics = new ArrayList<>();

        for(int i=0;i<num;i++) {
            //Create new clinic with ID i
            addClinic(i);
        }
    }

    /** ADDING CLINICS */

    // Add a basic clinic. Return whether the clinic could be added
    public void addClinic(int clinicId) {
        if(containsClinicWithId(clinicId)) {return;}

        clinics.add(new Clinic(clinicId));
    }

    private boolean containsClinicWithId(int clinicId) {
        for(ServiceLocation location : clinics) {
            if(location.getServiceLocationId() == clinicId) {
                return true;
            }
        }
        return false;
    }

    /** ADDING BATCHES */

    // Call the addBatch function to add a vaccine batch to the selected clinic
    public boolean addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId){
        ServiceLocation clinicById = getClinicById(clinicId);
        VaccineBatch batch = new VaccineBatch(batchBrand, batchQuantity, batchExpiry, batchId);
        BatchAdding newBatch = new BatchAdding(clinicById, batch);
        return newBatch.addBatch();
    }

    /** BOOKING APPOINTMENTS */
    public boolean bookAppointment(int clinicId, String clientName, String healthCareNumber,
                                   LocalDateTime appointmentTime, String vaccineBrand, int appointmentId) {
        BookableClinic clinic = (BookableClinic) getClinicById(clinicId);
        Client client = new Client(clientName, healthCareNumber);
        TimePeriod timePeriod = new TimePeriod(appointmentTime, 0);
        AppointmentBooking appointmentBooking = new AppointmentBooking(client, clinic, timePeriod,
                vaccineBrand, appointmentId);
        return appointmentBooking.createAppointment();
    }

    /** CANCELLING APPOINTMENTS */
    public boolean cancelAppointment(int clinicId, int appointmentId) {
        BookableClinic clinic = (BookableClinic) getClinicById(clinicId);
        AppointmentCancellation appointmentCancellation = new AppointmentCancellation(appointmentId, clinic);

        return appointmentCancellation.deleteAppointment();
    }

    /** VIEWING APPOINTMENTS */
    public String viewAppointment(int clinicId, int appointmentId){
        BookableClinic clinic = (BookableClinic) getClinicById(clinicId);
            AppointmentViewing appointmentViewing = new AppointmentViewing(appointmentId, clinic);

            return appointmentViewing.appointmentDetails();
        }

    /** TIME PERIOD */

    public boolean setEmployees(int clinicId, LocalDate date, int employees) {
        return createSetTimePeriod(clinicId).setEmployees(date, employees);
    }

    public boolean addTimePeriod(int clinicId, LocalDateTime dateTime) {
        return createSetTimePeriod(clinicId).addTimePeriod(dateTime);
    }

    public boolean removeTimePeriod(int clinicId, LocalDateTime dateTime){
        return createSetTimePeriod(clinicId).removeTimePeriod(dateTime);
    }

    public int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval) {
        return createSetTimePeriod(clinicId).addMultipleTimePeriods(start, end, interval);
    }

    private SetTimePeriod createSetTimePeriod(int clinicId) {
        ServiceLocation clinic = getClinicById(clinicId);
        return new SetTimePeriod(clinic);
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

    public boolean AppointmentBooking(Client client, ClinicDecorator clinic,
                                      TimePeriod timePeriod, String vaccineBrand, int appointmentId)
    {
        AppointmentBooking book = new AppointmentBooking(client, clinic, timePeriod, vaccineBrand, appointmentId);
        return book.createAppointment();
    }

    public boolean AppointmentCancellation(int appointmentId, ClinicDecorator clinic)
    {
        AppointmentCancellation cancel = new AppointmentCancellation(appointmentId, clinic);
        return cancel.deleteAppointment();
    }

    public String AppointmentViewing(int appointmentId, ClinicDecorator clinic)
    {
        AppointmentViewing view = new AppointmentViewing(appointmentId, clinic);
        return view.appointmentDetails();
    }
}
