package databaseintegration;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.sql.SQLException;

/**
 * This is the adapter for retrieving data from the database.
 * The methods here consist of getter methods that return a query from the database.
 */

public class DatabaseRetrieval implements DataRetrieval {
    private final DatabaseClientInterface databaseClient;
    private final DatabaseClinicInterface databaseClinic;
    private final DatabaseBatchInterface databaseBatch;
    private final DatabaseTimePeriodsInterface databaseTimePeriods;
    private final DatabaseAppointmentInterface databaseAppointment;

    public DatabaseRetrieval(RetrieverBuilder builder) {
        assert(builder.databaseClient != null);
        this.databaseClient = builder.databaseClient;
        assert(builder.databaseClinic != null);
        this.databaseClinic = builder.databaseClinic;
        assert(builder.databaseBatch != null);
        this.databaseBatch = builder.databaseBatch;
        assert(builder.databaseTimePeriods != null);
        this.databaseTimePeriods = builder.databaseTimePeriods;
        assert(builder.databaseAppointment != null);
        this.databaseAppointment = builder.databaseAppointment;

    }

    /** Get the IDs of the clinics
     *
     * @return a JsonArray of the clinics
     * @throws SQLException if the data could not be retrieved
     */
    public JsonArray getClinicIDs() throws SQLException {
        return databaseClinic.loadAllClinics();
    }

    /** Get the IDs of the bookable clinics
     *
     * @return a JsonArray of the bookable clinics
     * @throws SQLException if the data could not be retrieved
     */
    public JsonArray getBookableClinicIDs() throws SQLException {
        JsonArray clinics = databaseClinic.loadAllClinics();

        JsonArrayBuilder clinicIDs = Json.createArrayBuilder();
        for (int i = 0; i < clinics.size(); i++) {
            JsonObject thisClinic = clinics.getJsonObject(i);
            if (thisClinic.getBoolean("isBookable")) {
                clinicIDs.add(thisClinic.getInt("clinicID"));
            }
        }

        return clinicIDs.build();

    }

    /** Get all clients
     *
     * @return a JsonArray of the clients
     * @throws SQLException if the data could not be retrieved
     */
    public JsonArray getClients() throws SQLException {
        return databaseClient.loadAllClients();
    }

    /** Get the info for a clinic
     *
     * @param clinicID the clinic whose ID we want
     * @return a JsonArray of clinics
     * @throws SQLException if the data could not be retrieved
     */
    public JsonArray getClinicInfo(int clinicID) throws SQLException {
        return databaseClinic.loadAllClinics();
    }

    /** Get the batches for a clinic
     *
     * @param clinicID the ID of the clinic whose batches we want
     * @return a JsonArray of the batches
     * @throws SQLException if the data could not be retrieved
     */
    public JsonArray getVaccineBatches(int clinicID) throws SQLException {
        return databaseBatch.loadBatches(clinicID);
    }

    /** Get the time periods for a clinic
     *
     * @param clinicID the ID of the clinic whose batches we want
     * @return a JsonArray of the time periods
     * @throws SQLException if the data could not be retrieved
     */
    public JsonArray getTimePeriods(int clinicID) throws SQLException {
        return databaseTimePeriods.loadTimePeriods(clinicID);
    }

    /** Get the appointments for a bookable clinic
     *
     * @param clinicID the ID of a bookable clinic whose appoinments we want
     * @return a JsonArray of the appointments
     * @throws SQLException if the data could not be retrieved
     */
    public JsonArray getAppointments(int clinicID) throws SQLException {
        return databaseAppointment.loadAppointments(clinicID);
    }

    public static class RetrieverBuilder {
        private DatabaseClientInterface databaseClient;
        private DatabaseClinicInterface databaseClinic;
        private DatabaseBatchInterface databaseBatch;
        private DatabaseTimePeriodsInterface databaseTimePeriods;
        private DatabaseAppointmentInterface databaseAppointment;

        /**
         * Constructor for a Retriever.
         */
        public RetrieverBuilder() {}

        /**
         * assigns the client table class of this retriever
         *
         * @param databaseClient the client table class.
         * @return the builder of the Retriever.
         */
        public RetrieverBuilder client(DatabaseClientInterface databaseClient){
            this.databaseClient = databaseClient;
            return this;
        }

        /**
         * assigns the clinic table class of this retriever
         *
         * @param databaseClinic the clinic table class.
         * @return the builder of the Retriever.
         */
        public RetrieverBuilder clinic(DatabaseClinicInterface databaseClinic){
            this.databaseClinic = databaseClinic;
            return this;
        }

        /**
         * assigns the batch table class of this retriever
         *
         * @param databaseBatch the batch table class.
         * @return the builder of the Retriever.
         */
        public RetrieverBuilder batch(DatabaseBatchInterface databaseBatch){
            this.databaseBatch = databaseBatch;
            return this;
        }

        /**
         * assigns the time period table class of this retriever
         *
         * @param databaseTimePeriods the time period table class.
         * @return the builder of the Retriever.
         */
        public RetrieverBuilder timePeriod(DatabaseTimePeriodsInterface databaseTimePeriods){
            this.databaseTimePeriods = databaseTimePeriods;
            return this;
        }

        /**
         * assigns the appointment table class of this retriever
         *
         * @param databaseAppointment the appointment table class.
         * @return the builder of the Retriever.
         */
        public RetrieverBuilder appointment(DatabaseAppointmentInterface databaseAppointment){
            this.databaseAppointment = databaseAppointment;
            return this;
        }

        public DatabaseRetrieval build(){return new DatabaseRetrieval(this);}
    }

}
