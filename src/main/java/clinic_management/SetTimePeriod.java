package clinic_management;

import Constants.ExceptionConstants;
import entities.ServiceLocation;
import entities.TimePeriod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the Use Case for setting time periods and shifts.
 * Every time the use case is needed, a new SetTimePeriod instance is created
 * with the only parameter being clinic
 */

public class SetTimePeriod {
    private final ServiceLocation clinic;

    // Constructor
    public SetTimePeriod(ServiceLocation clinic){
        this.clinic = clinic;
    }

    // Setting the number of shifts for a certain time
    public String setEmployees(LocalDate date, int employees) {
        this.clinic.setShift(date, employees);
        return "Set " + employees + " employees for " + date;
    }

    // Adding a time period to a clinic if it is not already there
    public String addTimePeriod(LocalDateTime dateTime) throws Exception {
        if (this.clinic.shiftAvailable(dateTime.toLocalDate())
                && !this.clinic.checkTimePeriod(dateTime)){
            int slots = this.clinic.getShiftForDate(dateTime.toLocalDate());
            TimePeriod addedTimePeriod = new TimePeriod(dateTime, slots);
            this.clinic.addTimePeriod(addedTimePeriod, dateTime.toLocalDate());
            return addedTimePeriod.toString();
        }else if(!this.clinic.shiftAvailable(dateTime.toLocalDate())) {
            throw new Exception(ExceptionConstants.NO_SHIFT_AVAILABLE);
        }else {
            throw new Exception(ExceptionConstants.TIME_PERIOD_ALREADY_EXISTS);
        }
    }

    // Removing a time period from a clinic if there exists a time period at the specified date and time.
    public String removeTimePeriod(LocalDateTime dateTime) throws Exception {
        if(this.clinic.checkTimePeriod(dateTime)){
            this.clinic.removeTimePeriod(dateTime);
            return "Time: " + dateTime;
        }
        else{
            throw new Exception(ExceptionConstants.TIME_PERIOD_DOES_NOT_EXIST);
        }
    }

    /* Adding multiple time periods from a starting time until the end based on
       interval inputted in the form of minutes */
    public int addMultipleTimePeriods(LocalDateTime start, LocalDateTime end, int interval) throws Exception {
        if(!start.toLocalDate().equals(end.toLocalDate()) || interval <= 0){
            throw new Exception(ExceptionConstants.INVALID_RANGE_OR_INTERVAL);
        }

        ArrayList<LocalDateTime> times = new ArrayList<>();
        while (start.isBefore(end)){
            times.add(start);
            start = start.plusMinutes(interval);
        }
        int counter = 0;
        for(LocalDateTime time: times){
            if(!this.clinic.checkTimePeriod(time)){
                this.clinic.addTimePeriod(new TimePeriod(time,
                        this.clinic.getShiftForDate(time.toLocalDate())), time.toLocalDate());
                counter += 1;
            }
        }
        return counter;
    }

}
