/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projekat.data.Team;
import projekat.data.Track;
import projekat.data.TrackDirection;
import projekat.data.TrackType;
import projekat.util.DBUtil;

/**
 *
 * @author Milica
 */
public class TrackIO {
     public static List<Track> loadTracks() {
        List<Track> trackList = new ArrayList<>();
        try {
            System.out.println("Loading tracks...");
            DBUtil.dbConnect();
            String query = "SELECT * FROM track";
            Statement st = (Statement) DBUtil.con.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("TRACKID");
                String name = rs.getString("TRACKNAME");
                String location = rs.getString("LOCATION");
                double length = rs.getDouble("KMLENGTH");
                int type = rs.getInt("TRACKTYPEID");
                int dir = rs.getInt("TRACKDIRID");
                Track tmp = new Track(name, location, length, TrackType.valueOf(type), TrackDirection.valueOf(dir));
                tmp.setTrackId(id);
                trackList.add(tmp);
            }
            st.close();
            DBUtil.con.close();
            System.out.println("Tracks loaded");
            
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trackList ;
    }

     public static void updateTrack(Track d){
        try {
            System.out.println("Updating track");
            System.out.println("Track "+ d.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("UPDATE track SET TRACKNAME=?, LOCATION=?, KMLENGTH = ?,  TRACKDIRID = ? , TRACKTYPEID = ? WHERE TRACKID=?");
            st.setString(1, d.getName());
            st.setString(2, d.getLocation());
            st.setDouble(3, d.getKmLength());
            st.setInt(4, d.getDirection().getValue());
            st.setInt(5, d.getType().getValue());
            st.setInt(6, d.getTrackId());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Track updated");
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public static void addTrack(Track d){
        try {
            System.out.println("Adding track");
            System.out.println("Track " + d.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("INSERT INTO track (TRACKNAME, LOCATION, KMLENGTH, TRACKDIRID, TRACKTYPEID ) VALUES (?,?,?,?,?)");
            st.setString(1, d.getName());
            st.setString(2, d.getLocation());
            st.setDouble(3, d.getKmLength());
            st.setInt(4, d.getDirection().getValue());
            st.setInt(5, d.getType().getValue());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Track added");
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    public static void deleteTrack(Track d){
        try {
            System.out.println("Deleting track");
            System.out.println("Track "+ d.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("DELETE FROM track WHERE TRACKID = ?");
            st.setInt(1, d.getTrackId()); 
            st.executeUpdate();
            st.close();
            System.out.println(loadTracks());
            DBUtil.con.close();
            System.out.println("Track deleted");
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static String loadTrackTypesToString(){
        String types = "";
        try{
            System.out.println("Loading track types...");
            DBUtil.dbConnect();
            String query = "SELECT * FROM tracktype";
            Statement st = (Statement) DBUtil.con.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                types += "ID: ";
                types += rs.getString("TRACKTYPEID");
                types += "\tTYPE: ";
                types += rs.getString("TRACKTYPE");
                types +="\n";
            }
            st.close();
            DBUtil.con.close();
            System.out.println("Types loaded");
            
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return types;
    }
    public static String loadTrackDirToString(){
        String types = "";
        try{
            System.out.println("Loading track dir...");
            DBUtil.dbConnect();
            String query = "SELECT * FROM trackdirection";
            Statement st = (Statement) DBUtil.con.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                types += "ID: ";
                types += rs.getString("TRACKDIRID");
                types += "\tDirection: ";
                types += rs.getString("TRACKDIR");
                types +="\n";
            }
            st.close();
            DBUtil.con.close();
            System.out.println("Dirs loaded");
            
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return types;
    }
    
    public static Track loadTrack(int trackId){
        Track tmp = new Track();
        try {
            System.out.println("Loading track...");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT * FROM track WHERE TRACKID = ?");
            st.setInt(1, trackId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
               int id = rs.getInt("TRACKID");
                String name = rs.getString("TRACKNAME");
                String location = rs.getString("LOCATION");
                double length = rs.getDouble("KMLENGTH");
                int type = rs.getInt("TRACKTYPEID");
                int dir = rs.getInt("TRACKDIRID");
                tmp.setTrackId(id);
                tmp.setName(name);
                tmp.setLocation(location);
                tmp.setKmLength(length);
                tmp.setDirection(TrackDirection.valueOf(dir));
                tmp.setType(TrackType.valueOf(type));
            }
            st.close();
            DBUtil.con.close();
         } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }
}
