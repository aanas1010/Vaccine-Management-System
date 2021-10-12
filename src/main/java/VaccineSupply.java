import java.util.ArrayList;
import java.util.HashMap;

public class VaccineSupply {
    private ArrayList<VaccineBatch> batchList;

    public VaccineSupply(ArrayList<VaccineBatch> batchList){
        this.batchList = batchList;
    }

    public boolean isEmpty(){
        return this.batchList.isEmpty();
    }

    public ArrayList<VaccineBatch> getBatchList(){
        return this.batchList;
    }

    public HashMap getAvailableVaccines(){
        HashMap vaccines = new HashMap<String, Integer>();
        for (VaccineBatch batch : this.batchList) {
            if(vaccines.containsKey(batch.getBrand())){
                vaccines.put(batch.getBrand(), (Integer) vaccines.get(batch.getBrand()) + batch.getAvailable());
            }
            else{
                vaccines.put(batch.getBrand(), batch.getAvailable());
            }
        }
        HashMap vaccinesCopy = new HashMap<String, Integer>(vaccines);
        return vaccinesCopy;
    }

    @Override
    public String toString () {
        StringBuilder s = new StringBuilder("[");
        int l = this.batchList.size();
        for (VaccineBatch batch : this.batchList) {
            s.append(batch.getBrand()).append("-").append(batch.getId()).append(", ");
        }
        return s.toString();
    }
//    public VaccineBatch getEarliestBatch(String brand){
//        VaccineBatch earlyBatch = null;
//        for (VaccineBatch batch : this.batchList){
//            if (batch.getBrand() == brand && (earlybatch = null || earlybat)){
//
//            }
//        }
//    }
}
