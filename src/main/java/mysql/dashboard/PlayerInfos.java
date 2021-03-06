package mysql.dashboard;

import mysql.Connect;

import java.sql.*;

public class PlayerInfos {
    public static void createAccount(String id, String username, String pb, String banner){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO users (discord_id, discord_username, discord_bild, discord_banner, website_url) VALUES ('"+ id + "', '"+ username +"' , '"+ pb +"', '"+ banner +"', '" + id + "')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isExist(String id, String row, String table){
        boolean exist = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM " + table;
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString(row))){
                    exist = true;
                }

            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static void updatePlayerInfos(String discord_id, String row, String newInfos){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE users SET " + row + " = '" + newInfos + "' WHERE discord_id = '" + discord_id + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertRole(String id, String role){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO rollen (discord_id, discorde_role) VALUES ('"+ id + "', '"+ role +"')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
