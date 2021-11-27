package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This is the ServiceLocation interface which supports methods for
 * Reading and writing vaccine and appointment details
 */

public interface ServiceLocation {

    /**
     * does the vaccine batch with id x exists.
     *
     * @param id of the wanted batch
     * @return true if the wanted batch exists; false otherwise
     */
    boolean supplyContainsBatchId(int id);

    /**
     * add the provided batch to the location's supply.
     *
     * @param batch the vaccine batch we are interested in adding.
     */
    void addBatch(VaccineBatch batch);

    /**
     * logs an appointment that has already happened.
     *
     * @param vaccinationId id of the past appointment.
     * @param client who received the vaccine.
     * @param dateTime when the vaccine was given.
     * @param vaccineBrand of the vaccine which was administered.
     */
    void logPastVaccinations(String vaccinationId, User client, LocalDateTime dateTime, String vaccineBrand);

    /**
     * Set the number of shifts for a date.
     *
     * @param date of the date we are interested.
     * @param num number of employs assigned to that shift.
     */
    void setShift(LocalDate date, int num);

    /**
     * adds a time period the location's list of time periods.
     *
     * @param timePeriod the time period we are adding.
     * @param date the date to whose list we are adding the time period.
     */
    void addTimePeriod(TimePeriod timePeriod, LocalDate date);

    /**
     * Removing a time period from a location.
     *
     * @param dateTime of the date we are interested.
     */
    void removeTimePeriod(LocalDateTime dateTime);

    /**
     * Checks if a date has more than 0 shifts on a day.
     *
     * @param date of the date we are interested.
     * @return true if there are more than 0 shifts; false otherwise.
     */
    boolean shiftAvailable(LocalDate date);

    /**
     * Checks if a date has shifts.
     *
     * @param date of the date we are interested.
     * @return true if there is a shift at the given dateTime; false otherwise.
     */
    boolean containsShift(LocalDate date);

    /**
     * Checks if a time period is already stored in the location.
     *
     * @param dateTime of the date we are interested.
     * @return true if the time period of the given dateTime exists; false otherwise.
     */
    boolean checkTimePeriod(LocalDateTime dateTime);

    /**
     * getter.
     *
     * @return id of the location.
     */
    int getServiceLocationId();

    /**
     * getter.
     *
     * @param date of the date we are interested.
     * @return the number of shift.
     */
    int getShiftForDate(LocalDate date);

    /**
     * getter.
     *
     * @param dateTime of the date we are interested.
     * @return the time period of the given time.
     */
    TimePeriod getTimePeriod(LocalDateTime dateTime);


    /**
     * getter.
     *
     * @param date date from which extract the list of time periods.
     * @return list of time periods of the location of the given date.
     */
    List<TimePeriod> getTimePeriods(LocalDate date);

    /**
     * getter.
     *
     * @return vaccine log of the location.
     */
    VaccinationLog getVaccineLog();

    /**
     * getter.
     *
     * @return the location of the location.
     */
    String getLocation();


    /**
     * getter.
     *
     * @return list of vaccine batches of the location.
     */
    List<VaccineBatch> getSupply();

    /**
     * getter.
     *
     * @return vaccine supply of the location.
     */
    VaccineSupply getSupplyObj();

}
