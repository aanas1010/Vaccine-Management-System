package clinic_management;

import entities.Clinic;
import entities.ServiceLocation;
import entities.TimePeriod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SetTimePeriod {
    private final ServiceLocation clinic;

    // Constructor
    public SetTimePeriod(ServiceLocation clinic){
        this.clinic = clinic;
    }

    // Setting the number of shifts for a certain time
    public boolean setEmployees(LocalDate date, int employees) {
        try{
            this.clinic.setShift(date, employees);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    // Adding a time period to a clinic if it is not already there
    public boolean addTimePeriod(LocalDateTime dateTime){
        if (this.clinic.shiftAvailable(dateTime.toLocalDate())
                && !this.clinic.checkTimePeriod(dateTime)){
            int slots = this.clinic.getShiftForDate(dateTime.toLocalDate());
            this.clinic.addTimePeriod(new TimePeriod(dateTime, slots), dateTime.toLocalDate());
            return true;
        }
        return false;
    }

    // Removing a time period from a clinic if there exists a time period at the specified date and time.
    public boolean removeTimePeriod(LocalDateTime dateTime){
        if(!this.clinic.checkTimePeriod(dateTime)){
            return false;
        }
        else{
            this.clinic.removeTimePeriod(dateTime);
            return true;
        }
    }

    /* Adding multiple time periods from a starting time until the end based on
       interval inputted in the form of minutes */
    public int addMultipleTimePeriods(LocalDateTime start, LocalDateTime end, int interval){
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
