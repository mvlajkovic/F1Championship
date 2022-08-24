/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.data;

/**
 *
 * @author Milica
 */
public class Driver {
    private int SLN;
    private String name;
    private String lastName;
    private int carNumber;
    //private int countryCode;
    private int teamCode;
    private DriverRole role = DriverRole.Undefined;

    public Driver() {
    }

    public Driver(String name, String lastName, int carNumber, int teamCode) {
        this.name = name;
        this.lastName = lastName;
        this.carNumber = carNumber;
       
        this.teamCode = teamCode;
    }

    public Driver(int SLN, String name, String lastName, int carNumber, int teamCode) {
        this.SLN = SLN;
        this.name = name;
        this.lastName = lastName;
        this.carNumber = carNumber;
       this.teamCode = teamCode;
    }
    
    
    public Driver(Driver d) {
        this.name = d.name;
        this.lastName = d.lastName;
        this.carNumber = d.carNumber;
        this.teamCode = d.teamCode;
    } 
    
    @Override
    public String toString() {
        return name + " " + lastName + " " + carNumber + " " + teamCode;
    }

    public int getSLN() {
        return SLN;
    }

    public void setSLN(int SLN) {
        this.SLN = SLN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }


    public int getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(int teamCode) {
        this.teamCode = teamCode;
    }

    public DriverRole getRole() {
        return role;
    }

    public void setRole(DriverRole role) {
        this.role = role;
    }
    
    
    
}
