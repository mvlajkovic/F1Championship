/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package projekat.util;

import java.util.LinkedHashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projekat.data.Driver;
import projekat.data.Team;

/**
 *
 * @author Milica
 */
public class GeneralUtilTest {

    public GeneralUtilTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of driverWithMostPoints method, of class GeneralUtil.
     */
    @Test
    public void testDriverWithMostPoints() {
        System.out.println("driverWithMostPoints");
        LinkedHashMap<Driver, Double> points = new LinkedHashMap<>();
        Driver d1 = new Driver();
        d1.setSLN(33);
        Driver d2 = new Driver();
        d2.setSLN(16);
        Driver d3 = new Driver();
        d3.setSLN(5);
        points.put(d1, 82.0);
        points.put(d2, 45.0);
        points.put(d3, 35.0);
        Driver result = GeneralUtil.driverWithMostPoints(points);
        assertEquals(d1.getSLN(), result.getSLN());
        fail("Fail testDriverWithMostPoints");
    }

    /**
     * Test of driverWithMostPoints method, of class GeneralUtil.
     */
    @Test
    public void testDriverWithMostPoints1() {
        System.out.println("driverWithMostPoints");
        LinkedHashMap<Driver, Double> points = new LinkedHashMap<>();
        Driver d1 = new Driver();
        d1.setSLN(33);
        Driver d2 = new Driver();
        d2.setSLN(16);
        Driver d3 = new Driver();
        d3.setSLN(5);
        points.put(d1, 22.0);
        points.put(d2, 45.0);
        points.put(d3, 35.0);
        Driver result = GeneralUtil.driverWithMostPoints(points);
        assertNotEquals(d1.getSLN(), result.getSLN());
        fail("Fail testDriverWithMostPoints1");
    }

    /**
     * Test of driverWithMostPoints method, of class GeneralUtil.
     */
    @Test
    public void testDriverWithMostPoints2() {
        System.out.println("driverWithMostPoints");
        LinkedHashMap<Driver, Double> points = new LinkedHashMap<>();
        Driver d1 = new Driver();
        d1.setSLN(33);
        Driver d2 = new Driver();
        d2.setSLN(16);
        Driver d3 = new Driver();
        d3.setSLN(5);
        points.put(d1, 22.0);
        points.put(d2, 22.0);
        points.put(d3, 35.0);
        Driver result = GeneralUtil.driverWithMostPoints(points);
        assertNotEquals(d2.getSLN(), result.getSLN());
        fail("Fail testDriverWithMostPoints2");
    }

    /**
     * Test of driverWithMostPoints method, of class GeneralUtil.
     */
    @Test
    public void testDriverWithMostPoints3() {
        System.out.println("driverWithMostPoints");
        LinkedHashMap<Driver, Double> points = new LinkedHashMap<>();
        Driver d1 = new Driver();
        d1.setSLN(33);
        Driver d2 = new Driver();
        d2.setSLN(16);
        Driver d3 = new Driver();
        d3.setSLN(5);
        points.put(d1, 22.0);
        points.put(d2, 22.0);
        points.put(d3, 35.0);
        Driver result = GeneralUtil.driverWithMostPoints(points);
        assertEquals(d1.getSLN(), result.getSLN());
        fail("Fail testDriverWithMostPoints3");
    }

    /**
     * Test of teamWithMostPoints method, of class GeneralUtil.
     */
    @Test
    public void testTeamWithMostPoints() {
        System.out.println("teamWithMostPoints");
        LinkedHashMap<Team, Double> points = new LinkedHashMap<>();
        Team t1 = new Team();
        Team t2 = new Team();
        Team t3 = new Team();
        Team t4= new Team();
        t1.setTeamCode(1);
        t2.setTeamCode(2);
        t3.setTeamCode(3);
        t4.setTeamCode(4);
        points.put(t1, 88.8);
        points.put(t2, 80.8);
        points.put(t3, 87.8);
        points.put(t4, 28.8);
        Team result = GeneralUtil.teamWithMostPoints(points);
        assertEquals(t1.getTeamCode(), result.getTeamCode());
        fail("Fail testTeamWithMostPoints");
    }
    
    /**
     * Test of teamWithMostPoints method, of class GeneralUtil.
     */
    @Test
    public void testTeamWithMostPoints1() {
        System.out.println("teamWithMostPoints");
        LinkedHashMap<Team, Double> points = new LinkedHashMap<>();
        Team t1 = new Team();
        Team t2 = new Team();
        Team t3 = new Team();
        Team t4= new Team();
        t1.setTeamCode(1);
        t2.setTeamCode(2);
        t3.setTeamCode(3);
        t4.setTeamCode(4);
        points.put(t1, 88.8);
        points.put(t2, 80.8);
        points.put(t3, 87.8);
        points.put(t4, 28.8);
        Team result = GeneralUtil.teamWithMostPoints(points);
        assertNotEquals(t3.getTeamCode(), result.getTeamCode());
        fail("Fail testTeamWithMostPoints1");
    }
    
    /**
     * Test of teamWithMostPoints method, of class GeneralUtil.
     */
    @Test
    public void testTeamWithMostPoints2() {
        System.out.println("teamWithMostPoints");
        LinkedHashMap<Team, Double> points = new LinkedHashMap<>();
        Team t1 = new Team();
        Team t2 = new Team();
        Team t3 = new Team();
        Team t4= new Team();
        t1.setTeamCode(1);
        t2.setTeamCode(2);
        t3.setTeamCode(3);
        t4.setTeamCode(4);
        points.put(t1, 88.8);
        points.put(t2, 80.8);
        points.put(t3, 88.8);
        points.put(t4, 28.8);
        Team result = GeneralUtil.teamWithMostPoints(points);
        assertEquals(t1.getTeamCode(), result.getTeamCode());
        fail("Fail testTeamWithMostPoints2");
    }
    
    /**
     * Test of teamWithMostPoints method, of class GeneralUtil.
     */
    @Test
    public void testTeamWithMostPoints3() {
        System.out.println("teamWithMostPoints");
        LinkedHashMap<Team, Double> points = new LinkedHashMap<>();
        Team t1 = new Team();
        Team t2 = new Team();
        Team t3 = new Team();
        Team t4= new Team();
        t1.setTeamCode(1);
        t2.setTeamCode(2);
        t3.setTeamCode(3);
        t4.setTeamCode(4);
        points.put(t1, 88.8);
        points.put(t2, 80.8);
        points.put(t3, 88.8);
        points.put(t4, 28.8);
        Team result = GeneralUtil.teamWithMostPoints(points);
        assertNotEquals(t3.getTeamCode(), result.getTeamCode());
        fail("Fail testTeamWithMostPoints3");
    }

}
