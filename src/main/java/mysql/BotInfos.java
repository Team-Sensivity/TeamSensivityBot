package mysql;

import java.sql.*;

public class BotInfos {
    public static String getChannel(){
        String exist = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM botInfos";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                exist = rs.getString("createChannel");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static String getMinecraft(){
        String exist = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM botInfos";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                exist = rs.getString("minecraft");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static String getStatusMessage(){
        String exist = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM botInfos";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                exist = rs.getString("statusMessage");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static String getStatusChannel(){
        String exist = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM botInfos";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                exist = rs.getString("statusChannel");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static String getMemeChannel(){
        String exist = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM botInfos";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                exist = rs.getString("memeChannel");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static String getStandardRole(){
        String exist = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM botInfos";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                exist = rs.getString("standartRole");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static void updateChannel(String id, String oldChannel){

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE botInfos SET createChannel = '" + id + "' WHERE createChannel = '" + oldChannel + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStatusChannel(String id, String oldChannel){

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE botInfos SET statusChannel = '" + id + "' WHERE statusChannel = '" + oldChannel + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStatusMessage(String id, String oldChannel){

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE botInfos SET statusMessage = '" + id + "' WHERE statusMessage = '" + oldChannel + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateMinecraft(String minecraft){

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE botInfos SET minecraft = '" + minecraft + "' WHERE newminecraft = '" + minecraft + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}
