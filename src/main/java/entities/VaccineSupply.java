package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is the Entity for a clinic's vaccine supply
 * that stores the batches for a given clinic
 */

public class VaccineSupply {

    private final List<VaccineBatch> batchList;

    /**
     * construct a supply of vaccines object.
     */
    public VaccineSupply(){this.batchList = new ArrayList<>();}

    /**
     * Overloaded Constructor for testing.
     *
     * @param batchList list of batches in the supply.
     */
    public VaccineSupply(List<VaccineBatch> batchList){this.batchList = batchList;}

    /**
     * getter.
     *
     * @return a hashmap that stores vaccine brand and the corresponding amount of vaccine.
     */
    public HashMap<String, Integer> getAvailableVaccines(){
        HashMap<String, Integer> vaccines = new HashMap<>();
        // Get the total quantities of each vaccine brand and put in hashmap
        for (VaccineBatch batch : this.batchList) {
            // If the brand is already in the hashmap, add to the value
            if(vaccines.containsKey(batch.getBrand())){
                vaccines.put(batch.getBrand(), vaccines.get(batch.getBrand()) + batch.getAvailable());
            }
            // If the brand is not already in the hashmap, add it
            else{
                vaccines.put(batch.getBrand(), batch.getAvailable());
            }
        }
        return new HashMap<>(vaccines);
    }

    /**
     * Return a string of the list of batches using the brand and id number.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("----------------BATCH LIST---------------- \n");
        for (VaccineBatch batch : this.batchList) {
            s.append(batch.getBrand()).append("-").append(batch.getId())
                    .append(": ").append(batch.getReserve()).append("/")
                    .append(batch.getAvailable() + batch.getReserve()).append(" RESERVED | EXP:")
                    .append(batch.getExpiry());
            s.append("\n");
        }
        s.append("------------------------------------------");
        return s.toString();
    }

    // Getters

    /**
     * getter.
     *
     * @return a list of vaccine batches in the supply.
     */
    public List<VaccineBatch> getBatchList(){return this.batchList;}
}
