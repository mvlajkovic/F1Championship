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
import projekat.data.Driver;
import projekat.data.DriverRole;
import projekat.data.Team;
import projekat.util.DBUtil;

/**
 *
 * @author Milica
 */
public class DriverIO {

    /**
     *Metoda koja učitava vozače iz baze
     *
     * @return - lista vozača
     */
    public static List<Driver> loadDrivers() {
        List<Driver> driverList = new ArrayList<>();
        try {
            System.out.println("Loading drivers...");
            DBUtil.dbConnect();
            String query = "SELECT * FROM driver";
            Statement st = (Statement) DBUtil.con.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int sln = rs.getInt("SLN");
                int driverRole = rs.getInt("DRIVERROLEID");
                int teamCode = rs.getInt("TEAMCODE");
                String name = rs.getString("NAME");
                String lastName = rs.getString("LASTNAME");
                int carNum = rs.getInt("CARNUMBER");
                Driver tmp = new Driver(sln, name, lastName, carNum, teamCode);
                tmp.setRole(DriverRole.valueOf(driverRole));
                driverList.add(tmp);
            }
            st.close();
            DBUtil.con.close();
            System.out.println("Drivers loaded");
            
        } catch (SQLException ex) {
            Logger.getLogger(DriverIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return driverList ;
}
    /**
     * Metoda koja ažuira vozača u bazi
     * @param d - vozač koji se ažurira
     */
    public static void updateDriver(Driver d){
        try {
            System.out.println("Updating driver");
            System.out.println("Driver "+ d.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("UPDATE driver SET NAME=?, LASTNAME=?, DRIVERROLEID=?, TEAMCODE=?, CARNUMBER=? WHERE SLN=?");
            st.setString(1, d.getName());
            st.setString(2, d.getLastName());
            st.setInt(3, d.getRole().getValue());
            st.setInt(4, d.getTeamCode());
            st.setInt(5, d.getCarNumber());
            st.setInt(6, d.getSLN());  
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Driver updated");
        } catch (SQLException ex) {
            Logger.getLogger(DriverIO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    /**
     * Metoda koja dodaje vozača u bazu
     * @param d - vozač koji se dodaje
     */
    public static void addDriver(Driver d){
        try {
            System.out.println("Adding driver");
            System.out.println("Driver " + d.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("INSERT INTO driver (DRIVERROLEID, TEAMCODE, NAME, LASTNAME, CARNUMBER) VALUES (?,?,?,?,?)");
            st.setString(3, d.getName());
            st.setString(4, d.getLastName());
            st.setInt(1, d.getRole().getValue());
            st.setInt(2, d.getTeamCode());
            st.setInt(5, d.getCarNumber());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Driver added");
        } catch (SQLException ex) {
            Logger.getLogger(DriverIO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    /**
     * Metoda koja briše vozača iz baze
     * @param d  vozač koji se briše
     */
    public static void deleteDriver(Driver d){
        try {
            System.out.println("Deleting driver");
            System.out.println("Driver "+ d.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("DELETE FROM driver WHERE SLN = ?");
            st.setInt(1, d.getSLN());  
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Driver deleted");
        } catch (SQLException ex) {
            Logger.getLogger(DriverIO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
     * Metoda koja učitava jednog vozača iz baze
     * @param sln - parametar na osnovu kog se traži vozač - SUPERLICENCE number
     * @return Driver vozač dobijen iz baze
     */
    public static Driver loadDriver(int sln) {
        Driver tmp = null;
        try {
            System.out.println("Loading team..");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT * FROM driver WHERE SLN = ?");
            st.setInt(1, sln);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                tmp= new Driver();
                tmp.setSLN(sln);
                tmp.setName(rs.getString("NAME"));
                tmp.setLastName(rs.getString("LASTNAME"));
                tmp.setCarNumber(rs.getInt("CARNUMBER"));
                tmp.setRole(DriverRole.valueOf(rs.getInt("DRIVERROLEID")));
                tmp.setTeamCode(rs.getInt("TEAMCODE"));
            }
            st.close();
            DBUtil.con.close();

            System.out.println("Team loaded");
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }
    
   
}
