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

//    public ClientBooking getClientBooker() {
//        return clientBooker;
//    }
}