/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.data;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import projekat.io.RoundIO;
import projekat.io.TeamIO;
import projekat.util.GeneralUtil;

/**
 *
 * @author Milica
 */
public class ConstructorChampionship extends Championship {

    private Team champion;
    private LinkedHashMap<Team, Double> standings = new LinkedHashMap<>();

    public ConstructorChampionship() {
    }

    public ConstructorChampionship(int sezona) {
        super(sezona);
    }

    public ConstructorChampionship(Team champion) {
        this.champion = champion;
    }

    public ConstructorChampionship(Team champion, int sezona) {
        super(sezona);
        this.champion = champion;
    }

    public ConstructorChampionship(Championship ch) {
        super(ch);
    }

    public Team getChampion() {
        return champion;
    }

    public void setChampion(Team champion) {
        this.champion = champion;
    }

    public LinkedHashMap<Team, Double> getStandings() {
        return standings;
    }

    public void setStandings(LinkedHashMap<Team, Double> standings) {
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
     *
     * Metoda "čita"/štampa redom pojedinačne elemente iz LinkedHashMap
     * standings
     *
     */

    @Override
    public String toString() {
        return "ConstructorChampionship{ season=" + this.getSezona() + ", champion=" + champion + ", standings=" + standings + '}';
    }

    /**
     * Metoda ažurira listu standings za svaki tim na osnovu rezlutata u
     * kvalifikacijama/trkama za svaku rundu
     *
     * @param teamList - lista svih timova
     */
    public void updateStandings(List<Team> teamList) {
        for (Team team : teamList) {
            double teamRes = 0;
            for (Round champRound : this.getChampionshipRounds()) {
                if (!champRound.getQualification().getAwardedTeamPoints().isEmpty()) {
                    teamRes += champRound.getQualification().getAwardedTeamPoints().get(team);
                }
                if (!champRound.getRace().getAwardedTeamPoints().isEmpty()) {
                    teamRes += champRound.getRace().getAwardedTeamPoints().get(team);
                }
            }
            this.standings.put(team, teamRes);
        }
    }

    /**
     * Metoda setuje Champion - kao tim sa najvećim brojem poena u LinkedHashMap
     * standings
     */
    @Override
    public void declareChampion() {
        this.setChampion(GeneralUtil.teamWithMostPoints(this.standings));
    }

    

}
