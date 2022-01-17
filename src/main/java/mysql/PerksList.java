package mysql;

import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PerksList {

    public static List<String> getSurvPerksInfos(){
        String satzS = " ";
        String satzK = " ";

        List<String> satz = new ArrayList<>();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Perks";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(rs.getString("Rolle").equalsIgnoreCase("Survivor")) {
                    satzS = satzS + "**" + rs.getString("Name") + "** von " + rs.getString("Charakter") + "\n";
                }else {
                    satzK = satzK + "**" + rs.getString("Name") + "** von " + rs.getString("Charakter") + "\n";
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            satz.add(satzS);
            satz.add(satzK);
        return satz;
    }

    public static int getAnzahl(){
        int i = 0;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Perks";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                i++;
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    public static List<String> getPerkInfos(String Name){
        List<String> infos = new ArrayList<>();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Perks";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(Name.equalsIgnoreCase(rs.getString("Name"))){
                    infos.add(rs.getString("Charakter"));
                    infos.add(rs.getString("url"));
                    infos.add(rs.getString("Name"));
                    infos.add(rs.getString("Beschreibung"));
                }else if (Name.equalsIgnoreCase(rs.getString("Name2"))){
                    infos.add(rs.getString("Charakter"));
                    infos.add(rs.getString("url"));
                    infos.add(rs.getString("Name2"));
                    infos.add(rs.getString("Beschreibung"));
                }else if(rs.getString("Name").toLowerCase().contains(Name.toLowerCase())){
                    infos.add(rs.getString("Charakter"));
                    infos.add(rs.getString("url"));
                    infos.add(rs.getString("Name"));
                    infos.add(rs.getString("Beschreibung"));
                }else if(rs.getString("Name2").toLowerCase().contains(Name.toLowerCase())){
                    infos.add(rs.getString("Charakter"));
                    infos.add(rs.getString("url"));
                    infos.add(rs.getString("Name2"));
                    infos.add(rs.getString("Beschreibung"));
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return infos;
    }
}
