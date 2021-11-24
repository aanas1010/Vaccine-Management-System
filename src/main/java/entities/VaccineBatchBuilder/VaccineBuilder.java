package entities.VaccineBatchBuilder;

public interface VaccineBuilder {
    void newBatch();

    void putBrand();

    void addQuantity();

    void addExpiry();

    void setReserved();

    void shipBatch();
}
