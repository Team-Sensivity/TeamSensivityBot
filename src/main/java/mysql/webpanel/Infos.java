package mysql.webpanel;

import mysql.Connect;
import mysql.webpanel.CreateAccount;
import net.dv8tion.jda.api.entities.Member;

import java.sql.*;

public class Infos {

    public static void updateAvatar(String url, String id) {
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE users SET url = '" + url + "' WHERE ID = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBanner(String url, String id) {
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE users SET banner = '" + url + "' WHERE ID = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateName(String name, String id) {
        name = name.replace("'", "");
        name = name.replace(";", "");
        name = name.replace(">", "");
        name = name.replace("<", "");
        name = name.replace("\"", "");
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE users SET Username = '" + name + "' WHERE ID = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRole(Member m){
        String id = m.getId();

        String rolle = CreateAccount.getRole(m);

        rolle = rolle.replace("'", "");
        rolle = rolle.replace("<", "");
        rolle = rolle.replace(">", "");
        rolle = rolle.replace("\"", "");
        rolle = rolle.replace("'","");

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE users SET rolle = '" + rolle + "' WHERE ID = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean getUserHaveRole(String id, String rolle){
        boolean role = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM users";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("ID"))){
                    if(rolle.equals(rs.getString("rolle"))){
                       role = true;
                    }
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    public static void updateAll(String avatar, String username, String banner, String userid){
        username = username.replace("'", "");
        username = username.replace("<", "");
        username = username.replace(">", "");
        username = username.replace("\"", "");
        username = username.replace(";", "");


        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE users SET url = '" + avatar + "', username = '"+ username +"', banner = '" + banner +"' WHERE ID = '" + userid + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStatus(String id, String Status){
        Status = Status.replace("'", "");
        Status = Status.replace("<", "");
        Status = Status.replace(">", "");
        Status = Status.replace("\"", "");
        Status = Status.replace(";", "");

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE users SET status = '" + Status + "' WHERE ID = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}
