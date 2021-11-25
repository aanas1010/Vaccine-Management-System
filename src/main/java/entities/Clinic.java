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

    // Basic constructor
    public Clinic(ClinicBuilder builder) {
        this.clinicId = builder.clinicId;
        this.supply = builder.supply;
        this.log = builder.log;
        this.timePeriods = builder.timePeriods;
        this.shifts = builder.shifts;
        this.location = builder.location;
    }

    // Add a batch to the VaccineSupply
    public void addBatch(VaccineBatch batch) {
        this.getSupply().add(batch);
    }

    public boolean supplyContainsBatchId(int id) {
        for(VaccineBatch batch : this.getSupply()) {
            if(batch.getId() == id) {
                return true;
            }
        }
        return false;
    }

    // Log a past vaccination (NON-APPOINTMENT)
    public void logPastVaccinations(String vaccinationId, User client, LocalDateTime dateTime, String vaccineBrand) {
        log.addToLog(vaccinationId, client, dateTime, vaccineBrand);
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
                if (timePeriod.getDateTime().equals(dateTime)){
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
            ArrayList<TimePeriod> newTime = new ArrayList<>();
            newTime.add(timePeriod);
            this.timePeriods.put(date, newTime);
        }
    }

    // Removing a time period from a clinic
    @Override
    public void removeTimePeriod(LocalDateTime dateTime){
        this.timePeriods.get(dateTime.toLocalDate()).removeIf(timePeriod ->
                timePeriod.getDateTime().equals(dateTime));
    }

    // Getters
    public TimePeriod getTimePeriod(LocalDateTime dateTime){
        for (TimePeriod timePeriod: getTimePeriods(dateTime.toLocalDate())){
            if (timePeriod.getDateTime().equals(dateTime)){
                return timePeriod;
            }
        }
        return null;
    }


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

    public String getLocation() {return location;}

    public static  class ClinicBuilder {
        private final int clinicId;
        private VaccineSupply supply;
        private final VaccinationLog log;
        private final HashMap<LocalDate, ArrayList<TimePeriod>> timePeriods;
        private final HashMap<LocalDate, Integer> shifts;
        private final String location;

        // Basic constructor
        public ClinicBuilder(int id, String location) {
            this.clinicId = id;
            this.supply = new VaccineSupply();
            this.log = new VaccinationLog();
            this.timePeriods = new HashMap<>();
            this.shifts = new HashMap<>();
            this.location = location;
        }

        public ClinicBuilder supply (VaccineSupply supply){
            this.supply = supply;
            return this;
        }


        public Clinic build(){
            Clinic clinic = new Clinic(this);
            // validateBatch(clinic);
            return clinic;
        }
//        private void validateClinic(VaccineBatch1 batch){
//            assert(this.expiry.isAfter(LocalDate.now()));
//            assert(Objects.equals(this.brand, "Pfizer") || Objects.equals(this.brand, "Moderna"));
//        }
    }



}