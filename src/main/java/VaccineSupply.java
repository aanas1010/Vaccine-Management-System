import java.util.ArrayList;
import java.util.HashMap;

public class VaccineSupply {
    private ArrayList<VaccineBatch> batchList;

    public VaccineSupply(){this.batchList = new ArrayList<VaccineBatch>();}

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
        HashMap<String, Integer> vaccines = new HashMap<String, Integer>();
        for (VaccineBatch batch : this.batchList) {
            if(vaccines.containsKey(batch.getBrand())){
                vaccines.put(batch.getBrand(), (Integer) vaccines.get(batch.getBrand()) + batch.getAvailable());
            }
            else{
                vaccines.put(batch.getBrand(), batch.getAvailable());
            }
        }
        HashMap<String, Integer> vaccinesCopy = new HashMap<String, Integer>(vaccines);
        return vaccinesCopy;
    }


    // Return a string of the list of batches using the brand and id number
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("-----BATCHLIST-----: \n[");
        int l = this.batchList.size();
        for (VaccineBatch batch : this.batchList) {
            s.append(batch.getBrand()).append("-").append(batch.getId())
                    .append(": ").append(batch.getReserve()).append("/")
                    .append(batch.getAvailable()).append(" RESERVED | EXP:")
                    .append(batch.getExpiry());
            s.append("\n");
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
