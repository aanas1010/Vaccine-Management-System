package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the core Clinic entity which stores the clinic's vaccine supply
 * Clinics have a unique integer ClinicId which is used to identify the clinic
 */

public class Clinic implements ServiceLocation {
    private final int clinicId;
    private final VaccineSupply supply;
    private final VaccinationLog log;
    private final HashMap<LocalDate, ArrayList<TimePeriod>> timePeriods;
    private final HashMap<LocalDate, Integer> shifts;

    // Basic constructor
    public Clinic(int id) {
        this.clinicId = id;
        this.supply = new VaccineSupply();
        this.log = new VaccinationLog();
        this.timePeriods = new HashMap<LocalDate, ArrayList<TimePeriod>>();
        this.shifts = new HashMap<LocalDate, Integer>();
    }

    // Overloaded Constructors for testing
    public Clinic(int id, VaccineSupply supply, VaccinationLog vaccinationLog, HashMap<LocalDate, ArrayList<TimePeriod>> timePeriods, HashMap<LocalDate, Integer> shifts) {
        this.clinicId = id;
        this.supply = supply;
        this.log = vaccinationLog;
        this.timePeriods = timePeriods;
        this.shifts = shifts;
    }
    // Overloaded constructor
    public Clinic(int id, VaccineSupply supply) {
        this.clinicId = id;
        this.supply = supply;
        this.log = new VaccinationLog();
        this.timePeriods = new HashMap<LocalDate, ArrayList<TimePeriod>>();
        this.shifts = new HashMap<LocalDate, Integer>();
    }


    // Set the number of shifts for a date
    @Override
    public void setShift(LocalDate date, int num) {
        shifts.put(date, num);
    }


    // Checking if a date has a number of shifts
    public boolean containsShift(LocalDate date){return this.shifts.containsKey(date);}

    // Checking if a date has more than 0 shifts on a day
    public boolean shiftAvailable(LocalDate date){
        if (this.shifts.containsKey(date)){
            return this.shifts.get(date) > 0;
        }
        return false;
    }

    // Checking if a time period is already stored in a clinic
    @Override
    public boolean checkTimePeriod(LocalDateTime dateTime){
        if (this.timePeriods.containsKey(dateTime.toLocalDate())){
            ArrayList<TimePeriod> timePeriods = this.timePeriods.get(dateTime.toLocalDate());
            for (TimePeriod timePeriod: timePeriods){
                if (timePeriod.getDateTime() == dateTime){
                    return true;
                }
            }
        }
        return false;
    }

    // Adding a time period to a certain date
    @Override
    public void addTimePeriod(TimePeriod timePeriod, LocalDate date){
        if (this.timePeriods.containsKey(date)){
            this.timePeriods.get(date).add(timePeriod);
        }
        else{
            ArrayList<TimePeriod> newTime = new ArrayList<TimePeriod>();
            newTime.add(timePeriod);
            this.timePeriods.put(date, newTime);
        }
    }

    // Removing a time period from a clinic
    @Override
    public void removeTimePeriod(LocalDateTime dateTime){
        this.timePeriods.get(dateTime.toLocalDate()).removeIf(timePeriod ->
                timePeriod.getDateTime() == dateTime);
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

    @Override
    public int getShiftForDate(LocalDate date) {return shifts.get(date);}

    public ArrayList<TimePeriod> getTimePeriods(LocalDate date) {
        return this.timePeriods.get(date);
    }

    public VaccinationLog getVaccineLog() {return log;}


    //option if we choose not to use casting for clinics:

    //decorator methods

    //BookableClinic

    @Override
    public boolean addAppointment(Appointment ap) {return false;}

    @Override
    public Appointment getAppointmentRecord(int id) {return null;}

    @Override
    public boolean removeAppointment(Appointment ap) {return false;}

    @Override
    public boolean removeAppointmentById(int id) {return false;}

    @Override
    public void logPastVaccinations(Appointment appointmentRecord) {}

    //WalkInClinic

    @Override
    public void logPastVaccinations(String vaccinationId, Client client, LocalDateTime dateTime, String vaccineBrand) {}
}