package entities;

import constants.BookingConstants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the VaccinationLog entity that keeps track of all previous vaccinations
 * It contains only a list of VaccinationRecord records, which keep track of the data itself
 * Note that items in the list may be from either drop-in or appointment-based vaccinations
 */
public class VaccinationLog {
    private final List<VaccinationRecord> log;

    /**
     * constructs a vaccination log object.
     */
    public VaccinationLog() {
        this.log = new ArrayList<>();
    }

    /**
     * add vaccination-event to log.
     *
     * @param a vaccination-event that is being logged.
     */
    public void addToLog(Appointment a){
        int appointmentId = a.getAppointmentId();
        User client = a.getClient();
        LocalDateTime dateTime = a.getTimePeriod().getDateTime();
        String vaccineBrand = a.getVaccineBrand();

        VaccinationRecord dataObj =
                new VaccinationRecord(BookingConstants.APPOINTMENT_BASED_PREFIX + appointmentId, client, dateTime, vaccineBrand);

        log.add(dataObj);
    }

    /**
     * add vaccination-event to log, using its details.
     *
     * @param vaccinationId is of the logged vaccination-event.
     * @param client client attending the vaccination-event.
     * @param dateTime date when the vaccination-event happened.
     * @param vaccineBrand brand used in the vaccination-event.
     */
    public void addToLog(String vaccinationId, User client, LocalDateTime dateTime, String vaccineBrand){
        VaccinationRecord dataObj =
                new VaccinationRecord(BookingConstants.NON_APPOINTMENT_BASED_PREFIX + vaccinationId, client, dateTime, vaccineBrand);

        log.add(dataObj);
    }

    // Getters

    /**
     * getter.
     *
     * @param id of the vaccination-event.
     * @return vaccination record by ID if it exists; null otherwise.
     */
    public VaccinationRecord getVaccinationRecord(String id) {
        for(VaccinationRecord record: log) {
            if(id.equals(record.getVaccinationId())) {
                return record;
            }
        }
        return null;
    }

    /**
     * getter.
     *
     * @param id of the vaccination-event.
     * @return the client who attended the vaccination-event.
     */
    public User getClientByVaccinationId(String id) {
        try{
            return getVaccinationRecord(id).getClient();
        }catch(Exception ex) {
            return null;
        }
    }

    /**
     * getter.
     *
     * @param id of the vaccination-event.
     * @return the dateTime of the vaccination-event.
     */
    public LocalDateTime getDateTimeByVaccinationId(String id) {
        try{
            return getVaccinationRecord(id).getDateTime();
        }catch(Exception ex) {
            return null;
        }
    }

    /**
     * getter.
     *
     * @param id of the vaccination-event.
     * @return brand used in the vaccination-event.
     */
    public String getVaccineBrandByVaccinationId(String id) {
        try{
            return getVaccinationRecord(id).getVaccineBrand();
        }catch(Exception ex) {
            return null;
        }
    }

    /**
     * getter.
     *
     * @param id of the vaccination-event.
     * @return the record toString of an vaccination-event.
     */
    public String getRecordString (String id) {
        try{return getVaccinationRecord(id).toString();}
        catch(Exception ex) {return null;}
    }

    /**
     * indicates if the record already has this id.
     *
     * @param id of the vaccination-event.
     * @return true if the id exists; false otherwise.
     */
    public boolean containsId(String id) {
        for(VaccinationRecord record : log) {
            if(record.getVaccinationId().equals(id)) {
                return true;
            }
        }
        return false;
    }


    /**
     * VaccinationRecord inner class for storing info for a specific vaccination.
     */
    private static class VaccinationRecord {
        //vaccinationId is of the form "A123" for appointment-based vaccinations, or "V123" otherwise
        private final String vaccinationId;
        private final User client;
        private final LocalDateTime dateTime;
        private final String vaccineBrand;

        /**
         * construct a vaccination-event record object.
         *
         * @param vaccinationId id of the recorded vaccination-event.
         * @param client client attending the vaccination-event.
         * @param dateTime date when the vaccination-event happened.
         * @param vaccineBrand brand used in the vaccination-event.
         */
        public VaccinationRecord(String vaccinationId, User client, LocalDateTime dateTime, String vaccineBrand) {
            this.vaccinationId = vaccinationId;
            this.client = client;
            this.dateTime = dateTime;
            this.vaccineBrand = vaccineBrand;
        }

        //Getters.

        /**
         * getter.
         *
         * @return the id of the recorded vaccination-event, as a string.
         */
        public String getVaccinationId() {
            return vaccinationId;
        }

        /**
         * getter.
         *
         * @return client attending the recorded vaccination-event
         */
        public User getClient() {
            return client;
        }

        /**
         * getter.
         *
         * @return brand used in the recorded vaccination-event.
         */
        public String getVaccineBrand() {
            return vaccineBrand;
        }

        /**
         * getter.
         *
         * @return date when the recorded vaccination-event happened.
         */
        public LocalDateTime getDateTime() {
            return dateTime;
        }

        /**
         * Return a string of the information of this record.
         *
         * @return a string representation of the object.
         */
        @Override
        public String toString(){
            return "----------------VACCINATION #" + vaccinationId + "----------------" +
                    "\nCLIENT: " + client.getName() +
                    "\nTIME: " + dateTime.toString() +
                    "\nBRAND: " + vaccineBrand;
        }
    }
}
