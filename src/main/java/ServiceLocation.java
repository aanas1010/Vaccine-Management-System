import java.util.ArrayList;

public interface ServiceLocation {
    int getClinicId();

    ArrayList<VaccineBatch> getSupply();

    VaccineSupply getSupplyObj();
}
