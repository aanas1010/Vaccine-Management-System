import java.time.LocalDate;
import java.util.ArrayList;

public class VaccineManagementSystem {
    private final ClinicManagement clinicManagement;

    //Default constructor for skeleton: make num clinics (we can overload later)
    public VaccineManagementSystem(int num) {
        this.clinicManagement = new ClinicManagement(num);
    }

    public ArrayList<Clinic> getClinics() {
        return this.clinicManagement.getClinics();
    }

    public ArrayList<Integer> getClinicIds() {
        return this.clinicManagement.getClinicIds();
    }

    // Returning the clinic manager
    public ClinicManagement getClinicManager() {
        return this.clinicManagement;
    }

    //Returns whether the batch was added
    public boolean addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId){
        return this.clinicManagement.addBatch(clinicId, batchBrand, batchQuantity, batchExpiry, batchId);
    }

//    public ClientBooking getClientBooker() {
//        return clientBooker;
//    }
}