package clinicmanagement;

import constants.ManagementSystemException;
import entities.ServiceLocation;
import entities.TimePeriod;
import managers.Modifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the Use Case for setting time periods and shifts.
 * Every time the use case is needed, a new SetTimePeriod instance is created
 * with the only parameter being clinic
 */

public class SetTimePeriod {
    private final ServiceLocation clinic;
    private final Modifier modifier;

    /**
     * This is the Use Case for setting time periods and shifts.
     *
     * @param clinic The clinic for which the time period is being set for
     */
    public SetTimePeriod(ServiceLocation clinic){
        this.clinic = clinic;
        this.modifier = null;
    }

    public SetTimePeriod(ServiceLocation clinic, Modifier modifier){
        this.clinic = clinic;
        this.modifier = modifier;
    }

    /**
     * Setting the number of shifts for a certain date
     *
     * @param date The date for when the employees are being set
     * @param employees The number of employees being set for the given date
     * @return a string that indicates the number of employees assigned for the chosen date
     */
    public String setEmployees(LocalDate date, int employees) {
        this.clinic.setShift(date, employees);
        return "Set " + employees + " employees for " + date;
    }

    /**
     * Adding a time period to a clinic if it is not already there
     *
     * @param dateTime The date and time for which the time period is being added for
     * @return a string of the time period added
     * @throws ManagementSystemException if no employees have been set for the given date or a time period
     * already exists for the given time
     */
    public String addTimePeriod(LocalDateTime dateTime) throws ManagementSystemException {
        if (this.clinic.shiftAvailable(dateTime.toLocalDate())
                && !this.clinic.checkTimePeriod(dateTime)){
            int slots = this.clinic.getShiftForDate(dateTime.toLocalDate());
            TimePeriod addedTimePeriod = new TimePeriod(dateTime, slots);
            this.clinic.addTimePeriod(addedTimePeriod, dateTime.toLocalDate());

            if (this.modifier != null) {
                this.modifier.StoreTimePeriod(addedTimePeriod, clinic.getServiceLocationId());
            }

            return addedTimePeriod.toString();
        } else if (!this.clinic.shiftAvailable(dateTime.toLocalDate())) {
            throw new ManagementSystemException(ManagementSystemException.NO_SHIFT_AVAILABLE);
        } else {
            throw new ManagementSystemException(ManagementSystemException.TIME_PERIOD_ALREADY_EXISTS);
        }
    }

    /**
     * Removing a time period from a clinic if there exists a time period at the specified date and time.
     *
     * @param dateTime The date and time for which the time period is being removed for
     * @return a string that indicates the date and time of the time period that has been removed
     * @throws ManagementSystemException if there is no time period that exists for the chosen date and time
     */
    public String removeTimePeriod(LocalDateTime dateTime) throws ManagementSystemException {
        //TODO: removing timeperiods from the database
        if(this.clinic.checkTimePeriod(dateTime)){
            this.clinic.removeTimePeriod(dateTime);
            return "Time: " + dateTime;
        }
        else{
            throw new ManagementSystemException(ManagementSystemException.TIME_PERIOD_DOES_NOT_EXIST);
        }
    }

    /**
     * Adding multiple time periods from a starting time until the end based on interval inputted in
     * the form of minutes
     *
     * @param start starting date and time of the first time period
     * @param end ending date and time of the last time period
     * @param interval the length (in minutes) of each timePeriod
     * @return the number of timePeriods that have been added
     * @throws ManagementSystemException if the start date and end date are NOT the same, or the interval is less
     * than 1 minute
     */
    public int addMultipleTimePeriods(LocalDateTime start, LocalDateTime end, int interval) throws ManagementSystemException {
        if(!start.toLocalDate().equals(end.toLocalDate()) || interval <= 0){
            throw new ManagementSystemException(ManagementSystemException.INVALID_RANGE_OR_INTERVAL);
        }

        List<LocalDateTime> times = new ArrayList<>();
        while (start.isBefore(end)){
            times.add(start);
            start = start.plusMinutes(interval);
        }
        int counter = 0;
        for(LocalDateTime time: times){
            if(!this.clinic.checkTimePeriod(time)){
                TimePeriod createdTimePeriod = new TimePeriod(time, this.clinic.getShiftForDate(time.toLocalDate()));
                this.clinic.addTimePeriod(createdTimePeriod, time.toLocalDate());
                counter += 1;

                if(this.modifier != null) {
                    this.modifier.StoreTimePeriod(createdTimePeriod, clinic.getServiceLocationId());
                }
            }
        }
        return counter;
    }

}
