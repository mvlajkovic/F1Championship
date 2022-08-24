/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.data;

/**
 *
 * @author Milica
 */
public class Round {
    private int roundId;
    private Track track;
    private String grandPrixName;
    private Race race;
    private Qualification qualification;

    public Round() {
    }

    public Round(Track track, String grandPrixName) {
        this.track = track;
        this.grandPrixName=grandPrixName;
        
    }

    public Round(int roundId, Track track, String grandPrixName, Race race, Qualification qualification) {
        this.roundId = roundId;
        this.track = track;
        this.grandPrixName = grandPrixName;
        this.race = race;
        this.qualification = qualification;
    }
    
    

    public Round(Track track, String grandPrixName, Race race, Qualification qualification) {
        this.track = track;
        this.grandPrixName=grandPrixName;
        this.race = race;
        this.qualification = qualification;
    }
    
    public Round(Round r){
        this.track=r.track;
        this.grandPrixName=r.grandPrixName;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }


    public String getGrandPrixName() {
        return grandPrixName;
    }

    public void setGrandPrixName(String grandPrixName) {
        this.grandPrixName = grandPrixName;
    }

    @Override
    public String toString() {
        return "Round " + "track: " + track +  ", grandPrixName: " + grandPrixName;
    }
    
    
    
    
    
}
