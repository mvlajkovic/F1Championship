/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import projekat.data.Driver;
import projekat.data.Team;

/**
 *
 * @author Milica
 */
public class GeneralUtil {

    
    

    /**
     * Metoda sortira LinkedHashMap-u sa poenima vozača prema vrednosti
     * (poenima) od najvećeg ka najmanjem
     *
     * @param points - LinkedHashMap sa poenima vozača
     * @return - sortirana LinkedHashMap sa poenima vozača
     */
    public static LinkedHashMap<Driver, Double> sortByDriverPoints(LinkedHashMap<Driver, Double> points) {
        LinkedHashMap<Driver, Double> sortedPoints = new LinkedHashMap<>();
        LinkedHashMap<Driver, Integer> tempMap = new LinkedHashMap<>();
        for (Driver driver : points.keySet()) {
            int pos = 1;
            for (Driver dr : points.keySet()) {
                if (points.get(dr) > points.get(driver)) {
                    pos++;
                }
            }
            tempMap.put(driver, pos);
        }
        for (int position = 1; position <= 20; position++) {
            for (Driver d : tempMap.keySet()) {
                if (tempMap.get(d) == position) {
                    sortedPoints.put(d, points.get(d));
                }
            }
        }
        return sortedPoints;
    }
    
    

    /**
     * Metoda sortira LinkedHashMap-u sa poenima timova prema vrednosti
     * (poenima) od najvećeg ka najmanjem
     *
     * @param points - LinkedHashMap sa poenima timova
     * @return - sortirana LinkedHashMap sa poenima timova
     */
    public static LinkedHashMap<Team, Double> sortByTeamPoints(LinkedHashMap<Team, Double> points) {
        LinkedHashMap<Team, Double> sortedPoints = new LinkedHashMap<>();
        LinkedHashMap<Team, Integer> tempMap = new LinkedHashMap<>();
        for (Team team : points.keySet()) {
            int pos = 1;
            for (Team te : points.keySet()) {
                if (points.get(te) > points.get(team)) {
                    pos++;
                }
            }
            tempMap.put(team, pos);
        }
        for (int position = 1; position <= 20; position++) {
            for (Team t : tempMap.keySet()) {
                if (tempMap.get(t) == position) {
                    sortedPoints.put(t, points.get(t));
                }
            }
        }
        return sortedPoints;
    }

    
    /**
     * Metoda pronalazi vozača sa najvećim brojem poena
     *
     * @param points - LinkedHashMap sa vozačima i poenima
     * @return - Vozač sa najvećim brojem poena
     */
    public static Driver driverWithMostPoints(LinkedHashMap<Driver, Double> points) {
        LinkedHashMap<Driver, Integer> tempMap = new LinkedHashMap<>();
        Driver d = new Driver();
        for (Driver driver : points.keySet()) {
            int pos = 1;
            for (Driver dr : points.keySet()) {
                if (points.get(dr) > points.get(driver)) {
                    pos++;
                }
            }
            tempMap.put(driver, pos);
        }
        for (Driver dr : tempMap.keySet()) {
            if (tempMap.get(dr) == 1) {
                d = dr;
            }
        }
        return d;
    }

    /**
     * Metoda pronalazi tim sa najvećim brojem poena
     *
     * @param points LinkedHashMap sa timovima i poenima
     * @return Vozač sa najvećim brojem poena
     */
    public static Team teamWithMostPoints(LinkedHashMap<Team, Double> points) {
        LinkedHashMap<Team, Integer> tempMap = new LinkedHashMap<>();
        Team t = new Team();
        for (Team team : points.keySet()) {
            int pos = 1;
            for (Team te : points.keySet()) {
                if (points.get(te) > points.get(team)) {
                    pos++;
                }
            }
            tempMap.put(team, pos);
        }
        for (Team te : tempMap.keySet()) {
            if (tempMap.get(te) == 1) {
                t = te;
            }
        }
        return t;
    }

}
