package client_booking;

import entities.BookableServiceLocation;
import entities.VaccineBatch;
import entities.Client;
import entities.TimePeriod;


public class AppointmentBooking {
    int appointmentId;
    Client client;
    BookableServiceLocation clinic;
    TimePeriod timePeriod;
    String vaccineBrand;

    public AppointmentBooking(Client client, BookableServiceLocation clinic, TimePeriod timePeriod, String vaccineBrand, int id){
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