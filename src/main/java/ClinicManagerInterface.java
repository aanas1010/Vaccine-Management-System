import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This is the Interface that the Vaccine Management System uses.
 * Includes methods for managing clinics
 */

public interface ClinicManagerInterface {

    ArrayList<Integer> getClinicIds();

    boolean addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId);

}
