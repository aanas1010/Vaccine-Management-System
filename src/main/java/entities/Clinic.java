package entities;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is the core Clinic entity which stores the clinic's vaccine supply
 * Clinics have a unique integer ClinicId which is used to identify the clinic
 */

public class Clinic implements ServiceLocation {
    private final int clinicId;
    private final VaccineSupply supply;
    private final VaccinationLog log;
    private final HashMap<LocalDate, List<TimePeriod>> timePeriods;
    private final HashMap<LocalDate, Integer> shifts;
    private final String location;

    /**
     * creates a walk in clinic object.
     *
     * @param builder the builder of the clinic
     */
    public Clinic(ClinicBuilder builder) {
        assert(builder.clinicId != -1);
        this.clinicId = builder.clinicId;
        this.supply = builder.supply;
        this.log = builder.log;
        this.timePeriods = builder.timePeriods;
        this.shifts = builder.shifts;
        assert(builder.location != null);
        this.location = builder.location;
    }

    /**
     * add the provided batch to the location's supply.
     *
     * @param batch the vaccine batch we are interested in adding.
     */
    public void addBatch(VaccineBatch batch) {this.getSupply().add(batch);}

    /**
     * does the vaccine batch with id x exists.
     *
     * @param id of the wanted batch
     * @return true if the wanted batch exists; false otherwise
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
     * logs an appointment that has already happened - using the appointment details.
     *
     * @param vaccinationId id of the past appointment.
     * @param client who received the vaccine.
     * @param dateTime when the vaccine was given.
     * @param vaccineBrand of the vaccine which was administered.
     */
    public void logPastVaccinations(String vaccinationId, User client, LocalDateTime dateTime, String vaccineBrand) {
        log.addToLog(vaccinationId, client, dateTime, vaccineBrand);
    }

    /**
     * Set the number of shifts for a date.
     *
     * @param date of the date we are interested.
     * @param num number of employs assigned to that shift.
     */
    @Override
    public void setShift(LocalDate date, int num) {shifts.put(date, num);}

    /**
     * Checks if a date has shifts.
     *
     * @param date of the date we are interested.
     * @return true if there is a shift at the given dateTime; false otherwise.
     */
    public boolean containsShift(LocalDate date){return this.shifts.containsKey(date);}

    /**
     * Checks if a date has more than 0 shifts on a day.
     *
     * @param date of the date we are interested.
     * @return true if there are more than 0 shifts; false otherwise.
     */
    public boolean shiftAvailable(LocalDate date){
        if (this.shifts.containsKey(date)){
            return this.shifts.get(date) > 0;
        }
        return false;
    }

    /**
     * Checks if a time period is already stored in the location.
     *
     * @param dateTime of the date we are interested.
     * @return true if the time period of the given dateTime exists; false otherwise.
     */
    @Override
    public boolean checkTimePeriod(LocalDateTime dateTime){
        if (this.timePeriods.containsKey(dateTime.toLocalDate())){
            List<TimePeriod> timePeriods = this.timePeriods.get(dateTime.toLocalDate());
            for (TimePeriod timePeriod: timePeriods){
                if (timePeriod.getDateTime().equals(dateTime)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * adds a time period the location's list of time periods.
     *
     * @param timePeriod the time period we are adding.
     * @param date the date to whose list we are adding the time period.
     */
    @Override
    public void addTimePeriod(TimePeriod timePeriod, LocalDate date){
        if (this.timePeriods.containsKey(date)){
            this.timePeriods.get(date).add(timePeriod);
        }
        else{
            List<TimePeriod> newTime = new ArrayList<>();
            newTime.add(timePeriod);
            this.timePeriods.put(date, newTime);
        }
    }

    /**
     * Removing a time period from a location.
     *
     * @param dateTime of the date we are interested.
     */
    @Override
    public void removeTimePeriod(LocalDateTime dateTime){
        this.timePeriods.get(dateTime.toLocalDate()).removeIf(timePeriod ->
                timePeriod.getDateTime().equals(dateTime));
    }

    //getters

    /**
     * getter.
     *
     * @param dateTime of the date we are interested.
     * @return the time period of the given time.
     */
    public TimePeriod getTimePeriodByTime(LocalDateTime dateTime){
        for (TimePeriod timePeriod: getTimePeriods(dateTime.toLocalDate())){
            if (timePeriod.getDateTime().equals(dateTime)){
                return timePeriod;
            }
        }
        return null;
    }

    /**
     * getter.
     *
     * @param timePeriodID the ID of the time period
     * @return the time period of the given ID.
     */
    public TimePeriod getTimePeriodByID(int timePeriodID){
        for(LocalDate date : timePeriods.keySet()) {
            for (TimePeriod timePeriod: getTimePeriods(date)){
                if (timePeriod.getID() == timePeriodID){
                    return timePeriod;
                }
            }
        }
        return null;
    }

    /**
     * getter.
     *
     * @return id of the location.
     */
    public int getServiceLocationId() {return this.clinicId;}

    /**
     * getter.
     *
     * @return list of vaccine batches of the location.
     */
    public List<VaccineBatch> getSupply(){return this.supply.getBatchList();}

    /**
     * getter.
     *
     * @return vaccine supply of the location.
     */
    public VaccineSupply getSupplyObj() {return this.supply;}

    /**
     * getter.
     *
     * @param date of the date we are interested.
     * @return the number of shift.
     */
    @Override
    public int getShiftForDate(LocalDate date) {return shifts.get(date);}

    /**
     * getter.
     *
     * @param date date from which extract the list of time periods.
     * @return list of time periods of the location of the given date.
     */
    public List<TimePeriod> getTimePeriods(LocalDate date) {return this.timePeriods.get(date);}

    /**
     * getter.
     *
     * @return vaccine log of the location.
     */
    public VaccinationLog getVaccineLog() {return log;}

    /**
     * getter.
     *
     * @return the location of the location.
     */
    public String getLocation() {return location;}

    /**
     * The builder class for a clinic.
     */
    public static  class ClinicBuilder {
        private int clinicId;
        private VaccineSupply supply;
        private VaccinationLog log;
        private HashMap<LocalDate, List<TimePeriod>> timePeriods;
        private HashMap<LocalDate, Integer> shifts;
        private String location;

        /**
         * Constructor for a clinic.
         */
        public ClinicBuilder() {
            this.clinicId = -1;
            this.supply = new VaccineSupply();
            this.log = new VaccinationLog();
            this.timePeriods = new HashMap<>();
            this.shifts = new HashMap<>();
        }

        /**
         * assigns a clinic id to the clinic builder.
         *
         * @param iD the iD of the clinic.
         * @return the builder of the clinic.
         */
        public ClinicBuilder clinicId (int iD){
            this.clinicId = iD;
            return this;
        }

        /**
         * assigns a supply to the clinic builder.
         *
         * @param supply the supply of the clinic.
         * @return the builder of the clinic.
         */
        public ClinicBuilder supply (VaccineSupply supply){
            this.supply = supply;
            return this;
        }

        /**
         * assigns a vaccination log to the clinic builder.
         *
         * @param log the vaccination log of the clinic.
         * @return the builder of the clinic.
         */
        public ClinicBuilder log (VaccinationLog log){
            this.log = log;
            return this;
        }

        /**
         * assigns time periods to the clinic builder.
         *
         * @param timePeriods the time periods of the clinic.
         * @return the builder of the clinic.
         */
        public ClinicBuilder timePeriods (HashMap<LocalDate, List<TimePeriod>> timePeriods){
            this.timePeriods = timePeriods;
            return this;
        }

        /**
         * assigns shifts to the clinic builder.
         *
         * @param shifts of the clinic.
         * @return the builder of the clinic.
         */
        public ClinicBuilder shifts (HashMap<LocalDate, Integer> shifts){
            this.shifts = shifts;
            return this;
        }

        /**
         * assigns a supply to the clinic builder.
         *
         * @param location the location of the clinic.
         * @return the builder of the clinic.
         */
        public ClinicBuilder location (String location){
            this.location = location;
            return this;
        }

        /**
         * Building the clinic.
         *
         * @return returns the clinic.
         */
        public Clinic build(){return new Clinic(this);}
    }



}