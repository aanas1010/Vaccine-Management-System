package client_booking;

import entities.BookableServiceLocation;
import entities.VaccineBatch;
import entiites.client;


public class AppointmentBooking {
    int appointmentId;
    client client;
    BookableServiceLocation clinic;
    TimePeriod timePeriod;
    String vaccineBrand;
    
    public AppointmentBooking(Object client, Object clinic, TimePeriod timePeriod, String vaccineBrand, int id){
        this.client = client;
        this.clinic = clinic;
        this.timePeriod = timePeriod;
        this.vaccineBrand = vaccineBrand;
        this.appointmentId = id;
    }

    boolean isAppoinmentAvailble()
    {
        return false;
    }

    boolean createAppoinment()
    {
        if(isAppoinmentAvailble)
        {
            return true;
        }
        else
        {
            Sytem.out.println("appointment unavailble");
            return false;
        }
    }

}
