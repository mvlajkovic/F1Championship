/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projekat.data;

import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Milica
 */
public interface Driven {
    
    //is return void or LinkedHashMap<Driver, Integer>
    void addResults(List<Driver> driverList);
    void calculateDriverPoints(LinkedHashMap<Driver, Integer> results);
    void calculateTeamPoints(LinkedHashMap<Driver, Double> points, List<Team> teamList);
    String printPoints();
    String printTeamResult();

}
