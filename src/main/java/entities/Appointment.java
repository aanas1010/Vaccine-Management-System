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
        assert(builder.client != null);
        this.client = builder.client;
        assert(builder.timePeriod != null);
        this.timePeriod = builder.timePeriod;
        assert(builder.vaccineBrand != null);
        this.vaccineBrand = builder.vaccineBrand;
        assert(builder.appointmentId != -1);
        this.appointmentId = builder.appointmentId;
        assert(builder.clientVaccineBatch != null);
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
     * getter.
     *
     * @return vaccine batch used in the appointment.
     */
    public VaccineBatch getClientVaccineBatch() { return clientVaccineBatch;}

    /**
     * The builder class for an appointment.
     */
    public static  class AppointmentBuilder {

        private int appointmentId;
        private User client;
        private TimePeriod timePeriod;
        private String vaccineBrand;
        private VaccineBatch clientVaccineBatch;

        /**
         * Constructor for an appointment builder.
         */
        public AppointmentBuilder() {
            this.appointmentId = -1;
        }

        /**
         * assigns a client to the appointment builder.
         * @param client the client of the appointment.
         * @return the builder of the appointment.
         */
        public AppointmentBuilder client(User client){
            this.client = client;
            return this;
        }

        /**
         * assigns a time period to the appointment builder.
         * @param timePeriod the time period of the appointment.
         * @return the builder of the appointment.
         */
        public AppointmentBuilder timePeriod(TimePeriod timePeriod){
            this.timePeriod = timePeriod;
            return this;
        }

        /**
         * assigns a vaccine brand to the appointment builder.
         * @param brand the vaccine brand of the appointment.
         * @return the builder of the appointment.
         */
        public AppointmentBuilder vaccineBrand(String brand){
            this.vaccineBrand = brand;
            return this;
        }

        /**
         * assigns an id to the appointment builder.
         * @param id the id of the appointment.
         * @return the builder of the appointment.
         */
        public AppointmentBuilder appointmentID(int id){
            this.appointmentId = id;
            return this;
        }

        /**
         * assigns a client to the appointment builder.
         * @param batch the vaccine batch of the appointment.
         * @return the builder of the appointment.
         */
        public AppointmentBuilder clientVaccineBatch(VaccineBatch batch){
            this.clientVaccineBatch = batch;
            return this;
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

