public class AddBatch {
    private Clinic clinic;
    private VaccineBatch batch;

    public AddBatch(Clinic clinic, VaccineBatch batch){this.clinic = clinic; this.batch = batch;}

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
