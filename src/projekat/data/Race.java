/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import projekat.exceptions.InvalidCarNumberException;
import projekat.exceptions.InvalidDriverInputException;
import projekat.exceptions.NonExistentCarNumber;
import projekat.util.RoundUtil;

/**
 *
 * @author Milica
 */
public class Race implements Driven {

    private int raceId;
    private int completedLaps;
    private int raceLaps;
    Date raceDate;
    private LinkedHashMap<Driver, Integer> results = new LinkedHashMap<>();
    private LinkedHashMap<Driver, Double> awardedPoints = new LinkedHashMap<>();
    private LinkedHashMap<Team, Double> awardedTeamPoints = new LinkedHashMap<>();

    public Race() {
    }

    public Race(int raceLaps, int completedLaps, Date raceDate) {
        this.raceLaps = raceLaps;
        this.completedLaps = completedLaps;
        this.raceDate = raceDate;
    }

    public Race(int raceId, int completedLaps, int raceLaps, Date raceDate) {
        this.raceId = raceId;
        this.completedLaps = completedLaps;
        this.raceLaps = raceLaps;
        this.raceDate = raceDate;
    }

    public Race(Race r) {
        this.raceLaps = r.raceLaps;
        this.completedLaps = r.completedLaps;
        this.raceDate = r.raceDate;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public Date getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(Date date) {
        this.raceDate = date;
    }

    public int getCompletedLaps() {
        return completedLaps;
    }

    public void setCompletedLaps(int completedLaps) {
        this.completedLaps = completedLaps;
    }

    public LinkedHashMap<Driver, Integer> getResults() {
        return results;
    }

    public void setResults(LinkedHashMap<Driver, Integer> results) {
        this.results = results;
    }

    public LinkedHashMap<Driver, Double> getAwardedPoints() {
        return awardedPoints;
    }

    public void setAwardedPoints(LinkedHashMap<Driver, Double> awardedPoints) {
        this.awardedPoints = awardedPoints;
    }

    public int getRaceLaps() {
        return raceLaps;
    }

    public void setRaceLaps(int raceLaps) {
        this.raceLaps = raceLaps;
    }

    public LinkedHashMap<Team, Double> getAwardedTeamPoints() {
        return awardedTeamPoints;
    }

    public void setAwardedTeamPoints(LinkedHashMap<Team, Double> awardedTeamPoints) {
        this.awardedTeamPoints = awardedTeamPoints;
    }

    @Override
    public String toString() {
        return "Race: " + "completed laps " + completedLaps + " of " + raceLaps + " total race laps, on " + raceDate;
        //return "Race: " + "completed laps " + completedLaps + " of " + raceLaps + " total race laps, on " + raceDate+ "\n Race results \n" + results + "\n Race awardedPoints \n" + awardedPoints;
    }

    /**
     * Omogućava korisniku da unese rezultate
     *
     * @param driverList - lista svih vozača
     */
    @Override
    public void addResults(List<Driver> driverList) {
        List<Integer> carNum = new ArrayList<>();
        carNum.add(0);
        for (int position = 1; position <= 10; position++) {
            boolean bool = true;
            int carNumber = 0;
            while (bool) {
                try {
                    TextInputDialog dialog = new TextInputDialog("");
                    dialog.setTitle("Race result input");
                    String header = "Input car number for position #" + position;
                    dialog.setHeaderText(header);
                    dialog.setContentText("Please enter car number:");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        carNumber = Integer.parseInt(result.get());
                        Driver key = RoundUtil.searchByCarNumber(driverList, carNumber);
                        int tmp = 0;
                        for (Integer integer : carNum) {
                            if (integer == key.getCarNumber()) {
                                tmp += 1;
                            }
                        }

                        if (tmp > 0) {
                            throw new InvalidDriverInputException("Chosen driver is already entered.");
                        } else {
                            carNum.add(key.getCarNumber());
                            this.results.put(key, position);
                            bool = false;
                        }
                    }

                } catch (InvalidDriverInputException e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("InvalidDriverInputException");
                    alert.setContentText("Chosen driver is already entered.");
                    alert.showAndWait();
                    System.out.println(e.getMessage());
                } catch (NonExistentCarNumber ex) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("NonExistentCarNumber");
                    alert.setContentText("Driver doesn't exists. Try again.");
                    alert.showAndWait();
                    System.out.println(ex.getMessage());
                } catch (InvalidCarNumberException ex) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("InvalidCarNumberException");
                    alert.setContentText("Invalid car number! Try again. Number must be between 1 and 99");
                    alert.showAndWait();
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    /**
     * Obračunava poene na osnou rezultata. Poeni zavise od % kompletiranih
     * krugova trke u odnosu na predviđen broj krugova
     *
     * @param results - lista rezultata za koje se obračunavaju poeni
     */
    @Override
    public void calculateDriverPoints(LinkedHashMap<Driver, Integer> results) {
//        this.completedLaps = RoundUtil.inputCompletedLaps();
        if (this.completedLaps < (this.raceLaps * 0.7)) {
            for (Driver d : results.keySet()) {
                switch (results.get(d)) {
                    case 1:
                        this.awardedPoints.put(d, (double) 12.5);
                        break;
                    case 2:
                        this.awardedPoints.put(d, (double) 9);
                        break;
                    case 3:
                        this.awardedPoints.put(d, (double) 7.5);
                        break;
                    case 4:
                        this.awardedPoints.put(d, (double) 6);
                        break;
                    case 5:
                        this.awardedPoints.put(d, (double) 5);
                        break;
                    case 6:
                        this.awardedPoints.put(d, (double) 4);
                        break;
                    case 7:
                        this.awardedPoints.put(d, (double) 3);
                        break;
                    case 8:
                        this.awardedPoints.put(d, (double) 2);
                        break;
                    case 9:
                        this.awardedPoints.put(d, (double) 1);
                        break;
                    case 10:
                        this.awardedPoints.put(d, (double) 0.5);
                        break;
                    default:
                        this.awardedPoints.put(d, (double) 0);
                        break;
                }
            }
        } else {
            for (Driver d : results.keySet()) {
                switch (results.get(d)) {
                    case 1:
                        this.awardedPoints.put(d, (double) 25);
                        break;
                    case 2:
                        this.awardedPoints.put(d, (double) 18);
                        break;
                    case 3:
                        this.awardedPoints.put(d, (double) 15);
                        break;
                    case 4:
                        this.awardedPoints.put(d, (double) 12);
                        break;
                    case 5:
                        this.awardedPoints.put(d, (double) 10);
                        break;
                    case 6:
                        this.awardedPoints.put(d, (double) 8);
                        break;
                    case 7:
                        this.awardedPoints.put(d, (double) 6);
                        break;
                    case 8:
                        this.awardedPoints.put(d, (double) 4);
                        break;
                    case 9:
                        this.awardedPoints.put(d, (double) 2);
                        break;
                    case 10:
                        this.awardedPoints.put(d, (double) 1);
                        break;
                    default:
                        this.awardedPoints.put(d, (double) 0);
                        break;
                }
            }
        }
    }

    /**
     * Obračunava poene timova na osnovu poena vozača
     *
     * @param points - lista poena vozača
     * @param teamList - lista svih timova
     */
    @Override
    public void calculateTeamPoints(LinkedHashMap<Driver, Double> points, List<Team> teamList) {
        for (Team team : teamList) {
            double result = 0;
            for (Driver d : points.keySet()) {
                if (d.getTeamCode() == team.getTeamCode()) {
                    result += points.get(d);
                }
            }
            this.awardedTeamPoints.put(team, result);
        }
    }

    //Ostaviti samo print to string
    /**
     * Štampa poene vozača
     */
    @Override
    public String printPoints() {
        String print = "RACE Drivers results" + this.raceDate.toString() + " laps completed: " + this.completedLaps + "/" + this.raceLaps + "\n";
        print += RoundUtil.printDriversPoints(this.awardedPoints);
        return print;
    }

    /**
     * Štampa poene vozača u fajl
     */


    /**
     * Štampa poene timova
     */
    @Override
    public String printTeamResult() {
        String print = "RACE Teams results" + this.raceDate.toString() + " laps completed: " + this.completedLaps + "/" + this.raceLaps + "\n";
        print += RoundUtil.printTeamsPoints(this.awardedTeamPoints);
        return print;
    }



}
