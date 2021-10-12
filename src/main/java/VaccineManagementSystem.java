import java.util.ArrayList;

public class VaccineManagementSystem {
    private ArrayList<Clinic> clinicList;

    public VaccineManagementSystem(int num) {
        ArrayList<Clinic> clinics = new ArrayList<Clinic>(num);
        for(int i=0;i<num;i++) {
            clinics.add(new Clinic(i));
        }
        clinicList = clinics;
    }
    public ArrayList<Clinic> getClinics() {
        return clinicList;
    }

    public ArrayList<Integer> getClinicIds() {
        int arraySize = clinicList.size();
        ArrayList<Integer> clinicNums = new ArrayList<Integer>(arraySize);
        for (Clinic clinic : clinicList) {
            clinicNums.add(clinic.getClinicId());
        }
        return clinicNums;
    }
}