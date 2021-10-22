import java.util.ArrayList;

/**
 * This is the main Clinic entity which stores the clinic's vaccine supply
 * Clinics have a unique integer ClinicId which is used to identify the clinic
 */

public class Clinic implements ServiceLocation {
    private int clinicId;
    private VaccineSupply supply;

    // Constructor
    public Clinic(int id) {
        this.clinicId = id;
        this.supply = new VaccineSupply();
    }

    // Overloaded Constructor for testing
    public Clinic(int id, VaccineSupply supply) {
        this.clinicId = id;
        this.supply = supply;
    }

    // Getters
    public int getClinicId() {
        return this.clinicId;
    }

    public ArrayList<VaccineBatch> getSupply(){return this.supply.getBatchList();}

    public VaccineSupply getSupplyObj() {
        return this.supply;
    }

}
