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
     * @param id [description]
     * @return [description]
     */
    boolean supplyContainsBatchId(int id);

    /**
     * @param batch [description]
     */
    void addBatch(VaccineBatch batch);

    /**
     * @param vaccinationId [description]
     * @param client [description]
     * @param dateTime [description]
     * @param vaccineBrand [description]
     */
    void logPastVaccinations(String vaccinationId, User client, LocalDateTime dateTime, String vaccineBrand);

    /**
     * @param date [description]
     * @param num [description]
     */
    void setShift(LocalDate date, int num);

    /**
     * @param timePeriod [description]
     * @param date [description]
     */
    void addTimePeriod(TimePeriod timePeriod, LocalDate date);

    /**
     * @param dateTime [description]
     */
    void removeTimePeriod(LocalDateTime dateTime);

    /**
     * @return [description]
     */
    int getServiceLocationId();

    /**
     * @param date [description]
     * @return [description]
     */
    int getShiftForDate(LocalDate date);

    /**
     * @param date [description]
     * @return [description]
     */
    boolean shiftAvailable(LocalDate date);

    /**
     * @param date [description]
     * @return [description]
     */
    boolean containsShift(LocalDate date);

    /**
     * @param dateTime [description]
     * @return [description]
     */
    boolean checkTimePeriod(LocalDateTime dateTime);

    /**
     * @param dateTime [description]
     * @return [description]
     */
    TimePeriod getTimePeriod(LocalDateTime dateTime);


    /**
     * @param date [description]
     * @return [description]
     */
    List<TimePeriod> getTimePeriods(LocalDate date);

    /**
     * @return [description]
     */
    VaccinationLog getVaccineLog();

    /**
     * @return [description]
     */
    String getLocation();


    /**
     * @return [description]
     */
    List<VaccineBatch> getSupply();

    /**
     * @return [description]
     */
    VaccineSupply getSupplyObj();

}
