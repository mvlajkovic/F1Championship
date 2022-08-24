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
public enum QualificationType {
    STANDARD(1), SPRINT(2);
    
    private int value;
    private static Map map = new HashMap<>();

    private QualificationType(int value) {
        this.value = value;
    }

    static {
        for (QualificationType qualiType : QualificationType.values()) {
            map.put(qualiType.value, qualiType);
        }
    }

    public static QualificationType valueOf(int qualiType) {
        return (QualificationType) map.get(qualiType);
    }

    public int getValue() {
        return value;
    }
}
