package entities;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This is the main Clinic entity which stores the clinic's vaccine supply
 * Clinics have a unique integer ClinicId which is used to identify the clinic
 */

public class Clinic implements ServiceLocation {
    private int clinicId;
    private VaccineSupply supply;
    protected VaccinationLog log;

    // Basic constructor
    public Clinic(int id) {
        this.clinicId = id;
        this.supply = new VaccineSupply();
        this.log = new VaccinationLog();
    }

    // Overloaded Constructor for testing
    public Clinic(int id, VaccineSupply supply) {
        this.clinicId = id;
        this.supply = supply;
    }

    // Log a past vaccination (NON-APPOINTMENT)
    public void logPastVaccinations(String vaccinationId, Client client, LocalDate dateTime, String vaccineBrand) {
        log.addToLog(vaccinationId, client, dateTime, vaccineBrand);
    }

    // Getters
    public int getServiceLocationId() {
        return this.clinicId;
    }

    public ArrayList<VaccineBatch> getSupply(){
        return this.supply.getBatchList();}

    public VaccineSupply getSupplyObj() {
        return this.supply;
    }

}