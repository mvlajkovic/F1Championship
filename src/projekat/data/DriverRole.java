/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package projekat.data;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Milica
 */
public enum DriverRole {
    Regular (1), 
    Reserve (2), 
    Undefined (0);
    private int value;
    private static Map map = new HashMap<>();

    private DriverRole(int value) {
        this.value = value;
    }

    static {
        for (DriverRole driverRole : DriverRole.values()) {
            map.put(driverRole.value, driverRole);
        }
    }

    public static DriverRole valueOf(int driverRole) {
        return (DriverRole) map.get(driverRole);
    }

    public int getValue() {
        return value;
    }
}
