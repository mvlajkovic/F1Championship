/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import projekat.data.ConstructorChampionship;
import projekat.data.Driver;
import projekat.data.DriverChampionship;
import projekat.data.Qualification;
import projekat.data.QualificationType;
import projekat.data.Race;
import projekat.data.Round;
import projekat.data.Team;
import projekat.data.Track;
import projekat.data.TrackDirection;
import projekat.data.TrackType;
import projekat.util.DBUtil;
import projekat.util.GeneralUtil;

/**
 *
 * @author Milica
 */
public class RoundIO {

    /**
     * Metoda koja učitava runde iz baze
     * @param season - sezona za koju se učitvaju
     * @return lista rundi
     */
    public static List<Round> loadRounds(int season) {
        List<Round> roundList = new ArrayList<>();
        int champId = loadDChampId(season);
        int cha_champId = loadConChampId(season);
        try {
            System.out.println("Loading championship rounds...");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT * FROM round WHERE CHAMPID = ? AND CHA_CHAMPID = ?");
            st.setInt(1, champId);
            st.setInt(2, cha_champId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ROUNDID");
                int trackId = rs.getInt("TRACKID");
                Track track = TrackIO.loadTrack(trackId);
                String gp = rs.getString("GRANDPRIXNAME");
                Round tmp = new Round(track, gp);
                tmp.setRoundId(id);
                Race race = loadRace(tmp);
                Qualification quali = loadQuali(tmp);
                tmp.setRace(race);
                tmp.setQualification(quali);
                roundList.add(tmp);
            }
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roundList;
    }

    /**
     * Metoda koja dodaje rundu u bazu
     * @param r Runda koja se dodaje
     * @param champId šampionat za koji se dodaje (drivers)
     * @param cha_champId šampionat za koji se dodaje (constructors)
     */
    public static void addRound(Round r, int champId, int cha_champId) {
        try {
            System.out.println("Adding round");
            System.out.println("Round " + r.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("INSERT INTO round (TRACKID, CHAMPID, CHA_CHAMPID, GRANDPRIXNAME) VALUES (?,?,?,?)");
            st.setInt(1, r.getTrack().getTrackId());
            st.setInt(2, champId);
            st.setInt(3, cha_champId);
            st.setString(4, r.getGrandPrixName());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            addRace(getRoundIDByGPName(r.getGrandPrixName()), r.getRace());
            addQuali(getRoundIDByGPName(r.getGrandPrixName()), r.getQualification());
            System.out.println("Round added");
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda koja dodaje trku u bazu
     * @param roundId runda za koju se dodaje
     * @param r trka koja se dodaje
     */
    public static void addRace(int roundId, Race r) {
        try {
            System.out.println("Adding race");
            System.out.println("Race " + r.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("INSERT INTO race (ROUNDID, RACELAPS, COMPLETEDLAPS, RACEDATE) VALUES (?,?,?,?)");
            st.setInt(1, roundId);
            st.setInt(2, r.getRaceLaps());
            st.setInt(3, r.getCompletedLaps());
            st.setDate(4, (java.sql.Date) r.getRaceDate());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Race added");
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda koja dodaje kvalifikacije u bazu
     * @param roundId runda za koju se dodaje
     * @param q kvalifikacije koje se dodaju
     */
    public static void addQuali(int roundId, Qualification q) {
        try {
            System.out.println("Adding quali");
            System.out.println("Quali " + q.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("INSERT INTO qualification (ROUNDID, QUALITYPEID, QUALIDATE) VALUES (?,?,?)");
            st.setInt(1, roundId);
            st.setInt(2, q.getType().getValue());
            st.setDate(3, (java.sql.Date) q.getQualiDate());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Quali added");
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda koja ažurira rundu u bazi
     * @param r runda koja se ažurira
     */
    public static void updateRound(Round r) {
        try {
            System.out.println("Updating round");
            System.out.println("Round " + r.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("UPDATE round SET GRANDPRIXNAME = ? WHERE ROUNDID = ?");
            st.setString(1, r.getGrandPrixName());
            st.setInt(2, r.getRoundId());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            updateRace(r.getRace());
            updateQuali(r.getQualification());
            System.out.println("Round updated");
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda koja ažurira trku u bazi
     * @param r trka koja se ažurira
     */
    public static void updateRace(Race r) {
        try {
            System.out.println("Updating race");
            System.out.println("Race " + r.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("UPDATE race SET RACELAPS = ? , COMPLETEDLAPS = ?, RACEDATE = ? WHERE RACEID = ?");
            st.setInt(1, r.getRaceLaps());
            st.setInt(2, r.getCompletedLaps());
            st.setDate(3, (java.sql.Date) r.getRaceDate());
            st.setInt(4, r.getRaceId());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Race updated");
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda koja ažurira kvalifikacije u bazi
     * @param q kvalifikacije koje se ažuriraju
     */
    public static void updateQuali(Qualification q) {
        try {
            System.out.println("Updating quali");
            System.out.println("Quali " + q.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("UPDATE qualification SET QUALITYPEID = ? , QUALIDATE = ? WHERE QUALIID = ?");
            st.setInt(1, q.getType().getValue());
            st.setDate(2, (java.sql.Date) q.getQualiDate());
            st.setInt(3, q.getQualiID());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Quali updated");
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda koja briše rundu iz baze
     * @param r runda koja se briše
     */
    public static void deleteRound(Round r) {
        try {
            System.out.println("Deleting round");
            System.out.println("Round " + r.toString());
            deleteRace(r.getRace());
            deleteQuali(r.getQualification());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("DELETE FROM round WHERE ROUNDID = ?");
            st.setInt(1, r.getRoundId());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Round deleted");
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda koja briše trku iz baze
     * @param r trka koja se briše
     */
    public static void deleteRace(Race r) {
        try {
            System.out.println("Deleting race");
            System.out.println("Race " + r.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("DELETE FROM race WHERE RACEID = ?");
            st.setInt(1, r.getRaceId());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Race deleted");
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda koja briše kvalifikacije iz baze
     * @param q kvalifikacije koje se brišu
     */
    public static void deleteQuali(Qualification q) {
        try {
            System.out.println("Deleting quali");
            System.out.println("Quali " + q.toString());
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("DELETE FROM qualification WHERE QUALIID = ?");
            st.setInt(1, q.getQualiID());
            st.executeUpdate();
            st.close();
            DBUtil.con.close();
            System.out.println("Quali deleted");
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda koja učitava id šampionata konsturktora
     * @param season sezona za koju se učitava
     * @return id šampionata konsturktora
     */
    public static int loadConChampId(int season) {
        int cha_champId = 0;
        try {
            System.out.println("Loading cons championship...");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT CHAMPID FROM constructorchampionship WHERE SEASON = ?");
            st.setInt(1, season);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                cha_champId = rs.getInt("CHAMPID");
            }
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cha_champId;
    }
    
    /**
     * Metoda koja učitava šampionat konstruktora
     * @param season sezona za koju se učitava 
     * @return šampionat
     */
    public static ConstructorChampionship loadConChamp(int season) {
        ConstructorChampionship cc = new ConstructorChampionship();
        try {
            System.out.println("Loading cons championship...");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT CHAMPID FROM constructorchampionship WHERE SEASON = ?");
            st.setInt(1, season);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                cc.setIdChampionship(rs.getInt("CHAMPID"));
                cc.setSezona(season);
            }
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cc;
    }

    /**
     * Metoda koja učitava id šampionata vozača
     * @param season sezona za koju se učitava
     * @return id šampionata vozača
     */
    public static int loadDChampId(int season) {
        int champId = 0;
        try {
            System.out.println("Loading drivers championship...");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT CHAMPID FROM driverchampionship WHERE SEASON = ?");
            st.setInt(1, season);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                champId = rs.getInt("CHAMPID");
            }
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return champId;
    }
    
    /**
     * Metoda koja učitva šampionat vozača
     * @param season sezona za koju se učitava
     * @return šampionat
     */
    public static DriverChampionship loadDChamp(int season) {
        DriverChampionship dc = new DriverChampionship();
        try {
            System.out.println("Loading drivers championship...");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT CHAMPID FROM driverchampionship WHERE SEASON = ?");
            st.setInt(1, season);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                dc.setIdChampionship(rs.getInt("CHAMPID"));
                dc.setSezona(season);
            }
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dc;
    }

    /**
     * Metoda koja učitava trku za određenu rundu
     * @param r runda
     * @return race
     */
    public static Race loadRace(Round r) {
        Race race = new Race();
        try {
            System.out.println("Loading race...");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT * FROM race WHERE ROUNDID = ?");
            st.setInt(1, r.getRoundId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("RACEID");
                int completedLaps = rs.getInt("COMPLETEDLAPS");
                int raceLaps = rs.getInt("RACELAPS");
                Date raceDate = rs.getDate("RACEDATE");
                race.setRaceId(id);
                race.setRaceLaps(raceLaps);
                race.setCompletedLaps(completedLaps);
                race.setRaceDate(raceDate);

            }
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return race;
    }

    /**
     * Metoda koja učitava kvalifikacije za određenu rundu
     * @param r runda
     * @return  quali
     */
    public static Qualification loadQuali(Round r) {
        Qualification quali = new Qualification();
        try {
            System.out.println("Loading race...");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT * FROM qualification WHERE ROUNDID = ?");
            st.setInt(1, r.getRoundId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("QUALIID");
                int qualitype = rs.getInt("QUALITYPEID");
                Date date = rs.getDate("QUALIDATE");
                quali.setQualiID(id);
                quali.setQualiDate(date);
                quali.setType(QualificationType.valueOf(qualitype));
            }
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quali;
    }

    /**
     * Metoda koja učitava QualificationType iz baze kao string
     * @return string
     */
    public static String loadQualiTypeToString() {
        String types = "";
        try {
            System.out.println("Loading types...");
            DBUtil.dbConnect();
            String query = "SELECT * FROM qualificationtype";
            Statement st = (Statement) DBUtil.con.createStatement();

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                types += "ID: ";
                types += rs.getString("QUALITYPEID");
                types += "\tType: ";
                types += rs.getString("QUALITYPE");
                types += "\n";
            }
            st.close();
            DBUtil.con.close();
            System.out.println("Types loaded");

        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return types;
    }

    /**
     * Metoda koja pronalazi rundu(ID) u bazi prema grand prix name
     * @param gpname grand prix name
     * @return id runde int
     */
    public static int getRoundIDByGPName(String gpname) {
        // gp name je unique
        int r = 0;
        try {
            System.out.println("Loading round id...");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT ROUNDID FROM round WHERE GRANDPRIXNAME = ?");
            st.setString(1, gpname);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                r = rs.getInt("ROUNDID");
            }
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    /**
     * Metoda koja dodaje rezultate trke u bazu
     * @param r runda za koju se dodaje
     */
    public static void addRaceRes(Round r) {
        try {
            System.out.println("Saving results ...");
            DBUtil.dbConnect();
            Race race = r.getRace();
            LinkedHashMap<Driver, Integer> tmp = race.getResults();
            PreparedStatement st = DBUtil.con.prepareStatement("INSERT INTO raceresults (SLN, RACEID, POSITION) VALUES (?, ?, ?)");
            st.setInt(2, race.getRaceId());
            for (Driver driver : tmp.keySet()) {
                int pos = (int) tmp.get(driver);
                st.setInt(1, driver.getSLN());
                st.setInt(3, pos);
                st.executeUpdate();
            }
            System.out.println("saved all");
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda koja čuva poene u bazi za timove za određenu rundu/trku
     * @param r runda
     */
    public static void saveRacePointsTeam(Round r) {
        try {
            System.out.println("Saving results ...");
            DBUtil.dbConnect();
            Race race = r.getRace();
            LinkedHashMap<Team, Double> tmp = race.getAwardedTeamPoints();
            PreparedStatement st = DBUtil.con.prepareStatement("INSERT INTO awarded_team_points (race_id, teamcode, points ) VALUES (?, ?, ?)");
            st.setInt(1, race.getRaceId());
            for (Team team : tmp.keySet()) {
                double points = tmp.get(team);
                st.setInt(2, team.getTeamCode());
                st.setDouble(3, points);
                st.executeUpdate();
            }
            System.out.println("saved all");
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /**
     * Metoda koja u bazi čuva poene vozača za određenu trku/rundu
     * @param r runda
     */
    public static void saveRacePointsDriver(Round r) {
        try {
            System.out.println("Saving results ...");
            DBUtil.dbConnect();
            Race race = r.getRace();
            LinkedHashMap<Driver, Double> tmp = race.getAwardedPoints();
            PreparedStatement st = DBUtil.con.prepareStatement("INSERT INTO awarded_driver_points (race_id, sln, points ) VALUES (?, ?, ?)");
            st.setInt(1, race.getRaceId());
            for (Driver driver : tmp.keySet()) {
                double points = tmp.get(driver);
                st.setInt(2, driver.getSLN());
                st.setDouble(3, points);
                st.executeUpdate();
            }
            System.out.println("saved all");
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda koja učitava sve poene vozača za runde u šampionatu
     * @param champRounds runde u jednom šampionatu
     * @return linked hash map
     */
    public static LinkedHashMap<Driver, Double> loadDrChampionshipPoints(List<Round> champRounds) {
        LinkedHashMap<Driver, Double> tmp = new LinkedHashMap<>();
        try {
            System.out.println("Saving results ...");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT sln, sum(points) as poeni FROM awarded_driver_points group by sln");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                tmp.put(DriverIO.loadDriver(rs.getInt("sln")), rs.getDouble("poeni"));
            }
            System.out.println("saved all");
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }
    
    /**
     * Metoda koja čuva (ažurira red u tabeli) šampiona vozača za određeni šampionat
     * @param dc šampionat
     */
     public static void saveDChamp(DriverChampionship dc) {
        try {
            System.out.println("SAVING CHAMP...");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("UPDATE driverchampionship SET SLN = ? WHERE CHAMPID = ?");
            st.setInt(1, dc.getChampion().getSLN());
            st.setInt(2, dc.getIdChampionship());
           st.executeUpdate();
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     /**
      * Metoda koja čuva (ažurira red u tabeli) šampionata konstruktora za određeni šampionat
      * @param cc šampionat
      */
     public static void saveCChamp(ConstructorChampionship cc) {
        try {
            System.out.println("SAVING CHAMP...");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("UPDATE constructorchampionship SET TEAMCODE = ? WHERE CHAMPID = ?");
            st.setInt(1, cc.getChampion().getTeamCode());
            st.setInt(2, cc.getIdChampionship());
           st.executeUpdate();
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     /**
      * Metoda koja učitava poene vozača u određenom šampionatu (za runde tog šampionata)
      * @param champRounds runde
      * @return linked hash map
      */
     public static LinkedHashMap<Team, Double> loadCChampionshipPoints(List<Round> champRounds) {
        LinkedHashMap<Team, Double> tmp = new LinkedHashMap<>();
        try {
            System.out.println("Saving results ...");
            DBUtil.dbConnect();
            PreparedStatement st = DBUtil.con.prepareStatement("SELECT teamcode, sum(points) as poeni FROM awarded_team_points group by teamcode");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                tmp.put(TeamIO.loadTeam(rs.getInt("teamcode")), rs.getDouble("poeni"));
            }
            System.out.println("saved all");
            st.close();
            DBUtil.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrackIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

}
