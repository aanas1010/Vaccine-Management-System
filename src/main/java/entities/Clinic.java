package entities;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the main Clinic entity which stores the clinic's vaccine supply
 * Clinics have a unique integer ClinicId which is used to identify the clinic
 */

public class Clinic implements ServiceLocation {
    private int clinicId;
    private VaccineSupply supply;
    protected VaccinationLog log;
    private ArrayList<TimePeriod> timePeriods;
    private HashMap<LocalDate, Integer> shifts;

    // Basic constructor
    public Clinic(int id) {
        this.clinicId = id;
        this.supply = new VaccineSupply();
        this.log = new VaccinationLog();
        this.timePeriods = new ArrayList<>();
        this.shifts = new HashMap<LocalDate, Integer>();
    }

    // Overloaded Constructor for testing
    public Clinic(int id, VaccineSupply supply, VaccinationLog vaccinationLog, ArrayList<TimePeriod> timePeriods, HashMap<LocalDate, Integer> shifts) {
        this.clinicId = id;
        this.supply = supply;
        this.log = vaccinationLog;
        this.timePeriods = timePeriods;
        this.shifts = shifts;
    }

    // Log a past vaccination (NON-APPOINTMENT)
    public void logPastVaccinations(String vaccinationId, Client client, LocalDate dateTime, String vaccineBrand) {
        log.addToLog(vaccinationId, client, dateTime, vaccineBrand);
    }

    // Set the number of shifts for a date
    public void setShift(LocalDate date, int num) {
        shifts.put(date, num);
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

    public int getShiftForDate(LocalDate date) {return shifts.get(date);}

    public ArrayList<TimePeriod> getTimePeriods() {return timePeriods;}

    public TimePeriod getTimePeriodByDate(LocalDate date) {
        for(TimePeriod timePeriod: timePeriods) {
            if(timePeriod.getDateTime().equals(date)) {
                return timePeriod;
            }
        }
        return null;
    }
}