package entities;

import java.time.LocalDateTime;

/**
 * This is the Appointment entity for each appointment instance,
 * which keeps track of the client,
 */

public class Appointment {
    int appointmentId;
    Client client;
    TimePeriod timePeriod;
    String vaccineBrand;

    public Appointment(Client client, TimePeriod timePeriod, String vaccineBrand, int id){
        this.client = client;
        this.timePeriod = timePeriod;
        this.vaccineBrand = vaccineBrand;
        this.appointmentId = id;
    }

    // Return whether this appointment's time has already occurred
    public boolean appointmentTimePassed() {
        return timePeriod.getDateTime().isBefore(LocalDateTime.now());
    }

    // Getters
    public int getAppointmentId() {return appointmentId; }

    public Client getClient() { return client; }

    public TimePeriod getTimePeriod() { return timePeriod; }

    public String getVaccineBrand() { return vaccineBrand;}
}
