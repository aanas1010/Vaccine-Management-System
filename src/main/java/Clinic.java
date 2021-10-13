import java.util.ArrayList;

public class Clinic{
    private int clinicId;
    private VaccineSupply supply;

    // Constructor
    public Clinic(int id) {
        this.clinicId = id;
        this.supply = new VaccineSupply();
    }

    public Clinic(int id, VaccineSupply supply) {
        this.clinicId = id;
        this.supply = supply;
    }

    // Return the batchList from the clinic's vaccine supply
    public ArrayList<VaccineBatch> getSupply(){
        return this.supply.getBatchList();
    }

    public int getClinicId() {
        return this.clinicId;
    }

//    public VaccineBatch getOldest(){
//        ArrayList<VaccineBatch> batchList = this.getSupply();
//        VaccineBatch oldestBatch = null;
//        for (VaccineBatch batch : batchList) {
//            if(oldestBatch == null || oldestBatch.getExpiry().isAfter(batch.getExpiry())) {
//                oldestBatch = batch;
//            }
//        }
//        return oldestBatch;
//    }
}
