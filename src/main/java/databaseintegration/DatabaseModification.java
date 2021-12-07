package databaseintegration;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This is the adapter for writing data into the database.
 * The methods here consist of writing methods that write to
 * specific tables in a database, whose URL is specified in BookingConstants
 */

public class DatabaseModification implements DataModification {
    private final DatabaseClientInterface databaseClient;
    private final DatabaseBatchInterface databaseBatch;
    private final DatabaseTimePeriodsInterface databaseTimePeriods;
    private final DatabaseAppointmentInterface databaseAppointment;

    public DatabaseModification(ModifierBuilder builder) {
        assert(builder.databaseClient != null);
        this.databaseClient = builder.databaseClient;
        assert(builder.databaseBatch != null);
        this.databaseBatch = builder.databaseBatch;
        assert(builder.databaseTimePeriods != null);
        this.databaseTimePeriods = builder.databaseTimePeriods;
        assert(builder.databaseAppointment != null);
        this.databaseAppointment = builder.databaseAppointment;
    }


    /** Write information for a new appointment
     *
     * @param appointmentID the ID of the appointment
     * @param clinicID the ID of the clinic
     * @param clientID the HCN of the client
     * @param periodID the ID of the time period
     * @param batchID the ID of the batch
     * @param brand the brand of the vaccine
     * @throws SQLException if the data could not be written
     */
    public void writeToAppointment(int appointmentID, int clinicID, String clientID, int periodID, int batchID,
                                   String brand) throws SQLException {
        databaseAppointment.addAppointment(appointmentID, clinicID, clientID, periodID, batchID, brand);
    }

    /** Write information for a new time period
     *
     * @param periodID the ID of the time period
     * @param clinicID the ID of the clinic
     * @param availableSlots the number of available slots for this time period
     * @param bookedSlots the number of reserved slots for this time period
     * @param datetime the datetime of this time period
     * @throws SQLException if the data could not be written
     */
    public void writeToTimePeriods(int periodID, int clinicID, int availableSlots, int bookedSlots,
                                   LocalDateTime datetime) throws SQLException {
        databaseTimePeriods.addTimePeriod(periodID, clinicID, availableSlots, bookedSlots, Timestamp.valueOf(datetime));
    }

    /** Write information for a new vaccine batch
     *
     * @param batchID the ID of the batch
     * @param clinicID the ID of the clinic
     * @param brand the brand of this batch
     * @param expiryDate the expiry date for this batch
     * @param reserved the number of reserved doses
     * @param quantity the number of total doses
     * @throws SQLException if the data could not be written
     */
    public void writeToVaccineBatch(int batchID, int clinicID, String brand,
                                    LocalDate expiryDate, int reserved, int quantity) throws SQLException {
        databaseBatch.addBatch(batchID, clinicID, brand, Date.valueOf(expiryDate), reserved, quantity);
    }

    /** Delete an appointment
     *
     * @param clinicID the ID of the clinic
     * @param appointmentID the ID of the appointment
     * @throws SQLException if the data could not be deleted
     */
    public void deleteFromAppointments(int clinicID, int appointmentID) throws SQLException {
        databaseAppointment.deleteAppointment(clinicID, appointmentID);
    }

    /** Update the reserved quantity of a batch
     *
     * @param batchID the ID of the batch
     * @param reserved the number of reserved doses in this batch
     * @throws SQLException if the data could not be modified
     */
    public void updateReservedInBatch(int batchID, int reserved) throws SQLException {
        databaseBatch.updateReservedBatch(batchID, reserved);
    }

    /** Update the number of booked slots for a time period
     *
     * @param clinicID the ID of the clinic
     * @param periodID the ID of the time period
     * @param available the number of availabilities for this time period
     * @param booked the number of booked slots for this time period
     * @throws SQLException if the data could not be updated
     */
    public void updateBookedAvailableSlots(int clinicID, int periodID, int available, int booked) throws SQLException {
        databaseTimePeriods.updateTimePeriods(clinicID, periodID, available, booked);
    }

    /** Update the 'hasAppointment' property of a client to false
     *
     * @param healthCareID the HCN of the client
     * @throws SQLException if the data could not be updated
     */
    public void updateToNoAppointment(String healthCareID) throws SQLException {
        databaseClient.updateToNoAppointment(healthCareID);
    }

    /** Update the 'hasAppointment' property of a client to true
     *
     * @param healthCareID the HCN of the client
     * @throws SQLException if the data could not be updated
     */
    public void updateToHasAppointment(String healthCareID) throws SQLException {
        databaseClient.updateToHasAppointment(healthCareID);
    }

    public static class ModifierBuilder {
        private DatabaseClientInterface databaseClient;
        private DatabaseBatchInterface databaseBatch;
        private DatabaseTimePeriodsInterface databaseTimePeriods;
        private DatabaseAppointmentInterface databaseAppointment;

        /**
         * Constructor for a Retriever.
         */
        public ModifierBuilder() {}

        /**
         * assigns the client table class of this storer
         *
         * @param databaseClient the client table class.
         * @return the builder of the Storer.
         */
        public ModifierBuilder client(DatabaseClientInterface databaseClient){
            this.databaseClient = databaseClient;
            return this;
        }

        /**
         * assigns the batch table class of this storer
         *
         * @param databaseBatch the batch table class.
         * @return the builder of the storer.
         */
        public ModifierBuilder batch(DatabaseBatchInterface databaseBatch){
            this.databaseBatch = databaseBatch;
            return this;
        }

        /**
         * assigns the time period table class of this storer
         *
         * @param databaseTimePeriods the time period table class.
         * @return the builder of the storer.
         */
        public ModifierBuilder timePeriod(DatabaseTimePeriodsInterface databaseTimePeriods){
            this.databaseTimePeriods = databaseTimePeriods;
            return this;
        }

        /**
         * assigns the appointment table class of this storer
         *
         * @param databaseAppointment the appointment table class.
         * @return the builder of the storer.
         */
        public ModifierBuilder appointment(DatabaseAppointmentInterface databaseAppointment){
            this.databaseAppointment = databaseAppointment;
            return this;
        }

        public DatabaseModification build() {return new DatabaseModification(this);}
    }

}
