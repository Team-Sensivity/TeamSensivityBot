package mysql.dashboard;

import mysql.Connect;

import java.sql.*;

public class PlayerInfos {
    public static void createAccount(String id, String username, String pb, String banner){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO users (discord_id, discord_username, discord_bild, discord_banner) VALUES ('"+ id + "', '"+ username +"' , '"+ pb +"', '"+ banner +"')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean accountExist(String id){
        boolean exist = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM users";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("discord_id"))){
                    exist = true;
                }

            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }
}
