package client_booking;

import entities.BookableServiceLocation;
import entities.TimePeriod;
import entities.VaccineBatch;
import entities.Client;


public class AppointmentBooking {
    int appointmentId;
    Client client;
    BookableServiceLocation clinic;
    TimePeriod timePeriod;
    String vaccineBrand;
    
    public AppointmentBooking(Object client, Object clinic, TimePeriod timePeriod, String vaccineBrand, int id){
        this.client = (Client) client;
        this.clinic = (BookableServiceLocation) clinic;
        this.timePeriod = timePeriod;
        this.vaccineBrand = vaccineBrand;
        this.appointmentId = id;
    }

    public boolean isAppointmentAvailable()
    {
        return false;
    }

    public boolean createAppoinment()
    {
        if(isAppointmentAvailable())
        {
            return true;
        }
        else
        {
            System.out.println("appointment unavailble");
            return false;
        }
    }

}
