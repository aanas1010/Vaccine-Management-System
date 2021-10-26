package client_booking;

import entities.*;


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
        //if(isAppoinmentAvailble)
        if(true)
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
