/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.io;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import projekat.data.Driver;
import projekat.data.Team;
import projekat.data.Track;
import projekat.data.TrackDirection;
import projekat.data.TrackType;
import projekat.util.DBUtil;

/**
 *
 * @author Milica
 */
public class ChampionsIO {

    /**
     * Metoda koja učitava šampiona konstruktora iz baze
     * @param season - sezona za koju se traži šampiion
     * @return Team - šampion
     */
    public static Team loadConsChamp(int season) {
        Team champ = null;
        try {
            System.out.println("Loading champ...");
            int teamId = 0;
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT TEAMCODE FROM constructorchampionship WHERE SEASON = ?");
            st.setInt(1, season);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                teamId = rs.getInt("TEAMCODE");
            }
            st.close();
            DBUtil.con.close();
            if (teamId > 0) {
                champ = TeamIO.loadTeam(teamId);
                System.out.println("Champ loaded");
            }
            else{
                champ = new Team();
                champ.setTeamCode(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return champ;
    }
    
    /**
     * Metoda koja učitava šampiona iz baze
     * @param season - sezona za koju se traži šampion
     * @return Driver - šampion
     */
    public static Driver loadDriversChamp(int season) {
        Driver champ = new Driver();
        try {
            System.out.println("Loading champ...");
            int sln = 0;
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT SLN FROM driverchampionship WHERE SEASON = ?");
            st.setInt(1, season);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                sln = rs.getInt("SLN");
            }
            st.close();
            DBUtil.con.close();
            if (sln > 0) {
                champ = DriverIO.loadDriver(sln);
                System.out.println("Champ loaded");
            }
             else{
                champ = new Driver();
                champ.setSLN(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return champ;
    }
}
