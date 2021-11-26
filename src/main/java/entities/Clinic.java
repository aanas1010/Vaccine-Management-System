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
    private final String location;

    /**
     * @param id // Getters
     * @param location // Getters
     */
    // Basic constructor
    public Clinic(int id, String location) {
        this.clinicId = id;
        this.supply = new VaccineSupply();
        this.log = new VaccinationLog();
        this.timePeriods = new HashMap<>();
        this.shifts = new HashMap<>();
        this.location = location;
    }

    /**
     * @param id // Getters
     * @param supply // Getters
     * @param vaccinationLog // Getters
     * @param timePeriods // Getters
     * @param shifts // Getters
     * @param location // Getters
     */
    // Overloaded Constructors for testing
    public Clinic(int id, VaccineSupply supply, VaccinationLog vaccinationLog, HashMap<LocalDate,
            ArrayList<TimePeriod>> timePeriods, HashMap<LocalDate, Integer> shifts, String location) {
        this.clinicId = id;
        this.supply = supply;
        this.log = vaccinationLog;
        this.timePeriods = timePeriods;
        this.shifts = shifts;
        this.location = location;
    }

    /**
     * @param id // Getters
     * @param supply // Getters
     * @param location // Getters
     */
    // Overloaded constructor
    public Clinic(int id, VaccineSupply supply, String location) {
        this.clinicId = id;
        this.supply = supply;
        this.log = new VaccinationLog();
        this.timePeriods = new HashMap<>();
        this.shifts = new HashMap<>();
        this.location = location;
    }

    /**
     * @param batch [description]
     */
    // Add a batch to the VaccineSupply
    public void addBatch(VaccineBatch batch) {
        this.getSupply().add(batch);
    }

    /**
     * @param id [description]
     * @return [description]
     */
    public boolean supplyContainsBatchId(int id) {
        for(VaccineBatch batch : this.getSupply()) {
            if(batch.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param vaccinationId [description]
     * @param client        [description]
     * @param dateTime      [description]
     * @param vaccineBrand  [description]
     */
    // Log a past vaccination (NON-APPOINTMENT)
    public void logPastVaccinations(String vaccinationId, User client, LocalDateTime dateTime, String vaccineBrand) {
        log.addToLog(vaccinationId, client, dateTime, vaccineBrand);
    }


    /**
     * @param date [description]
     * @param num  [description]
     */
    // Set the number of shifts for a date
    @Override
    public void setShift(LocalDate date, int num) {
        shifts.put(date, num);
    }


    /**
     * @param date [description]
     * @return [description]
     */
    // Checking if a date has a number of shifts
    public boolean containsShift(LocalDate date){return this.shifts.containsKey(date);}

    /**
     * @param date [description]
     * @return [description]
     */
    // Checking if a date has more than 0 shifts on a day
    public boolean shiftAvailable(LocalDate date){
        if (this.shifts.containsKey(date)){
            return this.shifts.get(date) > 0;
        }
        return false;
    }

    /**
     * @param dateTime [description]
     * @return [description]
     */
    // Checking if a time period is already stored in a clinic
    @Override
    public boolean checkTimePeriod(LocalDateTime dateTime){
        if (this.timePeriods.containsKey(dateTime.toLocalDate())){
            ArrayList<TimePeriod> timePeriods = this.timePeriods.get(dateTime.toLocalDate());
            for (TimePeriod timePeriod: timePeriods){
                if (timePeriod.getDateTime().equals(dateTime)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param timePeriod [description]
     * @param date       [description]
     */
    // Adding a time period to a certain date
    @Override
    public void addTimePeriod(TimePeriod timePeriod, LocalDate date){
        if (this.timePeriods.containsKey(date)){
            this.timePeriods.get(date).add(timePeriod);
        }
        else{
            ArrayList<TimePeriod> newTime = new ArrayList<>();
            newTime.add(timePeriod);
            this.timePeriods.put(date, newTime);
        }
    }

    /**
     * @param dateTime [description]
     */
    // Removing a time period from a clinic
    @Override
    public void removeTimePeriod(LocalDateTime dateTime){
        this.timePeriods.get(dateTime.toLocalDate()).removeIf(timePeriod ->
                timePeriod.getDateTime().equals(dateTime));
    }

    /**
     * @param dateTime [description]
     * @return [description]
     */
    // Getters
    public TimePeriod getTimePeriod(LocalDateTime dateTime){
        for (TimePeriod timePeriod: getTimePeriods(dateTime.toLocalDate())){
            if (timePeriod.getDateTime().equals(dateTime)){
                return timePeriod;
            }
        }
        return null;
    }


    /**
     * @return [description]
     */
    public int getServiceLocationId() {
        return this.clinicId;
    }

    public ArrayList<VaccineBatch> getSupply(){
        return this.supply.getBatchList();}

    /**
     * @return [description]
     */
    public VaccineSupply getSupplyObj() {
        return this.supply;
    }

    /**
     * @param date [description]
     * @return [description]
     */
    @Override
    public int getShiftForDate(LocalDate date) {return shifts.get(date);}

    /**
     * @param date [description]
     * @return [description]
     */
    public ArrayList<TimePeriod> getTimePeriods(LocalDate date) {
        return this.timePeriods.get(date);
    }

    /**
     * @return [description]
     */
    public VaccinationLog getVaccineLog() {return log;}

    /**
     * @return [description]
     */
    public String getLocation() {return location;}

}