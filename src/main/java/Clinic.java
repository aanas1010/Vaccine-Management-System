import java.util.ArrayList;

public class Clinic{
    private VaccineSupply supply;

    public Clinic(VaccineSupply supply) {
        this.supply = supply;
    }

    public ArrayList<VaccineBatch> getSupply(){
        return this.supply.getBatchList();
    }
}
