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
public enum TrackType {
    Race_Cirtcuit(1), Street_Circuit(2), Road_Cirtcuit(3), Undefined(0);
    
    private int value;
    private static Map map = new HashMap<>();

    private TrackType(int value) {
        this.value = value;
    }

    static {
        for (TrackType trackType : TrackType.values()) {
            map.put(trackType.value, trackType);
        }
    }

    public static TrackType valueOf(int trackType) {
        return (TrackType) map.get(trackType);
    }

    public int getValue() {
        return value;
    }
}
