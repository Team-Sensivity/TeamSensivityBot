package mysql.webpanel;

import mysql.Connect;

import java.sql.*;

public class TokenErstellen {

    public static void setToken(String token, String id, int aufrufe){

        testToken(id);

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO Token (token, userid, aufrufe) VALUES ('"+ token + "', '"+ id +"', '" + aufrufe + "')");

            posted.executeUpdate();

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testToken(String id){
        int exist = 0;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Token";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("userid"))){
                    exist = 1;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(exist != 0){
            deleteToken(id);
        }
    }

    public static void deleteToken(String id){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM Token WHERE userid = '" + id +"'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isRegister(String id){
        int exist = 0;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM users";
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
