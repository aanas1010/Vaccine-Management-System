package client_booking;

import entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentCancellation {
    Client client;
    BookableServiceLocation clinic;
    TimePeriod timePeriod;
    String vaccineBrand;
    int appointmentId;

    public AppointmentCancellation(Client client, BookableServiceLocation clinic, TimePeriod timePeriod, String vaccineBrand, int id){
        this.client = client;
        this.clinic = clinic;
        this.timePeriod = timePeriod;
        this.vaccineBrand = vaccineBrand;
        this.appointmentId = id;
    }

    public boolean deleteAppointment(){
        if (this.client.getHasAppointment()) {
            this.clinic.removeAppointmentById(this.appointmentId);
            this.timePeriod.addAvailableSlot();
            return true;
        }
        return false;
    }


}
