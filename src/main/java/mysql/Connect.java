package mysql;

import geheim.Passwort;
import main.Start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    static String url = "jdbc:mysql://45.88.108.231:3306/u8146-785_discord";
    static String user = "michel_929";
    static String pass = Passwort.getDatabasePassword();

    public static Connection getConnection(){
        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            return con;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
