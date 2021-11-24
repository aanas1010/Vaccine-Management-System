package entities.VaccineBatchBuilder;

import java.time.LocalDate;

public class ModernaBatch implements VaccineBuilder{

    private VaccineBatch1 batch;

    @Override
    public void newBatch(){
        this.batch = new VaccineBatch1();
    }

    @Override
    public void putBrand(){
        this.batch.setBrand("Moderna");
    }

    @Override
    public void addQuantity(){
        this.batch.setQuantity(1000);
    }

    @Override
    public void addExpiry(){
        this.batch.setExpiry(LocalDate.now().plusDays(90));
    }

    @Override
    public void setReserved(){
        this.batch.setReserved(0);
    }

    @Override
    public void shipBatch(){
        System.out.println(this.batch);
    }
}
