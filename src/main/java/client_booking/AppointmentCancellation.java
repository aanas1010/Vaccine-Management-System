package client_booking;

import entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentCancellation {
    Client client;
    BookableServiceLocation clinic;
    TimePeriod timePeriod;
    String vaccineBrand;
    int appointmentId;

  public AppointmentCancellation(Client client, BookableServiceLocation clinic, TimePeriod timePeriod, String vaccineBrand, int id){
    this.client = client;
    this.clinic = clinic;
    this.timePeriod = timePeriod;
    this.vaccineBrand = vaccineBrand;
    this.appointmentId = id;
  }



//  public boolean deleteAppoinment(){
//     if (this.client.getHasAppointment()) {
//         //Need to make timeslot increase by 1
//         this.clinic.getTimePeriods(this.timePeriod.getDateTime().toLocalDate());
//         //↳We're currently in the value part of the hashmap. We've found the key (the date) and must now find the time.
//        this.clinic.removeAppointmentById(this.appointmentId); //Remove Appointment from list
//         //Vaccine dose increase by 1
//         this.clinic.removeAppointmentById(this.appointmentId);
//         //
//     }
//  }


}
