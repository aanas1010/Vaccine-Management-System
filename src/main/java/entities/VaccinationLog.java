package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the VaccinationLog entity that keeps track of all previous vaccinations
 * It contains only a list of VaccinationRecord records, which keep track of the data itself
 * Note that items in the list may be from either drop-in or appointment-based vaccinations
 */

public class VaccinationLog {
    private final ArrayList<VaccinationRecord> log;

    public VaccinationLog() {
        this.log = new ArrayList<>();
    }

    // For appointments
    public void addToLog(Appointment a){
        int appointmentId = a.getAppointmentId();
        Client client = a.getClient();
        LocalDateTime dateTime = a.getTimePeriod().getDateTime();
        String vaccineBrand = a.getVaccineBrand();

        VaccinationRecord dataObj =
                new VaccinationRecord("A" + appointmentId, client, dateTime, vaccineBrand);

        log.add(dataObj);
    }

    // For non-appointments
    public void addToLog(String vaccinationId, Client client, LocalDateTime dateTime, String vaccineBrand){
        VaccinationRecord dataObj =
                new VaccinationRecord("V" + vaccinationId, client, dateTime, vaccineBrand);

        log.add(dataObj);
    }

    // Return the VaccinationRecord record by ID. Return null if it cannot be found
    public VaccinationRecord getVaccinationRecord(String id) {
        for(VaccinationRecord record: log) {
            if(id.equals(record.getVaccinationId())) {
                return record;
            }
        }
        return null;
    }

    // Getters
    public Client getClientByVaccinationId(String id) {return getVaccinationRecord(id).getClient();}

    public LocalDateTime getDateTimeByVaccinationId(String id) {return getVaccinationRecord(id).getDateTime();}

    public String getVaccineBrandByVaccinationId(String id) {return getVaccinationRecord(id).getVaccineBrand();}




    // VaccinationRecord inner class for storing info for a specific vaccination
    private class VaccinationRecord {
        //vaccinationId is of the form "A123" for appointment-based vaccinations, or "V123" otherwise
        private final String vaccinationId;
        private final Client client;
        private final LocalDateTime dateTime;
        private final String vaccineBrand;

        public VaccinationRecord(String vaccinationId, Client client, LocalDateTime dateTime, String vaccineBrand) {
            this.vaccinationId = vaccinationId;
            this.client = client;
            this.dateTime = dateTime;
            this.vaccineBrand = vaccineBrand;
        }

        public String getVaccinationId() {
            return vaccinationId;
        }

        public Client getClient() {
            return client;
        }

        public String getVaccineBrand() {
            return vaccineBrand;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        @Override
        public String toString() {
            return vaccinationId + ": " + vaccineBrand + ", administered " +
                    dateTime.toString() + " to " + client.getName();
        }
    }
}
