package client_booking;

import entities.*;

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
        {
            Appointment appointment = new Appointment(this.client, this.timePeriod, this.vaccineBrand, this.appointmentId);
            this.clinic.addAppointment(appointment);
            this.timePeriod.findAndReserveSlot();
            this.clinic.getSupplyObj().removeDose();
            return true;
        }
        else
        {
            System.out.println("appointment unavailable");
            return false;
        }
    }

}