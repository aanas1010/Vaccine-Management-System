package entities.VaccineBatchBuilder;

import entities.TimePeriod;
import entities.User;
import entities.VaccineBatch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This is the Appointment entity for each appointment instance,
 * which keeps track of the client,
 */

public class Appointment1 {
    int appointmentId;
    User client;
    TimePeriod timePeriod;
    String vaccineBrand;
    VaccineBatch clientVaccineBatch;

    public Appointment1(AppointmentBuilder builder){
        this.client = builder.client;
        this.timePeriod = builder.timePeriod;
        this.vaccineBrand = builder.vaccineBrand;
        this.appointmentId = builder.appointmentId;
        this.clientVaccineBatch = builder.clientVaccineBatch;
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

    public static  class AppointmentBuilder {

        private final int appointmentId;
        private final User client;
        private final TimePeriod timePeriod;
        private final String vaccineBrand;
        private final VaccineBatch clientVaccineBatch;

        // Constructor for an appointment
        public AppointmentBuilder(User client, TimePeriod timePeriod, String vaccineBrand, int id, VaccineBatch clientVaccineBatch) {
            this.client = client;
            this.timePeriod = timePeriod;
            this.vaccineBrand = vaccineBrand;
            this.appointmentId = id;
            this.clientVaccineBatch = clientVaccineBatch;
        }

        // Building the appointment
        public Appointment1 build(){
            Appointment1 appointment = new Appointment1(this);
//            validateAppointment(appointment);
            return appointment;
        }

        // Possibly add a validation check in the future
//        private void validateAppointment(Appointment1 batch){
//
//        }
    }
}

