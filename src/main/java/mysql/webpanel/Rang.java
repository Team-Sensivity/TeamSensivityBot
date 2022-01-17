package mysql.webpanel;

import mysql.Connect;

import java.awt.*;
import java.sql.*;

public class Rang {

    public static void createRang(String name, String color, String id, int priority) {
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO Ranks (Name, Color, ID, priority) VALUES ('" + name + "', '" + color + "', '" + id + "', '" + priority + "')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeRang(String id){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM Ranks WHERE ID = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeColor(String color, String id){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE Ranks SET Color = '" + color + "' WHERE ID = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changePriority(String id, int priority){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE Ranks SET priority = '" + priority + "' WHERE ID = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean roleExist(String id){
        int exist = 0;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Ranks";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("ID"))){
                    exist = 1;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(exist != 0){
            return true;
        }else {
            return false;
        }
    }
}
