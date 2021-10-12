import java.util.ArrayList;

public class Clinic{
    private int clinicId;
    private VaccineSupply supply;
    private VaccinationLog PastVaccinations;


    // Constructor
    public Clinic(int id) {
        this.clinicId = id;
        this.supply = new VaccineSupply();
    }

    // Return the batchList from the clinic's vaccine supply
    public ArrayList<VaccineBatch> getSupply(){
        return this.supply.getBatchList();
    }

    public int getClinicId() {
        return this.clinicId;
    }
}
