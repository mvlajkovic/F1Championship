/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package projekat.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;
import projekat.data.Driver;
import projekat.data.Team;

/**
 *
 * @author Milica
 */
public class RoundUtilTest {
    
    public RoundUtilTest() {
    }
    
    @Before
    public void setUp() {
    }

    

    /**
     * Test of searchByCarNumber method, of class RoundUtil.
     */
    @Test
    public void testSearchByCarNumber() throws Exception {
        //vraća grešku jer metoda baca exception ako je nepostojeći broj
        System.out.println("searchByCarNumber");
        List<Driver> driverList = new ArrayList<>();
        Driver d1 = new Driver();
        d1.setCarNumber(33);
        Driver d2 = new Driver();
        d2.setCarNumber(16);
        Driver d3 = new Driver();
        d3.setCarNumber(5);
        driverList.add(d1);
        driverList.add(d2);
        driverList.add(d3);
        int number = 7;
        Driver expResult = null;
        Driver result = RoundUtil.searchByCarNumber(driverList, number);
        assertNotEquals(expResult, result.getCarNumber(), "Failed testSearchByCarNumber.");
    }
    
    /**
     * Test of searchByCarNumber method, of class RoundUtil.
     */
    @Test
    public void testSearchByCarNumber1() throws Exception {
        System.out.println("searchByCarNumber");
        List<Driver> driverList = new ArrayList<>();
        Driver d1 = new Driver();
        d1.setCarNumber(33);
        Driver d2 = new Driver();
        d2.setCarNumber(16);
        Driver d3 = new Driver();
        d3.setCarNumber(5);
        driverList.add(d1);
        driverList.add(d2);
        driverList.add(d3);
        int number = 33;
        Driver result = RoundUtil.searchByCarNumber(driverList, number);
        assertEquals(33, result.getCarNumber(), "Failed testSearchByCarNumber.");
    }
    
    /**
     * Test of searchByCarNumber method, of class RoundUtil.
     */
    @Test
    public void testSearchByCarNumber2() throws Exception {
        //greška jer metoda baca exception ako nema broj
        System.out.println("searchByCarNumber");
        List<Driver> driverList = new ArrayList<>();
        int number = 0;
        Driver expResult = null;
        Driver result = RoundUtil.searchByCarNumber(driverList, number);
        assertEquals(expResult, result.getCarNumber());
        fail("Failed testSearchByCarNumber2");
    }

   
    
}
