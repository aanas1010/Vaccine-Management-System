package databaseintegration;

import constants.BookingConstants;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This is the adapter for writing data into the database.
 * The methods here consist of writing methods that write to
 * specific tables in a database, whose URL is specified in BookingConstants
 */

public class DatabaseModification implements DataModification {
    private final Connection connection;
    private final DatabaseClientInterface databaseClient;
    private final DatabaseBatchInterface databaseBatch;
    private final DatabaseTimePeriodsInterface databaseTimePeriods;
    private final DatabaseAppointmentInterface databaseAppointment;

    public DatabaseModification(ModifierBuilder builder) throws SQLException {
        connection = DriverManager.getConnection(
                BookingConstants.DATABASE_CONNECTION_URL,
                BookingConstants.DATABASE_CONNECTION_USERNAME,
                BookingConstants.DATABASE_CONNECTION_PASSWORD);

        assert(builder.databaseClient != null);
        this.databaseClient = builder.databaseClient;
        assert(builder.databaseBatch != null);
        this.databaseBatch = builder.databaseBatch;
        assert(builder.databaseTimePeriods != null);
        this.databaseTimePeriods = builder.databaseTimePeriods;
        assert(builder.databaseAppointment != null);
        this.databaseAppointment = builder.databaseAppointment;
    }


    public void writeToAppointment(int appointmentID, int clinicID, String clientID, int periodID, int batchID,
                                   String brand) throws SQLException {
        databaseAppointment.addAppointment(appointmentID, clinicID, clientID, periodID, batchID, brand);
    }


    public void writeToClient(String healthCardID, String name, boolean hasAppointment) throws SQLException {
        databaseClient.addClient(healthCardID, name, hasAppointment);
    }

    public void writeToTimePeriods(int periodID, int clinicID, int availableSlots, int bookedSlots,
                                   LocalDateTime datetime) throws SQLException {
        databaseTimePeriods.addTimePeriod(periodID, clinicID, availableSlots, bookedSlots, Timestamp.valueOf(datetime));
    }

    public void writeToVaccineBatch(int batchID, int clinicID, String brand,
                                    LocalDate expiryDate, int reserved, int quantity) throws SQLException {
        databaseBatch.addBatch(batchID, clinicID, brand, Date.valueOf(expiryDate), reserved, quantity);
    }

    public void deleteFromAppointments(int clinicID, int appointmentID) throws SQLException {
        databaseAppointment.deleteAppointment(clinicID, appointmentID);
    }

    public void updateReservedInBatch(int clinicID, int batchID, int reserved) throws SQLException {
        databaseBatch.updateReservedBatch(clinicID, batchID, reserved);
    }

    public void updateBookedAvailableSlots(int clinicID, int periodID, int available, int booked) throws SQLException {
        databaseTimePeriods.updateTimePeriods(clinicID, periodID, available, booked);
    }

    public void updateToNoAppointment(String healthCareID) throws SQLException {
        databaseClient.updateToNoAppointment(healthCareID);
    }

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

        public DatabaseModification build() throws SQLException {return new DatabaseModification(this);}
    }

}
