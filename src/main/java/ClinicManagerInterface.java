import java.time.LocalDate;
import java.util.ArrayList;

public interface ClinicManagerInterface {

    ArrayList<Integer> getClinicIds();

    boolean addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId);

}
