/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.data;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import projekat.io.DriverIO;
import projekat.io.RoundIO;
import projekat.util.GeneralUtil;

/**
 *
 * @author Milica
 */
public class DriverChampionship extends Championship{
    private Driver champion;
    private LinkedHashMap<Driver, Double> standings = new LinkedHashMap<>();

    public DriverChampionship() {
    }

    public DriverChampionship(int sezona) {
        super(sezona);
    }
    
    public DriverChampionship(Driver champion) {
        this.champion = champion;
    }

    public DriverChampionship(Driver champion, int sezona) {
        super(sezona);
        this.champion = champion;
    }

    public DriverChampionship(Championship ch) {
        super(ch);
    }

    public Driver getChampion() {
        return champion;
    }

    public void setChampion(Driver champion) {
        this.champion = champion;
    }

    public LinkedHashMap<Driver, Double> getStandings() {
        return standings;
    }

    public void setStandings(LinkedHashMap<Driver, Double> standings) {
        this.standings = standings;
    }

    @Override
    public List<Round> getChampionshipRounds() {
        return championshipRounds;
    }

    @Override
    public void setChampionshipRounds(List<Round> championshipRounds) {
        this.championshipRounds = championshipRounds;
    }

    /**
     * Metoda ažurira listu standings za svakog vozača na osnovu rezlutata u kvalifikacijama/trkama za svaku rundu
     * @param driverList - lista svih vozača
     */
    public void updateStandings(List<Driver> driverList) {
        for (Driver driver : driverList) {
            double driversRes = 0;
            for (Round champRound : this.getChampionshipRounds()) {  
                /*if (!champRound.getQualification().getAwardedPoints().isEmpty()) {
                    if(champRound.getQualification().getAwardedPoints().containsKey(driver)){
                        driversRes += champRound.getQualification().getAwardedPoints().get(driver);
                    }
                }*/
                if (!champRound.getRace().getAwardedPoints().isEmpty()){
                    if (champRound.getRace().getAwardedPoints().containsKey(driver)) {
                        driversRes += champRound.getRace().getAwardedPoints().get(driver);
                    }
                }
            }
           this.standings.put(driver, driversRes);
        }
    }

    /**
     * Metoda setuje Champion - kao vozača sa najvećim brojem poena u LinkedHashMap
     * standings
     */
    @Override
    public void declareChampion() {
        this.setChampion(GeneralUtil.driverWithMostPoints(this.standings));
    }

    /**
     * Metoda štampa redom pojedinačne elemente iz LinkedHashMap standings u
     * fajl
     */






    
    
    
    
}
