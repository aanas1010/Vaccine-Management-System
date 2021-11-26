package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This is the ServiceLocation interface which supports methods for
 * Reading and writing vaccine and appointment details
 */

public interface ServiceLocation {

    boolean supplyContainsBatchId(int id);

    void addBatch(VaccineBatch batch);

    void logPastVaccinations(String vaccinationId, User client, LocalDateTime dateTime, String vaccineBrand);

    void setShift(LocalDate date, int num);

    void addTimePeriod(TimePeriod timePeriod, LocalDate date);

    void removeTimePeriod(LocalDateTime dateTime);

    int getServiceLocationId();

    int getShiftForDate(LocalDate date);

    boolean shiftAvailable(LocalDate date);

    boolean containsShift(LocalDate date);

    boolean checkTimePeriod(LocalDateTime dateTime);

    TimePeriod getTimePeriod(LocalDateTime dateTime);


    List<TimePeriod> getTimePeriods(LocalDate date);

    VaccinationLog getVaccineLog();

    String getLocation();


    List<VaccineBatch> getSupply();

    VaccineSupply getSupplyObj();

}
