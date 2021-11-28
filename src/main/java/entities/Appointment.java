package entities;

import entities.TimePeriod;
import entities.User;
import entities.VaccineBatch;

import java.time.LocalDateTime;

/**
 * This is the Appointment entity for each appointment instance,
 * which keeps track of the client,
 */

public class Appointment {
    final int appointmentId;
    final User client;
    final TimePeriod timePeriod;
    final String vaccineBrand;
    final VaccineBatch clientVaccineBatch;

    /**
     * constructs the appointment object.
     *
     * @param builder the builder of the appointment.
     */
    public Appointment(AppointmentBuilder builder){
        this.client = builder.client;
        this.timePeriod = builder.timePeriod;
        this.vaccineBrand = builder.vaccineBrand;
        this.appointmentId = builder.appointmentId;
        this.clientVaccineBatch = builder.clientVaccineBatch;
    }

    /**
     * Return whether this appointment's time has already occurred.
     *
     * @return true if the appointment has passed; false otherwise.
     */
    public boolean appointmentTimePassed() {
        return timePeriod.getDateTime().isBefore(LocalDateTime.now());
    }

    /**
     * Return a string of the information of this appointment.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString(){
        return "----------------APPOINTMENT #" + appointmentId + "----------------" +
                "\nCLIENT: " + client.getName() +
                "\nTIME: " + timePeriod.getDateTime() +
                "\nBRAND: " + vaccineBrand +
                "\nBATCH: " + clientVaccineBatch.getId();
    }

    // Getters

    /**
     * getter.
     *
     * @return the id of the appointment.
     */
    public int getAppointmentId() {return appointmentId; }

    /**
     * getter.
     *
     * @return the client assigned in the appointment.
     */
    public User getClient() { return client; }

    /**
     * getter.
     *
     * @return time period in which the appointment is happening.
     */
    public TimePeriod getTimePeriod() { return timePeriod; }

    /**
     * getter.
     *
     * @return vaccine brand used in the appointment.
     */
    public String getVaccineBrand() { return vaccineBrand;}

    /**
     * The builder class for an appointment.
     */
    public static  class AppointmentBuilder {

        private final int appointmentId;
        private final User client;
        private final TimePeriod timePeriod;
        private final String vaccineBrand;
        private final VaccineBatch clientVaccineBatch;

        /**
         * Constructor for an appointment.
         *
         * @param client the client assigned in the appointment.
         * @param timePeriod the time period when the appointment is happening.
         * @param vaccineBrand the vaccine brand used in the appointment.
         * @param id is fo the appointment.
         * @param clientVaccineBatch the vaccine batch reserved for the appointment.
         */
        public AppointmentBuilder(User client, TimePeriod timePeriod, String vaccineBrand, int id, VaccineBatch clientVaccineBatch) {
            this.client = client;
            this.timePeriod = timePeriod;
            this.vaccineBrand = vaccineBrand;
            this.appointmentId = id;
            this.clientVaccineBatch = clientVaccineBatch;
        }

        /**
         * Building the appointment.
         *
         * @return returns the appointment
         */
        public Appointment build(){
            return new Appointment(this);
        }
    }
}

