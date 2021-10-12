import java.util.ArrayList;

public class Clinic{
    private VaccineSupply supply;

    // Constructor
    public Clinic(VaccineSupply supply) {
        this.supply = supply;
    }

    // Return the batchList from the clinic's vaccine supply
    public ArrayList<VaccineBatch> getSupply(){
        return this.supply.getBatchList();
    }
}
