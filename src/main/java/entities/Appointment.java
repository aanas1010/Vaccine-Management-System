package entities;

import java.time.LocalDateTime;

/**
 * This is the Appointment entity for each appointment instance,
 * which keeps track of the client,
 */

public class Appointment {
    int appointmentId;
    User client;
    TimePeriod timePeriod;
    String vaccineBrand;
    VaccineBatch clientVaccineBatch;

    public Appointment(User client, TimePeriod timePeriod, String vaccineBrand, int id, VaccineBatch clientVaccineBatch){
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

    @Override
    public String toString(){
        return "----------------APPOINTMENT #" + appointmentId + "----------------" +
                "\nCLIENT: " + client.getName() +
                "\nTIME: " + timePeriod.getDateTime() +
                "\nBRAND: " + vaccineBrand +
                "\nBATCH: " + clientVaccineBatch.getId();
    }

    // Getters
    public int getAppointmentId() {return appointmentId; }

    public User getClient() { return client; }

    public TimePeriod getTimePeriod() { return timePeriod; }

    public String getVaccineBrand() { return vaccineBrand;}
}
