package mysql.webpanel;

import main.Start;
import mysql.Connect;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateAccount {

    public static String create(String username, String id, String url, String role, String banner){

            username = username.replace("'", "");
            username = username.replace("<", "");
            username = username.replace(">", "");
            username = username.replace("\"", "");
            username = username.replace(";", "");

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO users (Username, ID, url, rolle, banner) VALUES ('"+ username +"', '"+ id +"', '" + url + "', '" + role + "', '" + banner + "')");

            posted.executeUpdate();
            con.close();

            uploadRollen(id);
            return "**Account wurde erstellt** (Lösch am besten die Nachricht, welche das Passwort beinhaltet)";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Fehler";
        }
    }

    public static List<String> getRollen(){

        List<String> rollen = new ArrayList<>();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Ranks ORDER BY priority DESC";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                rollen.add(rs.getString("ID"));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rollen;
    }

    public static void uploadRollen(String id){
        List<Role> rollen = Start.INSTANCE.getApi().getGuilds().get(0).getMemberById(id).getRoles();
        deleteRollen(id);

        try {
            Connection con = Connect.getConnection();

            for (Role r: rollen) {
                PreparedStatement posted = con.prepareStatement("INSERT INTO rollen (username, rolle) VALUES ('"+ id +"', '"+ r.getId() +"')");

                posted.executeUpdate();
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRollen(String id){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM rollen WHERE username = '" + id +"'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getRole(String id){

        List<Role> roles = Start.INSTANCE.getApi().getGuilds().get(0).getMemberById(id).getRoles();
        List<String> rollen = getRollen();

        for(int i = 0; i < roles.size(); i++){
            String name = roles.get(i).getId();
            for (int x = 0; x < rollen.size(); x++) {
                if (name.equals(rollen.get(x))) {
                    return name;
                }
            }
        }

        return "Team Sensivity";
    }

    public static String getRole(Member m){

        List<Role> roles = m.getRoles();
        List<String> rollen = getRollen();

        for(int i = 0; i < roles.size(); i++){
            String name = roles.get(i).getId();
            for (int x = 0; x < rollen.size(); x++) {
                if (name.equals(rollen.get(x))) {
                    return name;
                }
            }
        }

        return "Team Sensivity";
    }

    public static void delete(String id) {
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM users WHERE ID = '" + id +"'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}