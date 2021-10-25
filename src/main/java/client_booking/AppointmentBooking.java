package client_booking;

import entities.BookableServiceLocation;
import entities.VaccineBatch;
import entities.Client;
import entities.TimePeriod;
import java.time.LocalDate;
import java.time.LocalDateTime;


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

    public boolean isAppointmentAvailable(){
        return ((Integer) this.clinic.getSupplyObj().getAvailableVaccines().get(this.vaccineBrand) > 0) &&
                (this.timePeriod.getAvailableSlots() > 0);
    }

    public boolean createAppointment() {
        if(this.isAppointmentAvailable())
        { //Add to list of appointments
            //Remove from availability times
            //Remove from supply
            return true;
        }
        else
        {
            System.out.println("appointment unavailable");
            return false;
        }
    }

}