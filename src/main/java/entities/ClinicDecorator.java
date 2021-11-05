package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class ClinicDecorator implements ServiceLocation{
    protected ServiceLocation decoratedClinic;

        //constructor
    public  ClinicDecorator(ServiceLocation decoratedClinic)
    {
        super();
        this.decoratedClinic = decoratedClinic;
    }

//     implementing methods of the interface

    @Override
    public boolean supplyContainsBatchId(int id) {return decoratedClinic.supplyContainsBatchId(id);}

    @Override
    public void addBatch(VaccineBatch batch){decoratedClinic.addBatch(batch);}

    @Override
    public void logPastVaccinations(String vaccinationId, Client client, LocalDateTime dateTime, String vaccineBrand){
        decoratedClinic.logPastVaccinations(vaccinationId, client, dateTime, vaccineBrand);
    }

    @Override
    public void setShift(LocalDate date, int num){
        decoratedClinic.setShift(date, num);
    }

    @Override
    public void addTimePeriod(TimePeriod timePeriod, LocalDate date){
        decoratedClinic.addTimePeriod(timePeriod, date);
    }

    @Override
    public void removeTimePeriod(LocalDateTime dateTime){
        decoratedClinic.removeTimePeriod(dateTime);
    }

    @Override
    public int getServiceLocationId(){
        return this.decoratedClinic.getServiceLocationId();
    }

    @Override
    public int getShiftForDate(LocalDate date){
        return this.decoratedClinic.getShiftForDate(date);
    }

    @Override
    public boolean shiftAvailable(LocalDate date){
        return this.decoratedClinic.shiftAvailable(date);
    }

    @Override
    public boolean containsShift(LocalDate date){
        return this.decoratedClinic.containsShift(date);
    }

    @Override
    public boolean checkTimePeriod(LocalDateTime dateTime){
        return this.decoratedClinic.checkTimePeriod(dateTime);
    }

    @Override
    public ArrayList<TimePeriod> getTimePeriods(LocalDate date){
        return this.decoratedClinic.getTimePeriods(date);
    }

    @Override
    public VaccinationLog getVaccineLog(){
        return this.decoratedClinic.getVaccineLog();
    }

    @Override
    public ArrayList<VaccineBatch> getSupply(){
        return this.decoratedClinic.getSupply();
    }

    @Override
    public VaccineSupply getSupplyObj(){
        return this.decoratedClinic.getSupplyObj();
    }


    // //option if we choose not to use casting for clinics:

    // //decorator methods

    // //BookableClinic
    // @Override
    // public abstract boolean addAppointment(Appointment ap);

    // @Override
    // public abstract Appointment getAppointmentRecord(int id);

    // @Override
    // public abstract boolean removeAppointment(Appointment ap);

    // @Override
    // public abstract boolean removeAppointmentById(int id);

    // @Override
    // public abstract void logPastVaccinations(Appointment appointmentRecord);

    // //WalkInClinic
    // @Override
    // public abstract void logPastVaccinations(String vaccinationId, Client client, LocalDateTime dateTime, String vaccineBrand);
}
