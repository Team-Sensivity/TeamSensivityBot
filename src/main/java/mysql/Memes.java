package mysql;

import net.dv8tion.jda.api.entities.Message;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Memes {
    public static void uploadMeme(Message message) {
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO Memes (messageid, url) VALUES ('" + message.getId() + "','" + message.getAttachments().get(0).getUrl() + "')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String bestMeme(){
        String meme = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Memes ORDER BY likes DESC LIMIT 1";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
              meme = rs.getString("messageid");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meme;
    }

    public static String MemeOfTheMonth(){
        String meme = "ff";
        LocalDateTime d3 = LocalDateTime.now(ZoneId.of("Europe/Berlin"));
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime d1 = null;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Memes ORDER BY likes DESC";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            boolean exist = false;

            while (rs.next()) {
                d1 = LocalDateTime.parse(rs.getString("time"), sdf);

                if(d1.getMonth() == d3.getMonth() && !exist){
                    meme = rs.getString("messageid");
                    exist = true;
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meme;
    }

    public static boolean isMeme(String message){
        boolean likes = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Memes";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(message.equals(rs.getString("messageid"))){
                    likes = true;
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return likes;
    }

    public static int getLikes(String message){
        int likes = 0;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Memes";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(message.equals(rs.getString("messageid"))){
                  likes = rs.getInt("likes");
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return likes;
    }

    public static int getDislikes(String message){
        int likes = 0;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Memes";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(message.equals(rs.getString("messageid"))){
                    likes = rs.getInt("dislikes");
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return likes;
    }

    public static void removelike(String message){
        int likes = getLikes(message) - 1;
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE Memes SET likes = '" + likes + "' WHERE messageid = '"+ message + "'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removedislike(String message){
        int likes = getDislikes(message) - 1;
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE Memes SET dislikes = '" + likes + "' WHERE messageid = '"+ message + "'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void like(String message){
        int likes = getLikes(message) + 1;
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE Memes SET likes = '" + likes + "' WHERE messageid = '"+ message + "'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dislike(String message){
        int likes = getDislikes(message) + 1;
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE Memes SET dislikes = '" + likes + "' WHERE messageid = '"+ message + "'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMeme(String message){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM Memes WHERE messageid = '" + message + "'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
