/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Milica
 */
public class Team {
    private String name;
    private int teamCode;
    private String location;
    private List<Driver> driverList = new ArrayList<>();

    public Team() {
    }

    public Team(String name, int teamCode, String location) {
        this.name = name;
        this.teamCode = teamCode;
        this.location = location;
    }
    
    public Team(Team t){
        this.name=t.name;
        this.teamCode=t.teamCode;
        this.location=t.location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(int teamCode) {
        this.teamCode = teamCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }
    
    public void addDriverToList(Driver d){
        this.driverList.add(d);
    }

    @Override
    public String toString() {
        return name + " " + teamCode + " " + location;
    }
    
    
    
}
