import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This is the Use Case for adding batches.
 * Every time the use case is needed, a new BatchAdding instance is created
 * with the parameters of the batch
 */

public class ClinicManagement implements ClinicManagerInterface {
    //List of clinics, looking to change this into a database
    private ArrayList<ServiceLocation> clinics;


    //Constructor for a list of clinics
    public ClinicManagement(ArrayList<ServiceLocation> clinics){
        this.clinics = clinics;
    }

    //Constructor for num clinics with IDs 0 to num-1
    public ClinicManagement(int num) {
        ArrayList<ServiceLocation> clinicsList = new ArrayList<>(num);
        for(int i=0;i<num;i++) {
            //Create new clinic with ID i
            clinicsList.add(new Clinic(i));
        }
        clinics = clinicsList;
    }

    // Call the addBatch function to add a vaccine batch to the selected clinic
    public boolean addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId){
        ServiceLocation clinicById = getClinicById(clinicId);
        BatchAdding newBatch = new BatchAdding(clinicById, batchBrand, batchQuantity, batchExpiry, batchId);
        return newBatch.addBatch();
    }

    //Return a list of the clinic IDs
    public ArrayList<Integer> getClinicIds() {
        int arraySize = clinics.size();
        ArrayList<Integer> clinicNums = new ArrayList<>(arraySize);
        for (ServiceLocation clinic : clinics) {
            //Call the getClinicId method for all clinics
            clinicNums.add(clinic.getClinicId());
        }
        return clinicNums;
    }

    private ServiceLocation getClinicById(int clinicId) {
        for (ServiceLocation clinic : clinics) {
            //Call the getClinicId method for all clinics
            if(clinic.getClinicId() == clinicId) {
                return clinic;
            }
        }
        return null;
    }
}
