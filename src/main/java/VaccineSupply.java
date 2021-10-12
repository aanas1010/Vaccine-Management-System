import java.util.ArrayList;
import java.util.HashMap;

public class VaccineSupply {
    private ArrayList<VaccineBatch> batchList;

    public VaccineSupply(ArrayList<VaccineBatch> batchList){
        this.batchList = batchList;
    }

    // Return whether the batchList has any batches in it
    public boolean isEmpty(){
        return this.batchList.isEmpty();
    }

    // Return the batchList
    public ArrayList<VaccineBatch> getBatchList(){
        return this.batchList;
    }

    // Return a hashmap that stores vaccine brand and the corresponding amount of vaccine
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


    // Return a string of the list of batches using the brand and id number
    @Override
    public String toString () {
        StringBuilder s = new StringBuilder("[");
        int l = this.batchList.size();
        for (VaccineBatch batch : this.batchList) {
            s.append(batch.getBrand()).append("-").append(batch.getId()).append(", ");
        }
        s.append("]");
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
