package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *  this is the abstract class which acts as the decorator class within the decorator design patter.
 */
public abstract class ClinicDecorator implements ServiceLocation{
    protected final ServiceLocation decoratedClinic;

    /**
     * construct a decorator for clinics
     *
     * @param decoratedClinic an object of the interface for service location.
     */
    public ClinicDecorator(ServiceLocation decoratedClinic)
    {
        super();
        this.decoratedClinic = decoratedClinic;
    }

    /**
     * does the vaccine batch with id x exists.
     *
     * @param id of the wanted batch
     * @return true if the wanted batch exists; false otherwise
     */
    public boolean supplyContainsBatchId(int id) {return decoratedClinic.supplyContainsBatchId(id);}

    /**
     * add the provided batch to the location's supply.
     *
     * @param batch the vaccine batch we are interested in adding.
     */
    public void addBatch(VaccineBatch batch){decoratedClinic.addBatch(batch);}

    /**
     * Set the number of shifts for a date.
     *
     * @param date of the date we are interested.
     * @param num number of employs assigned to that shift.
     */
    public void setShift(LocalDate date, int num){decoratedClinic.setShift(date, num);}

    /**
     * adds a time period the location's list of time periods.
     *
     * @param timePeriod the time period we are adding.
     * @param date the date to whose list we are adding the time period.
     */
    public void addTimePeriod(TimePeriod timePeriod, LocalDate date){decoratedClinic.addTimePeriod(timePeriod, date);}

    /**
     * Removing a time period from a location.
     *
     * @param dateTime of the date we are interested.
     */
    public void removeTimePeriod(LocalDateTime dateTime){decoratedClinic.removeTimePeriod(dateTime);}

    /**
     * getter.
     *
     * @return id of the location.
     */
    public int getServiceLocationId(){return this.decoratedClinic.getServiceLocationId();}

    /**
     * getter.
     *
     * @param date of the date we are interested.
     * @return the number of shift.
     */
    public int getShiftForDate(LocalDate date){return this.decoratedClinic.getShiftForDate(date);}

    /**
     * Checks if a date has more than 0 shifts on a day.
     *
     * @param date of the date we are interested.
     * @return true if there are more than 0 shifts; false otherwise.
     */
    public boolean shiftAvailable(LocalDate date){return this.decoratedClinic.shiftAvailable(date);}

    /**
     * Checks if a date has shifts.
     *
     * @param date of the date we are interested.
     * @return true if there is a shift at the given dateTime; false otherwise.
     */
    public boolean containsShift(LocalDate date){return this.decoratedClinic.containsShift(date);}

    /**
     * Checks if a time period is already stored in the location.
     *
     * @param dateTime of the date we are interested.
     * @return true if the time period of the given dateTime exists; false otherwise.
     */
    public boolean checkTimePeriod(LocalDateTime dateTime){return this.decoratedClinic.checkTimePeriod(dateTime);}

    /**
     * getter.
     *
     * @param date date from which extract the list of time periods.
     * @return list of time periods of the location of the given date.
     */
    public List<TimePeriod> getTimePeriods(LocalDate date){return this.decoratedClinic.getTimePeriods(date);}

    /**
     * getter.
     *
     * @return vaccine log of the location.
     */
    public VaccinationLog getVaccineLog(){return this.decoratedClinic.getVaccineLog();}

    /**
     * getter.
     *
     * @return the location of the location.
     */
    public String getLocation() {return this.decoratedClinic.getLocation();}

    /**
     * getter.
     *
     * @return list of vaccine batches of the location.
     */
    public List<VaccineBatch> getSupply(){return this.decoratedClinic.getSupply();}

    /**
     * getter.
     *
     * @return vaccine supply of the location.
     */
    public VaccineSupply getSupplyObj(){return this.decoratedClinic.getSupplyObj();}

    /**
     * getter.
     *
     * @param dateTime of the date we are interested.
     * @return the time period of the given time.
     */
    public TimePeriod getTimePeriodByTime(LocalDateTime dateTime){return this.decoratedClinic.getTimePeriodByTime(dateTime);}

    public TimePeriod getTimePeriodByID(int periodID){return this.decoratedClinic.getTimePeriodByID(periodID);}

    /**
     * logs an appointment that has already happened.
     *
     * @param vaccinationId id of the past appointment.
     * @param client who received the vaccine.
     * @param dateTime when the vaccine was given.
     * @param vaccineBrand of the vaccine which was administered.
     */
    public void logPastVaccinations(String vaccinationId, User client, LocalDateTime dateTime, String vaccineBrand) {
        this.decoratedClinic.logPastVaccinations(vaccinationId, client, dateTime, vaccineBrand);
    }

    //BookableClinic

    /**
     * Try to add the appointment to the list and return whether the appointment was added.
     *
     * @param ap appointment that is getting added to the list.
     * @return true if appointment added successfully.
     */
     public abstract boolean addAppointment(Appointment ap);

    /**
     * Return the Appointment record by ID.
     *
     * @param id of the appointment
     * @return appointment by id if found; null otherwise.
     */
     public abstract Appointment getAppointmentRecord(int id);

     // --Commented out by Inspection (2021-11-22 3:52 p.m.):
    /*
     * Try to remove an appointment from the list.
     *
     * @param ap appointment we are interested in removing.
     * @return true if the appointment removed successfully; false otherwise.
     */
    // public abstract boolean removeAppointment(Appointment ap);

    /**
     * Try to remove an appointment by ID from the list.
     *
     * @param id the id of the appointment we are interested in removing.
     * @return true if appointment removed successfully; false otherwise.
     */
     public abstract boolean removeAppointmentById(int id);

    /**
     * logs an appointment that has already happened.
     *
     * @param appointmentRecord the appointment we want to log.
     */
     public abstract void logPastVaccinations(Appointment appointmentRecord);

    /**
     * getter.
     *
     * @return list of appointment ids
     */
     public abstract List<Integer> getAppointmentIds();

    /**
     * getters.
     *
     * @param timePeriod the date in which are interested in getting a list of appointments.
     * @return list of appointment at a given date.
     */
     public abstract List<Appointment> getAppointmentByTimePeriod(TimePeriod timePeriod);

}
