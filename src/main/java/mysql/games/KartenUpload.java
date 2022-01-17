package mysql.games;

import mysql.Connect;
import net.dv8tion.jda.api.entities.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KartenUpload {

    public static void upload(String id, String url, int gameid){

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO Karten (userid, url, gameid) VALUES ('"+ id + "', '"+ url +"' , '"+ gameid +"')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getLastCard(String id, int gameid){
        String url = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Karten ORDER BY id ASC";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("userid")) && gameid == rs.getInt("gameid")){
                    url = rs.getString("url");
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static void clearTable(){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM Karten");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void uploadDeck(List<String> cards, String userid){
      for (String c: cards){
          try {
              Connection con = Connect.getConnection();

              PreparedStatement posted = con.prepareStatement("INSERT INTO Deck (userid, card) VALUES ('"+ userid + "', '"+ c +"')");

              posted.executeUpdate();
              con.close();

          } catch (SQLException e) {
              e.printStackTrace();
          }
      }
    }

    public static void clearTable2(){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM Deck");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCards(String userid){
        int i = 0;
        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Deck";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(userid.equals(rs.getString("userid"))){
                    i++;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    public static List<String> getCardArt(String userid){
        List<String> card = new ArrayList<>();
        String karte = "";
        char eins;
        char zwei;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Deck";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(userid.equals(rs.getString("userid"))){
                  karte = rs.getString("card");

                    karte = karte.substring(61);

                    eins = karte.charAt(0);
                    zwei = karte.charAt(1);

                    card.add("" + eins + zwei);
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return card;
    }

    public static List<String> getKarts(String userid){
        List<String> card = new ArrayList<>();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Deck";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(userid.equals(rs.getString("userid"))){
                    card.add(rs.getString("card"));
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return card;
    }
}
