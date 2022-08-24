/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Milica
 */
public abstract class Championship {

    private int idChampionship;
    private int sezona;

    List<Round> championshipRounds = new ArrayList<>();

    public Championship() {
    }

    public Championship(int sezona) {
        this.sezona = sezona;
    }

    public Championship(int idChampionship, int sezona) {
        this.idChampionship = idChampionship;
        this.sezona = sezona;
    }
    
    public Championship(Championship ch) {
        this.sezona = ch.sezona;
    }

    public int getIdChampionship() {
        return idChampionship;
    }

    public void setIdChampionship(int idChampionship) {
        this.idChampionship = idChampionship;
    }
   
    public int getSezona() {
        return sezona;
    }

    public void setSezona(int sezona) {
        this.sezona = sezona;
    }

    public List<Round> getChampionshipRounds() {
        return championshipRounds;
    }

    public void setChampionshipRounds(List<Round> championshipRounds) {
        this.championshipRounds = championshipRounds;
    }
    
   
    public abstract void declareChampion();



    @Override
    public String toString() {
        return "Championship{" + "sezona=" + sezona + '}';
    }

}
