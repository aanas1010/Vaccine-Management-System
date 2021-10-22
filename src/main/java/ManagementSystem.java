import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This is the Interface that the Command Line uses.
 * Includes methods for calling use cases
 */

public interface ManagementSystem {

    boolean addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId);

    ArrayList<Integer> getClinicIds();
}
