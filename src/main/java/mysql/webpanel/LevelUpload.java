package mysql.webpanel;

import mysql.Connect;

import java.sql.*;

public class LevelUpload {

    public static void createLevel(int level, int xp) {
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO Level (level, xp) VALUES ('" + level + "', '" + xp + "')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean levelExist(int level){
        boolean exist = false;
        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Level";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(level == rs.getInt("level")){
                    exist = true;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static boolean xpHigh(int xp, int levels){
        boolean exist = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Level";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                int i = levels - 1;
                if(i == rs.getInt("level")){
                    if(xp > rs.getInt("xp")){
                        exist = true;
                    }
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }
}
