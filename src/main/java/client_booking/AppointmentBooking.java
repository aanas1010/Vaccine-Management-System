package client_booking;

import entities.*;

import java.time.LocalDate;
import java.util.ArrayList;


public class AppointmentBooking {
    int appointmentId;
    Client client;
    BookableServiceLocation clinic;
    TimePeriod timePeriod;
    String vaccineBrand;

    public AppointmentBooking(Client client, BookableServiceLocation clinic, TimePeriod timePeriod, String vaccineBrand, int id){
        this.client = client;
        this.clinic = clinic;
        this.timePeriod = timePeriod;
        this.vaccineBrand = vaccineBrand;
        this.appointmentId = id;
    }

    //Check if there is an opening for the specified time period
    public boolean isTimeslotAvailable(){return this.timePeriod.getAvailableSlots() > 0;}

    //Reserve a vaccine dose for this client IF there is a timeslot available
    public boolean assignVaccineDose() {
        if (this.isTimeslotAvailable()) {
            ArrayList<VaccineBatch> batchList = this.clinic.getSupplyObj().getBatchList();
            VaccineBatch earliestExpiringVaccine = null;
            LocalDate earliestDate = this.timePeriod.getDateTime().toLocalDate().plusYears(2);
            for (VaccineBatch batch : batchList) {
                if (batch.getBrand().equals(this.vaccineBrand) && !batch.isExpired() && batch.getAvailable() > 0) {
                    if (batch.getExpiry().isBefore(earliestDate)) {
                        earliestDate = batch.getExpiry();
                        earliestExpiringVaccine = batch;
                    } else {
                        System.out.println("Sorry, the chosen clinic does not have any " + this.vaccineBrand + " doses");
                        return false;
                    }
                }
            }
            if (batchList.size() <= 0 || earliestExpiringVaccine == null) {
                System.out.println("Sorry, the chosen clinic does not have any " + this.vaccineBrand + " doses");
                return false;
            }
            earliestExpiringVaccine.changeReserve(1);
            return true;
        }
        return false;
    }

    // Create an appointment for this client in the Clinic's system
    public boolean createAppointment() {
        if(this.isTimeslotAvailable() && this.assignVaccineDose()) {
            Appointment appointment = new Appointment(this.client, this.timePeriod, this.vaccineBrand, this.appointmentId);
            this.clinic.addAppointment(appointment);
            this.timePeriod.findAndReserveSlot();
            return true;
        }else{
            System.out.println("appointment unavailable");
            return false;
        }
    }

}