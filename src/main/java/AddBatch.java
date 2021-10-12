public class AddBatch {
    private Clinic clinic;
    private VaccineBatch batch;

    // Constructor
    public AddBatch(Clinic clinic, VaccineBatch batch){this.clinic = clinic; this.batch = batch;}

    // Adding a vaccine batch to the supply of a clinic if it is not expired
    public boolean addBatch(){
        if (batch.isExpired()){
            return false;
        }
        else{
            this.clinic.getSupply().add(batch);
            return true;
        }
    }

}
