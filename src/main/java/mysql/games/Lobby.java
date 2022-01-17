package mysql.games;

import mysql.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Lobby {

    public static boolean lobbyExist(int gameid){
        boolean exist = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Lobby";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(gameid == rs.getInt("gameid") && rs.getInt("voll") == 0){
                    exist = true;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static String getLobbyId(int gameid){
        String text = "";
        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Lobby";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(gameid == rs.getInt("gameid") && rs.getInt("voll") == 0){
                    text = rs.getString("lobbyid");
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return text;
    }

    public static String getLobbyId(String userid){
        String text = "";
        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Lobby";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(userid.equals(rs.getString("userid"))){
                    text = rs.getString("lobbyid");
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return text;
    }

    public static void createLobby(int gameid, String userid, String lobbyid, String messageid){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO Lobby (lobbyid, userid, gameid, messageid) VALUES ('"+ lobbyid + "', '"+ userid +"' , '"+ gameid +"', '" + messageid + "')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean userExist(String id){
        boolean exist = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Lobby";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("userid"))){
                    exist = true;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static List<String> leaveLobby(String id){
        List<String> infos = new ArrayList<>();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Lobby";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("userid"))){
                    infos.add(rs.getString("lobbyid"));

                    if(rs.getInt("gameid") == 1){
                        infos.add("MAUMAU");
                    }else if(rs.getInt("gameid") == 2){
                        infos.add("POKER");
                    }else if(rs.getInt("gameid") == 3){
                        infos.add("BLACKJACK");
                    }else if(rs.getInt("gameid") == 4){
                        infos.add("ROULETTE");
                    }
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM Lobby WHERE userid = '" + id +"'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return infos;
    }

    public static List<String> getMessageId(String lobbyid){
        List<String> messages = new ArrayList<>();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Lobby";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(lobbyid.equals(rs.getString("lobbyid"))){
                    messages.add(rs.getString("messageid"));
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public static List<String> getUserId(String lobbyid){
        List<String> channel = new ArrayList<>();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Lobby";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(lobbyid.equals(rs.getString("lobbyid"))){
                    channel.add(rs.getString("userid"));
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return channel;
    }

    public static int getSpieler(String lobbyid){
        int spieler = 0;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Lobby";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(lobbyid.equals(rs.getString("lobbyid"))){
                    spieler++;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spieler;
    }

    public static void clearTable(){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM Lobby");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateVoll(String id){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE Lobby SET voll = 1 WHERE userid = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isReady(String lobbyid){
        boolean ready = false;
        int i = 0;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Lobby";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(lobbyid.equals(rs.getString("lobbyid"))){
                    if(rs.getInt("voll") == 1){
                        i++;
                    }
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(getSpieler(lobbyid) == i){
            ready = true;
        }

        return ready;
    }

    public static void updateMessageId(String userid, String messageid){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE Lobby SET messageid = '"+ messageid +"' WHERE userid = '" + userid + "'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAktiv(String userid, int number){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE Lobby SET aktiv = '"+ number +"' WHERE userid = '" + userid + "'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getAktiv(String userid){
        int aktiv = 0;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Lobby";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(userid.equals(rs.getString("userid"))){
                    aktiv = rs.getInt("aktiv");
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aktiv;
    }

    public static void updateDeckId(String userid, String messageid){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE Lobby SET kartendeck = '"+ messageid +"' WHERE userid = '" + userid + "'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void reinfolge(String lobbyid){
        List<String> user = getUserId(lobbyid);
        int i = 1;
        for (String u: user) {

            try {
                Connection con = Connect.getConnection();

                PreparedStatement posted = con.prepareStatement("UPDATE Lobby SET voll = "+ i +" WHERE userid = '" + u + "'");

                posted.executeUpdate();
                con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            i++;
        }
    }

    public static String getUserID(String lobbyid, int voll){
        String use = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Lobby";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(lobbyid.equals(rs.getString("lobbyid")) && voll == rs.getInt("voll")){
                    use = rs.getString("userid");
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return use;
    }

    public static String getMessage(String userid){
        String message = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Lobby";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(userid.equals(rs.getString("userid"))){
                    message = rs.getString("messageid");
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return message;
    }

    public static List<String> getDeckId(String lobbyid){
        List<String> messages = new ArrayList<>();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Lobby";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(lobbyid.equals(rs.getString("lobbyid"))){
                    messages.add(rs.getString("kartendeck"));
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }
}
