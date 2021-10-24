package clinic_management;

import entities.Clinic;
import entities.TimePeriod;

import java.time.LocalDate;

public class SetTimePeriod {
    private Clinic clinic;


    public SetTimePeriod(Clinic clinic){
        this.clinic = clinic;
    }

    public boolean setEmployees(LocalDate date, int employees) {
        if (this.clinic.containsShift(date)){
            return false;
        }
        else{
            this.clinic.setShift(date, employees);
            return true;
        }
    }

    public boolean addTimePeriod(LocalDate date){
        if (this.clinic.checkShifts(date) && this.clinic.checkTimePeriod(date)){
            int slots = this.clinic.getShiftForDate(date);
            this.clinic.addTimePeriod(new TimePeriod(date, slots));
            return true;
        }
        return false;
    }
}
