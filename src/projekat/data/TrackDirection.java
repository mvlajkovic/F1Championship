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
public enum TrackDirection {
    Clockwise(1), Anti_Clockwise(2), Undefined(0);
    private int value;
    private static Map map = new HashMap<>();

    private TrackDirection(int value) {
        this.value = value;
    }

    static {
        for (TrackDirection trackDir : TrackDirection.values()) {
            map.put(trackDir.value, trackDir);
        }
    }

    public static TrackDirection valueOf(int trackDir) {
        return (TrackDirection) map.get(trackDir);
    }

    public int getValue() {
        return value;
    }
}
