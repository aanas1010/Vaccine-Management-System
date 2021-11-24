package entities.VaccineBatchBuilder;

public class VaccineDistributor {

    private VaccineBuilder batchBuilder;

    void setBatch(String brand){
        if(brand.equals("Pfizer")){
            batchBuilder = new PfizerBatch();
        }
        else if (brand.equals("Moderna")){
            batchBuilder = new ModernaBatch();
        }
        else{
            System.out.println("Not a recognized vaccine brand");
        }

        if(batchBuilder != null){
            buildBatch();
        }
    }

    void buildBatch(){
        batchBuilder.newBatch();
        batchBuilder.putBrand();
        batchBuilder.addQuantity();
        batchBuilder.addExpiry();
        batchBuilder.setReserved();
        batchBuilder.shipBatch();
    }
}
