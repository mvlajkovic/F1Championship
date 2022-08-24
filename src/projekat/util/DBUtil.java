/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milica
 */
public class DBUtil {
    public static java.sql.Connection con = null;
    private static String url = "jdbc:mysql://localhost/formula1";
    private static String user = "root";
    private static String pass = "";
    
    public static void dbConnect(){
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            System.out.println("No connection with database");
        }
    }
}
