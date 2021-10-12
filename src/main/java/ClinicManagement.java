import java.util.ArrayList;

public class ClinicManagement {
    private ArrayList<Clinic> clinics; //List of clinics, looking to change this into a database
    // Constructor
    public ClinicManagement(ArrayList<Clinic> clinics){this.clinics = clinics;}
    // Call the addBatch function to add a vaccine batch to the selected clinic
    public boolean batchAdd(VaccineBatch batch, Clinic clinic){
        AddBatch newBatch = new AddBatch(clinic, batch);
        return newBatch.addBatch();
    }
    //public void setTimePeriod(Clinic clinic, LocalDateTime Start, LocalDateTime End){}
}
