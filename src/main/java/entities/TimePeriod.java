package entities;

import java.time.LocalDate;
import java.util.List;

/**
 * This is the TimePeriod entity that represents a time of day for a specific date.
 * A TimePeriod has a dateTime and keeps track of how many available slots there are
 * and booked slots in the case of BookableClinics
 */

public class TimePeriod {
    LocalDate dateTime;
    int availableSlots;
    int bookedSlots;

    public TimePeriod(LocalDate dateTime, int availableSlots){
        this.dateTime = dateTime;
        this.availableSlots = availableSlots;
        this.bookedSlots = 0;
    }

    // Try to remove 1 from available slots and add 1 to bookedSlots.
    // Return whether it was added
    public boolean findAndReserveSlot(){
        // If there are no slots, return false
        if(this.getAvailableSlots() == 0) {return false;}

        availableSlots -= 1;
        bookedSlots += 1;
        return true;
    }

    // Getters
    public int getAvailableSlots() {
        return availableSlots;
    }

    public LocalDate getDateTime() {return dateTime;}
}
