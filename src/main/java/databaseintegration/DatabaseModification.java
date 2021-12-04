package databaseintegration;

import constants.BookingConstants;
import managers.Storer;

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

    public DatabaseModification(StorerBuilder builder) throws SQLException {
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
        String query = getQuery("appointment", appointmentID, clinicID, clientID, periodID, brand);
        connection.prepareStatement(query);
        databaseAppointment.addAppointment(appointmentID, clinicID, clientID, periodID, batchID, brand);
    }


    public void writeToClient(String healthCardID, String name, boolean hasAppointment) throws SQLException {
        String query = getQuery("client", healthCardID, name, hasAppointment);
        connection.prepareStatement(query);
        databaseClient.addClient(healthCardID, name, hasAppointment);
    }

    public void writeToTimePeriods(int periodID, int clinicID, int availableSlots, int bookedSlots, LocalDateTime datetime)
            throws SQLException {
        String query = getQuery("timePeriods", periodID, clinicID, availableSlots, datetime);
        connection.prepareStatement(query);
        databaseTimePeriods.addTimePeriod(periodID, clinicID, availableSlots, bookedSlots, Timestamp.valueOf(datetime));
    }

    public void writeToVaccineBatch(int batchID, int clinicID, String brand,
                                    LocalDate expiryDate, int reserved, int quantity) throws SQLException {
        String query = getQuery("vaccineBatch", batchID, clinicID, brand, expiryDate, reserved, quantity);
        connection.prepareStatement(query);
        databaseBatch.addBatch(batchID, clinicID, brand, Date.valueOf(expiryDate), reserved, quantity);
    }



    private String getQuery(String table, Object ... o) {
        StringBuilder query = new StringBuilder("INSERT INTO " + table + " VALUES (");
        for(int i = 0;i<o.length;i++) {
            query.append(i);
            if(i < o.length - 1) {
                query.append(", ");
            }
        }
        query.append(")");
        return query.toString();
    }

    public static  class StorerBuilder {
        private DatabaseClientInterface databaseClient;
        private DatabaseBatchInterface databaseBatch;
        private DatabaseTimePeriodsInterface databaseTimePeriods;
        private DatabaseAppointmentInterface databaseAppointment;

        /**
         * Constructor for a Retriever.
         */
        public StorerBuilder() {}

        /**
         * assigns the client table class of this storer
         *
         * @param databaseClient the client table class.
         * @return the builder of the Storer.
         */
        public StorerBuilder client(DatabaseClientInterface databaseClient){
            this.databaseClient = databaseClient;
            return this;
        }

        /**
         * assigns the batch table class of this storer
         *
         * @param databaseBatch the batch table class.
         * @return the builder of the storer.
         */
        public StorerBuilder batch(DatabaseBatchInterface databaseBatch){
            this.databaseBatch = databaseBatch;
            return this;
        }

        /**
         * assigns the time period table class of this storer
         *
         * @param databaseTimePeriods the time period table class.
         * @return the builder of the storer.
         */
        public StorerBuilder timePeriod(DatabaseTimePeriodsInterface databaseTimePeriods){
            this.databaseTimePeriods = databaseTimePeriods;
            return this;
        }

        /**
         * assigns the appointment table class of this storer
         *
         * @param databaseAppointment the appointment table class.
         * @return the builder of the storer.
         */
        public StorerBuilder appointment(DatabaseAppointmentInterface databaseAppointment){
            this.databaseAppointment = databaseAppointment;
            return this;
        }

        public DatabaseModification build() throws SQLException {return new DatabaseModification(this);}
    }

}
