import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the Entity for a clinic's vaccine supply
 * that stores the batches for a given clinic
 */

public class VaccineSupply {
    private final ArrayList<VaccineBatch> batchList;

    // Constructor
    public VaccineSupply(){
        this.batchList = new ArrayList<>();
    }

    // Overloaded Constructor for testing
    public VaccineSupply(ArrayList<VaccineBatch> batchList){
        this.batchList = batchList;
    }

    // Return a hashmap that stores vaccine brand and the corresponding amount of vaccine
    public HashMap getAvailableVaccines(){
        HashMap<String, Integer> vaccines = new HashMap<String, Integer>();
        // Get the total quantities of each vaccine brand and put in hashmap
        for (VaccineBatch batch : this.batchList) {
            // If the brand is already in the hashmap, add to the value
            if(vaccines.containsKey(batch.getBrand())){
                vaccines.put(batch.getBrand(), (Integer) vaccines.get(batch.getBrand()) + batch.getAvailable());
            }
            // If the brand is not already in the hashmap, add it
            else{
                vaccines.put(batch.getBrand(), batch.getAvailable());
            }
        }
        HashMap<String, Integer> vaccinesCopy = new HashMap<String, Integer>(vaccines);
        return vaccinesCopy;
    }

    // Return whether the batchList has any batches in it
    public boolean isEmpty(){
        return this.batchList.isEmpty();
    }

    // Return a string of the list of batches using the brand and id number
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("--------BATCHLIST-------- \n[\n");
        for (VaccineBatch batch : this.batchList) {
            s.append("  ").append(batch.getBrand()).append("-").append(batch.getId())
                    .append(": ").append(batch.getReserve()).append("/")
                    .append(batch.getAvailable()).append(" RESERVED | EXP:")
                    .append(batch.getExpiry());
            s.append("\n");
        }
        s.append("]");
        return s.toString();
    }


    // Getters
    public ArrayList<VaccineBatch> getBatchList(){return this.batchList;}
}
