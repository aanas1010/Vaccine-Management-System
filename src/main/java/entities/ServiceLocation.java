package entities;

import entities.VaccineBatch;
import entities.VaccineSupply;

import java.util.ArrayList;

public interface ServiceLocation {
    int getClinicId();

    ArrayList<VaccineBatch> getSupply();

    VaccineSupply getSupplyObj();
}
