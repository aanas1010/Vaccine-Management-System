package entities;

import java.time.LocalDateTime;

/**
 * This is the TimePeriod entity that represents a time of day for a specific date.
 * A TimePeriod has a dateTime and keeps track of how many available slots there are
 * and booked slots in the case of BookableClinics
 */

public class TimePeriod {
    static int timePeriodIDTracker = 1;
    final int timePeriodID;
    final LocalDateTime dateTime;
    int availableSlots;
    int bookedSlots;

    /**
     * constructs a time period object.
     *
     * @param dateTime the date of the time period.
     * @param availableSlots number of available spot in this time period.
     */
    public TimePeriod(LocalDateTime dateTime, int availableSlots){
        this.dateTime = dateTime;
        this.availableSlots = availableSlots;
        this.bookedSlots = 0;
        this.timePeriodID = timePeriodIDTracker;
        timePeriodIDTracker++;
    }

    /**
     * constructs a time period object with explicit ID.
     *
     * @param dateTime the date of the time period.
     * @param availableSlots number of available spot in this time period.
     * @param timePeriodID explicit id of the time period
     */
    public TimePeriod(LocalDateTime dateTime, int availableSlots, int timePeriodID){
        this.dateTime = dateTime;
        this.availableSlots = availableSlots;
        this.bookedSlots = 0;
        this.timePeriodID = timePeriodID;
        timePeriodIDTracker = Math.max(timePeriodID, timePeriodIDTracker) + 1;
    }

    /**
     * try to remove 1 from available slots and add 1 to bookedSlots.
     *
     * @return true if added successfully; false otherwise and there are no slots available.
     */
    public boolean findAndReserveSlot(){
        if(this.getAvailableSlots() == 0) {return false;}
        availableSlots -= 1;
        bookedSlots += 1;
        return true;
    }

    /**
     * Try to add 1 to available slots and remove 1 from bookedSlots. This is done because the Client cancelled.
     */
    public void addAvailableSlot(){
        availableSlots += 1;
        bookedSlots -= 1;
    }

    /**
     * Return a string of the information of this time period.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return  "Time: " + this.dateTime +
                "\nTotal Timeslots: " + this.availableSlots +
                "\nTimeslots Booked: " + this.bookedSlots;
    }

    // Getters

    /**
     * getter.
     *
     * @return ID of the time period
     */
    public int getID() {return timePeriodID;}

    /**
     * getter.
     *
     * @return number of available slots.
     */
    public int getAvailableSlots() {return availableSlots;}

    /**
     * getter.
     *
     * @return number of booked slots.
     */
    public int getBookedSlots() {return availableSlots;}

    /**
     * getter.
     *
     * @return local date of the time period.
     */
    public LocalDateTime getDateTime() {return dateTime;}
}
