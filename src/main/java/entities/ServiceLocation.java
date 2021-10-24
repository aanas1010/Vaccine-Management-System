package entities;

import entities.VaccineBatch;
import entities.VaccineSupply;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the ServiceLocation interface which supports methods for
 * Reading and writing vaccine and appointment details
 */

public interface ServiceLocation {

    void logPastVaccinations(String vaccinationId, Client client, LocalDateTime dateTime, String vaccineBrand);

    int getServiceLocationId();

    ArrayList<VaccineBatch> getSupply();

    VaccineSupply getSupplyObj();
}
