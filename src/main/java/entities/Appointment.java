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
    VaccineBatch clientVaccineBatch;

    public Appointment(Client client, TimePeriod timePeriod, String vaccineBrand, int id, VaccineBatch clientVaccineBatch){
        this.client = client;
        this.timePeriod = timePeriod;
        this.vaccineBrand = vaccineBrand;
        this.appointmentId = id;
        this.clientVaccineBatch = clientVaccineBatch;
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

    public VaccineBatch getClientVaccineBatch(){return clientVaccineBatch;}
}
