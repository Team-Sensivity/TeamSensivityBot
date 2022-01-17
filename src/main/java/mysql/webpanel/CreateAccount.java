package mysql.webpanel;

import main.Start;
import mysql.Connect;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateAccount {

    public static String create(String username, String id, String url, String role){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO users (Username, ID, url, rolle) VALUES ('"+ username +"', '"+ id +"', '" + url + "', '" + role + "')");

            posted.executeUpdate();
            con.close();
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
}