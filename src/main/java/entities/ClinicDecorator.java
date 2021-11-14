package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class ClinicDecorator implements ServiceLocation{
    protected ServiceLocation decoratedClinic;

    //constructor
    public ClinicDecorator(ServiceLocation decoratedClinic)
    {
        super();
        this.decoratedClinic = decoratedClinic;
    }

    // implementing methods of the interface

    public boolean supplyContainsBatchId(int id) {return decoratedClinic.supplyContainsBatchId(id);}

    public void addBatch(VaccineBatch batch){decoratedClinic.addBatch(batch);}

    public void setShift(LocalDate date, int num){
        decoratedClinic.setShift(date, num);
    }

    public void addTimePeriod(TimePeriod timePeriod, LocalDate date){
        decoratedClinic.addTimePeriod(timePeriod, date);
    }

    public void removeTimePeriod(LocalDateTime dateTime){
        decoratedClinic.removeTimePeriod(dateTime);
    }

    public int getServiceLocationId(){
        return this.decoratedClinic.getServiceLocationId();
    }

    public int getShiftForDate(LocalDate date){
        return this.decoratedClinic.getShiftForDate(date);
    }

    public boolean shiftAvailable(LocalDate date){
        return this.decoratedClinic.shiftAvailable(date);
    }

    public boolean containsShift(LocalDate date){
        return this.decoratedClinic.containsShift(date);
    }

    public boolean checkTimePeriod(LocalDateTime dateTime){
        return this.decoratedClinic.checkTimePeriod(dateTime);
    }

    public ArrayList<TimePeriod> getTimePeriods(LocalDate date){
        return this.decoratedClinic.getTimePeriods(date);
    }

    public VaccinationLog getVaccineLog(){
        return this.decoratedClinic.getVaccineLog();
    }

    public String getLocation() {return this.decoratedClinic.getLocation();}

    public ArrayList<VaccineBatch> getSupply(){
        return this.decoratedClinic.getSupply();
    }

    public VaccineSupply getSupplyObj(){
        return this.decoratedClinic.getSupplyObj();
    }

    public TimePeriod getTimePeriod(LocalDateTime dateTime){
        for (TimePeriod timePeriod: getTimePeriods(dateTime.toLocalDate())){
            if (timePeriod.getDateTime().equals(dateTime)){
                return timePeriod;
            }
        }
        return null;
    }

    public void logPastVaccinations(String vaccinationId, User client, LocalDateTime dateTime, String vaccineBrand) {
        this.decoratedClinic.logPastVaccinations(vaccinationId, client, dateTime, vaccineBrand);
    }


     //BookableClinic
     public abstract boolean addAppointment(Appointment ap);

     public abstract Appointment getAppointmentRecord(int id);

     public abstract boolean removeAppointment(Appointment ap);

     public abstract boolean removeAppointmentById(int id);

     public abstract void logPastVaccinations(Appointment appointmentRecord);

     public abstract ArrayList<Integer> getAppointmentIds();

     public abstract ArrayList<Appointment> getAppointmentByTimePeriod(TimePeriod timePeriod);

}
