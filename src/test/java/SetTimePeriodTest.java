import clinic_management.BatchAdding;
import clinic_management.SetTimePeriod;
import entities.Clinic;
import entities.TimePeriod;
import entities.VaccineBatch;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SetTimePeriodTest {
    SetTimePeriod setTimePeriod;
    Clinic clinic;
    LocalDateTime testDateTime;
    LocalDate testDate;
    @Before // Setting up before the tests
    public void setUp() throws Exception{
        clinic = new Clinic(1);
        setTimePeriod = new SetTimePeriod(clinic);
        testDateTime = LocalDateTime.of(2021, 10, 31, 12, 0);
        testDate = LocalDate.of(2021, 11, 4);
    }

    @Test(timeout = 100) // Testing setting the number of employees
    public void TestSetEmployees(){
        setTimePeriod.setEmployees(testDate, 5);
        assertEquals(clinic.getShiftForDate(testDate), 5);
    }

    @Test(timeout = 100) // Testing a time period is added
    public void TestAddTimePeriodTrue(){
        setTimePeriod.setEmployees(testDateTime.toLocalDate(), 5);
        assert(setTimePeriod.addTimePeriod(testDateTime));
        //assert(clinic.getTimePeriods(testDateTime.toLocalDate()).contains(new TimePeriod(testDateTime, 5)));
    }

    @Test(timeout = 100) // Testing a time period is not added
    public void TestAddTimePeriodFalse(){
        assert(!setTimePeriod.addTimePeriod(testDateTime));
    }

    @Test(timeout = 100) // Testing a time period is not added after it has already been added
    public void TestAddTimePeriodNotAdded(){
        setTimePeriod.setEmployees(testDateTime.toLocalDate(), 5);
        setTimePeriod.addTimePeriod(testDateTime);
        assert(clinic.checkTimePeriod(testDateTime));
    }

    @Test(timeout = 100) // Testing a time period is removed
    public void TestRemoveTimePeriodTrue(){
        setTimePeriod.setEmployees(testDateTime.toLocalDate(), 5);
        setTimePeriod.addTimePeriod(testDateTime);
        setTimePeriod.removeTimePeriod(testDateTime);
        assert(!clinic.checkTimePeriod(testDateTime));
    }

    @Test(timeout = 100) // Testing that a time period is not removed when it does not exist
    public void TestRemoveTimePeriodFalse(){
        assert(!setTimePeriod.removeTimePeriod(testDateTime));
    }

    @Test(timeout = 100) // Testing the correct number of time period is added
    public void TestAddMultipleTimePeriods(){
        setTimePeriod.setEmployees(testDateTime.toLocalDate(), 5);
        assertEquals(setTimePeriod.addMultipleTimePeriods(testDateTime,
                testDateTime.plusMinutes(120), 30), 4);
    }
    @Test(timeout = 100) // Testing the correct number of time period is added after a time period is added
    public void TestAddMultipleTimePeriodsSelected(){
        setTimePeriod.setEmployees(testDateTime.toLocalDate(), 5);
        setTimePeriod.addTimePeriod(testDateTime);
        assertEquals(setTimePeriod.addMultipleTimePeriods(testDateTime,
                testDateTime.plusMinutes(120), 30), 3);
    }

//    @Test(timeout = 100)
//    public void TestAddMultipleTimePeriodsResults(){
//        setTimePeriod.setEmployees(testDateTime.toLocalDate(), 5);
//        setTimePeriod.addMultipleTimePeriods(testDateTime, testDateTime.plusMinutes(120), 30);
//        ArrayList<TimePeriod> testList = new ArrayList<>();
//        LocalDateTime iteratorTime = testDateTime;
//        while(iteratorTime.isBefore(testDateTime.plusMinutes(120))) {
//            testList.add(new TimePeriod(iteratorTime, 5));
//            iteratorTime = iteratorTime.plusMinutes(30);
//        }
//        assertEquals(clinic.getTimePeriods(testDateTime.toLocalDate()), (testList));
//    }
    }
