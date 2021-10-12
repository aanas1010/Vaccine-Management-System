public class VaccineManagementSystem {
    private ClinicManagement clinicManager;
    //private ClientBooking clientBooker;
    // Constructor (Comments are for a future class)
    public VaccineManagementSystem(ClinicManagement clinicManager/*, ClientBooking clientBooker*/){
        this.clinicManager = clinicManager;
        /*this.clientBooker = clientBooker*/
    }
    // Returning the clinic manager
    public ClinicManagement getClinicManager() {
        return clinicManager;
    }

//    public ClientBooking getClientBooker() {
//        return clientBooker;
//    }
}
