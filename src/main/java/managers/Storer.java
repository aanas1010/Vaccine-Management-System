package managers;

import databaseintegration.DataRetrieval;
import databaseintegration.DataStoring;

/**
 * This is the Use Case that stores data to a class that implements DataStoring
 */

public class Storer {
    DataStoring dataStoring;

    public Storer(DataStoring dataStoring) {
        this.dataStoring = dataStoring;
    }
}
