import java.time.LocalDate;
import java.util.ArrayList;

public class VaccineManagementSystem {
    private final ClinicManagerInterface clinicManagement;

    //Default constructor for skeleton: make num clinics (we can overload later)
    public VaccineManagementSystem(int num) {
        this.clinicManagement = new ClinicManagement(num);
    }

    public ArrayList<Integer> getClinicIds() {
        return this.clinicManagement.getClinicIds();
    }

    //Returns whether the batch was added
    public boolean addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId){
        return this.clinicManagement.addBatch(clinicId, batchBrand, batchQuantity, batchExpiry, batchId);
    }
}