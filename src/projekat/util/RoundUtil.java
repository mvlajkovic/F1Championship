/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import projekat.data.Driver;
import projekat.data.DriverRole;
import projekat.data.Team;
import projekat.exceptions.CountryCodeException;
import projekat.exceptions.DriverAlreadyExistsException;
import projekat.exceptions.InvalidCarNumberException;
import projekat.exceptions.InvalidTeamCodeException;
import projekat.exceptions.NonExistentCarNumber;
import projekat.exceptions.NonExistentDriverRoleException;
import projekat.exceptions.NonExistentTeamCodeException;
import projekat.exceptions.takenCarNumberException;
import projekat.io.DriverIO;

/**
 *
 * @author Milica
 */
public class RoundUtil {

    public static Driver searchByCarNumber(List<Driver> driverList, int number) throws NonExistentCarNumber, InvalidCarNumberException {

        try {
            if (number <= 0 || number >= 100) {
                throw new InvalidCarNumberException("Invalid car number! Try again. Number must be between 1 and 99");
            }
            int numOfDriv = 0;
            for (Driver driver : driverList) {
                if (number == driver.getCarNumber()) {
                    return driver;
                } else {
                    numOfDriv += 1;
                }
            }
            if (numOfDriv == driverList.size()) {
                throw new NonExistentCarNumber("Driver doesn't exists. Try again.");
            }

        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("You have to input a number between 1-99");
        }
        return null;
    }

    public static String printDriversPoints(LinkedHashMap<Driver, Double> points) {
        LinkedHashMap<Driver, Double> tmp = GeneralUtil.sortByDriverPoints(points);
        int position = 1;
        String str = "";
        for (Driver driver : tmp.keySet()) {
            str += position + ". " + driver.toString() + " - " + tmp.get(driver) + "\n";
            position++;
        }
        return str;

    }
    
    public static String printTeamsPoints(LinkedHashMap<Team, Double> points) {
        LinkedHashMap<Team, Double> tmp = GeneralUtil.sortByTeamPoints(points);
        int position = 1;
        String str = "";
        for (Team team : tmp.keySet()) {
            str += position + ". " + team.toString() + " - " + tmp.get(team) + "\n";
            position++;
        }
        return str;
    }
    
}
