package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *  [description]
 */
public abstract class ClinicDecorator implements ServiceLocation{
    protected ServiceLocation decoratedClinic;

    /**
     * @param decoratedClinic  [description]
     */
    //constructor
    public ClinicDecorator(ServiceLocation decoratedClinic)
    {
        super();
        this.decoratedClinic = decoratedClinic;
    }

    // implementing methods of the interface

    /**
     * @param id [description]
     * @return [description]
     */
    public boolean supplyContainsBatchId(int id) {return decoratedClinic.supplyContainsBatchId(id);}

    /**
     * @param batch [description]
     */
    public void addBatch(VaccineBatch batch){decoratedClinic.addBatch(batch);}

    /**
     * @param date [description]
     * @param num  [description]
     */
    public void setShift(LocalDate date, int num){
        decoratedClinic.setShift(date, num);
    }

    /**
     * @param timePeriod [description]
     * @param date       [description]
     */
    public void addTimePeriod(TimePeriod timePeriod, LocalDate date){
        decoratedClinic.addTimePeriod(timePeriod, date);
    }

    /**
     * @param dateTime [description]
     */
    public void removeTimePeriod(LocalDateTime dateTime){
        decoratedClinic.removeTimePeriod(dateTime);
    }

    /**
     * @return [description]
     */
    public int getServiceLocationId(){
        return this.decoratedClinic.getServiceLocationId();
    }

    /**
     * @param date [description]
     * @return [description]
     */
    public int getShiftForDate(LocalDate date){
        return this.decoratedClinic.getShiftForDate(date);
    }

    /**
     * @param date [description]
     * @return [description]
     */
    public boolean shiftAvailable(LocalDate date){
        return this.decoratedClinic.shiftAvailable(date);
    }

    /**
     * @param date [description]
     * @return [description]
     */
    public boolean containsShift(LocalDate date){
        return this.decoratedClinic.containsShift(date);
    }

    /**
     * @param dateTime [description]
     * @return [description]
     */
    public boolean checkTimePeriod(LocalDateTime dateTime){
        return this.decoratedClinic.checkTimePeriod(dateTime);
    }

    /**
     * @param date [description]
     * @return [description]
     */
    public ArrayList<TimePeriod> getTimePeriods(LocalDate date){
        return this.decoratedClinic.getTimePeriods(date);
    }

    /**
     * @return [description]
     */
    public VaccinationLog getVaccineLog(){
        return this.decoratedClinic.getVaccineLog();
    }

    /**
     * @return [description]
     */
    public String getLocation() {return this.decoratedClinic.getLocation();}

    /**
     * @return [description]
     */
    public ArrayList<VaccineBatch> getSupply(){
        return this.decoratedClinic.getSupply();
    }

    /**
     * @return [description]
     */
    public VaccineSupply getSupplyObj(){
        return this.decoratedClinic.getSupplyObj();
    }

    /**
     * @param dateTime [description]
     * @return [description]
     */
    public TimePeriod getTimePeriod(LocalDateTime dateTime){
        for (TimePeriod timePeriod: getTimePeriods(dateTime.toLocalDate())){
            if (timePeriod.getDateTime().equals(dateTime)){
                return timePeriod;
            }
        }
        return null;
    }

    /**
     * @param vaccinationId [description]
     * @param client        [description]
     * @param dateTime      [description]
     * @param vaccineBrand  [description]
     */
    public void logPastVaccinations(String vaccinationId, User client, LocalDateTime dateTime, String vaccineBrand) {
        this.decoratedClinic.logPastVaccinations(vaccinationId, client, dateTime, vaccineBrand);
    }

    //BookableClinic

    /**
     * @param ap [description]
     * @return  [description]
     */
     public abstract boolean addAppointment(Appointment ap);

    /**
     * @param id  [description]
     * @return [description]
     */
     public abstract Appointment getAppointmentRecord(int id);

    /**
     * @param ap  [description]
     * @return [description]
     */
     public abstract boolean removeAppointment(Appointment ap);

    /**
     * @param id  [description]
     * @return [description]
     */
     public abstract boolean removeAppointmentById(int id);

    /**
     * @param appointmentRecord  [description]
     */
     public abstract void logPastVaccinations(Appointment appointmentRecord);

    /**
     * @return [description]
     */
     public abstract ArrayList<Integer> getAppointmentIds();

    /**
     * @param timePeriod [description]
     * @return [description]
     */
     public abstract ArrayList<Appointment> getAppointmentByTimePeriod(TimePeriod timePeriod);

}
