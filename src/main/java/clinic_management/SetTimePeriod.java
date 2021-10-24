package clinic_management;

import entities.Clinic;
import entities.TimePeriod;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SetTimePeriod {
    private Clinic clinic;

    // Constructor
    public SetTimePeriod(Clinic clinic){
        this.clinic = clinic;
    }

    // Setting the number of shifts for a certain time
    public boolean addEmployees(LocalDate date, int employees) {
        if (this.clinic.containsShift(date)){
            return false;
        }
        else{
            this.clinic.setShift(date, employees);
            return true;
        }
    }

    // Adding a time period to a clinic if it is not already there
    public boolean addTimePeriod(LocalDateTime dateTime, LocalDate date){
        if (this.clinic.shiftAvailable(date) && this.clinic.checkTimePeriod(dateTime, date)){
            int slots = this.clinic.getShiftForDate(dateTime.toLocalDate());
            this.clinic.addTimePeriod(new TimePeriod(dateTime, slots), date);
            return true;
        }
        return false;
    }
}
