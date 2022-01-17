package mysql.webpanel;

import mysql.Connect;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class PunkteSystem {

    public static void uploadConnect(String id){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO CheckSystem (UserID) VALUES ('"+ id +"')");

            posted.executeUpdate();
            con.close();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    public static boolean userJoined(String id){
        boolean start = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM CheckSystem";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("UserID"))){
                    start = true;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return start;
    }

    public static void delete(String id){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM CheckSystem WHERE UserID = '" + id + "'");

            posted.executeUpdate();
            con.close();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    public static void time(String id){
        String start = " ";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM CheckSystem";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("UserID"))){
                    start = rs.getString("Time");
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

       long diff = findDifference(start, id);

        delete(id);
        uploadPunkte(diff, id);


    }

    static String getDay(Date d1) {
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        switch (c.get(Calendar.DAY_OF_WEEK)){
            case 1:
                return "so";
            case 2:
                return "mo";
            case 3:
                return "di";
            case 4:
                return "mi";
            case 5:
                return "do";
            case 6:
                return "fr";
            case 7:
                return "sa";
            default:
                return "Incorrect argument";
        }
    }

    static long findDifference(String start_date, String id) {
        LocalDateTime d3 = LocalDateTime.now(ZoneId.of("Europe/Berlin"));
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat fff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = null;
        try {
            d1 = fff.parse(start_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

            LocalDateTime d2 = LocalDateTime.parse(start_date, sdf);

            System.out.println(d2);
            System.out.println(d3);

            long difference_In_Minutes = Duration.between(d2, d3).toMinutes();
            System.out.println(difference_In_Minutes);
            if(difference_In_Minutes < 0){
                return 0;
            }else {
                String day = getDay(d1);
                upploadTime(day, difference_In_Minutes, id);
                return difference_In_Minutes;
            }
    }

    static void upploadTime(String day, long time, String id){

        if(userExist(id)){
            long zeit = time + getTime(id, day);
            try {
                Connection con = Connect.getConnection();

                PreparedStatement posted = con.prepareStatement("UPDATE Online SET " + day + " = '" + zeit + "' WHERE userid = '" + id + "'");

                posted.executeUpdate();
                con.close();

            } catch (
                    SQLException e) {
                e.printStackTrace();
            }
        }else {
            try {
                Connection con = Connect.getConnection();

                PreparedStatement posted = con.prepareStatement("INSERT INTO Online (userid, "+ day +") VALUES ('" + id + "', '" + time + "')");

                posted.executeUpdate();
                con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static int getTime(String id, String day){
        int time = 0;
        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Online";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("userid"))){
                    time = rs.getInt(day);
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return time;
    }

    static boolean userExist(String id){
        boolean exist = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Online";
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

    public static void uploadPunkte(long i, String id){

        long zahl = getPoints(id);
        i = i + zahl;
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE users SET punkte = '" + i + "' WHERE ID = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setPoints(long i, String id){

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE users SET punkte = '" + i + "' WHERE ID = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static void resetPoints(String id){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE users SET punkte = '0' WHERE ID = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getPoints(String id){
        int punkte = 0;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM users";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("ID"))){
                    punkte = rs.getInt("punkte");
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return punkte;
    }

    public static void uploadMessage(String id, int i){

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE users SET nachrichten = '" + i + "' WHERE ID = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static void chatPoints(String id){
        int nachrichten = 0;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM users";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("ID"))){
                    nachrichten = rs.getInt("nachrichten");
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(nachrichten < 9){
            nachrichten = nachrichten + 1;
            uploadMessage(id, nachrichten);
        }else {
            uploadMessage(id, 0);
            uploadPunkte(1, id);
        }
    }
}
