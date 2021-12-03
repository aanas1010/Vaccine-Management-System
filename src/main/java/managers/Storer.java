package managers;

import databaseintegration.DataStoring;
import entities.*;

import java.sql.SQLException;

/**
 * This is the Use Case that stores data to a class that implements DataStoring
 */

public class Storer{
    DataStoring dataStoring;

    public Storer(DataStoring dataStoring){this.dataStoring = dataStoring;}

//    public void StoreClients(List<Client> clients){
//        try{
//            for(Client client: clients) {
//                this.dataStoring.writeToClient(client.getHealthCareNumber(), client.getName(), client.getHasAppointment());
//            }
//        }catch(SQLException ex) {
//            System.out.println("Cannot enter the clients");
//        }
//    }

    /**
     * Storing a vaccine batch in the database
     *
     * @param batch the vaccine batch being stored
     * @param clinicID the ID of the clinic where the vaccine batch is located
     */
    public void StoreBatch(VaccineBatch batch, int clinicID){
        try{
            this.dataStoring.writeToVaccineBatch(batch.getId(), clinicID,
                    batch.getBrand(), batch.getExpiry(), batch.getReserve(), batch.getAvailable());
        }catch(SQLException ex) {
            System.out.println("Cannot enter the batch");
        }
    }

    /**
     * Storing a time period in the database
     *
     * @param timePeriod the time period being stored
     * @param clinicID the ID of the clinic where the time period is located
     */
    public void StoreTimePeriod(TimePeriod timePeriod, int clinicID){
        try{
            this.dataStoring.writeToTimePeriods(timePeriod.getID(),
                    clinicID, timePeriod.getAvailableSlots(), timePeriod.getDateTime());
        }catch(SQLException ex) {
            System.out.println("Cannot enter the time period");
        }
    }

    /**
     * Storing an appointment in the database
     *
     * @param appointment the appointment being stored
     * @param clinicID the ID of the clinic where the appointment is located
     */
    public void StoreAppointment(Appointment appointment, int clinicID){
        try{
            this.dataStoring.writeToAppointment(appointment.getAppointmentId(), clinicID,
                    appointment.getClient().getHealthCareNumber(),
                    appointment.getTimePeriod().getID(), appointment.getVaccineBrand());
        }catch(SQLException ex) {
            System.out.println("Cannot enter the appointment");
        }
    }



}
