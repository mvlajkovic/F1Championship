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
import static projekat.io.DriverIO.loadDrivers;
import projekat.util.DBUtil;

/**
 *
 * @author Milica
 */
public class TeamIO {

    /**
     *
     *
     * @return - lista timova
     */
    public static List<Team> loadTeams() {
        List<Team> teamList = new ArrayList<>();
        try {
            System.out.println("Loading teams...");
            DBUtil.dbConnect();
            String query = "SELECT * FROM team";
            Statement st = (Statement) DBUtil.con.createStatement();

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("NAME");
                String location = rs.getString("LOCATION");
                int teamCode = rs.getInt("TEAMCODE");
                Team tmp = new Team(name, teamCode, location);
                teamList.add(tmp);
            }
            st.close();
            DBUtil.con.close();
            System.out.println("Teams loaded");

        } catch (SQLException ex) {
            Logger.getLogger(TeamIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teamList;
    }

    public static void updateTeam(Team d) {
        try {
            System.out.println("Updating team");
            System.out.println("Team " + d.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("UPDATE team SET NAME=?, LOCATION=? WHERE TEAMCODE=?");
            st.setString(1, d.getName());
            st.setString(2, d.getLocation());
            st.setInt(3, d.getTeamCode());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Team updated");
        } catch (SQLException ex) {
            Logger.getLogger(TeamIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addTeam(Team d) {
        try {
            System.out.println("Adding tea,");
            System.out.println("Team " + d.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("INSERT INTO team (NAME, LOCATION) VALUES (?,?)");
            st.setString(1, d.getName());
            st.setString(2, d.getLocation());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Team added");
        } catch (SQLException ex) {
            Logger.getLogger(TeamIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteTeam(Team d) {
        try {
            System.out.println("Deleting team");
            System.out.println("Team " + d.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("DELETE FROM team WHERE TEAMCODE=?");
            st.setInt(1, d.getTeamCode());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Team deleted");
        } catch (SQLException ex) {
            Logger.getLogger(TeamIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Team loadTeam(int teamCode) {
        Team tmp = null;
        try {
            System.out.println("Loading team..");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT * FROM team WHERE TEAMCODE = ?");
            st.setInt(1, teamCode);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                tmp= new Team();
                tmp.setTeamCode(teamCode);
                tmp.setName(rs.getString("NAME"));
                tmp.setLocation(rs.getString("LOCATION"));
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
