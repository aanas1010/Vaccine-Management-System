package entities.VaccineBatchBuilder;

import java.time.LocalDate;

public interface Vaccine {

    void setBrand(String brand);

    void setQuantity(int quantity);

    void setExpiry(LocalDate localDate);

    void setId(int id);

    void setReserved(int reserved);
}
