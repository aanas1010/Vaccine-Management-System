import java.util.ArrayList;

public class ClinicManagement {
    //List of clinics, looking to change this into a database
    private ArrayList<Clinic> clinics;


    //Constructor for a list of clinics
    public ClinicManagement(ArrayList<Clinic> clinics){
        this.clinics = clinics;
    }

    //Constructor for num clinics with IDs 0 to num-1
    public ClinicManagement(int num) {
        ArrayList<Clinic> clinicsList = new ArrayList<Clinic>(num);
        for(int i=0;i<num;i++) {
            //Create new clinic with ID i
            clinicsList.add(new Clinic(i));
        }
        clinics = clinicsList;
    }

    //Return a list of the clinic IDs
    public ArrayList<Integer> getClinicIds() {
        int arraySize = clinics.size();
        ArrayList<Integer> clinicNums = new ArrayList<Integer>(arraySize);
        for (Clinic clinic : clinics) {
            //Call the getClinicId method for all clinics
            clinicNums.add(clinic.getClinicId());
        }
        return clinicNums;
    }


    // Call the addBatch function to add a vaccine batch to the selected clinic
    public boolean addBatch(VaccineBatch batch, Clinic clinic){
        BatchAdding newBatch = new BatchAdding(clinic, batch);
        return newBatch.addBatch();
    }

    public ArrayList<Clinic> getClinics() {
        return clinics;
    }

    //public void setTimePeriod(Clinic clinic, LocalDateTime Start, LocalDateTime End){}
}
