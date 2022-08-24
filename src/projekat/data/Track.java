/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.data;

/**
 *
 * @author Milica
 */
public class Track {
    private int trackId;
    private String name;
    private String location;
    private double kmLength;
    private TrackType type = TrackType.Undefined;
    private TrackDirection direction = TrackDirection.Undefined;

    public Track() {
    }

    public Track(String name, String location, double kmLength, TrackType type, TrackDirection direction) {
        this.name = name;
        this.location = location;
        this.kmLength = kmLength;
        this.type = type;
        this.direction = direction;
    }

    public Track(int trackId, String name, String location, double kmLength) {
        this.trackId = trackId;
        this.name = name;
        this.location = location;
        this.kmLength = kmLength;
    }
    
    
    
     public Track(Track t) {
        this.name = t.name;
        this.location = t.location;
        this.kmLength = t.kmLength;
        this.type = t.type;
        this.direction = t.direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getKmLength() {
        return kmLength;
    }

    public void setKmLength(double kmLength) {
        this.kmLength = kmLength;
    }

    public TrackType getType() {
        return type;
    }

    public void setType(TrackType type) {
        this.type = type;
    }

    public TrackDirection getDirection() {
        return direction;
    }

    public void setDirection(TrackDirection direction) {
        this.direction = direction;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }
    
    //THIS
    @Override
    public String toString() {
        return "Track " + name + ", " + location + ", " + kmLength + "km, type: " + type + ", direction: " + direction;
    }

}
