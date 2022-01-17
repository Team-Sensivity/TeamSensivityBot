package mysql;

import java.sql.*;

public class TemporereChannel {
    public static void createChannel(String voice, String text, String cat) {
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO maxIdee (textChannel, voiceChannel, cat) VALUES ('" + text + "', '" + voice + "', '"+ cat +"')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getTextChannel(String voice){
        String exist = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM maxIdee";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(voice.equals(rs.getString("voiceChannel"))){
                    exist = rs.getString("textChannel");
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static String getVoiceChannel(String cat){
        String exist = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM maxIdee";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(cat.equals(rs.getString("cat"))){
                    exist = rs.getString("voiceChannel");
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static String getTextWithCat(String cat){
        String exist = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM maxIdee";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(cat.equals(rs.getString("cat"))){
                    exist = rs.getString("textChannel");
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static String getCat(String voice){
        String exist = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM maxIdee";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(voice.equals(rs.getString("voiceChannel"))){
                    exist = rs.getString("cat");
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static boolean isExist(String voice){
        boolean exist = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM maxIdee";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(voice.equals(rs.getString("voiceChannel"))){
                    exist = true;
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static boolean isExistText(String text){
        boolean exist = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM maxIdee";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(text.equals(rs.getString("textChannel"))){
                    exist = true;
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static void saveChat(String author, String nachricht, String user, int number , String MessId){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO ChannelSave (author, userid, text, nummer, textid) VALUES ('"+ author +"', '"+ user +"', '"+ nachricht +"', '"+ number +"', '" + MessId + "')");

            posted.executeUpdate();
            con.close();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    public static boolean textExist(String id){
        boolean exist = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM ChannelSave";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("textid"))){
                    exist = true;
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static int getChatNummer(String user){
        int nummer = 0;
        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM ChannelSave";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(user.equals(rs.getString("userid"))){
                    if(nummer < rs.getInt("nummer")){
                        nummer = rs.getInt("nummer");
                    }
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nummer;
    }

    public static boolean chatidExist(String id){
        boolean exist = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM ChannelSave";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("channelid"))){
                    exist = true;
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static boolean isExistCat(String cat){
        boolean exist = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM maxIdee";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(cat.equals(rs.getString("cat"))){
                    exist = true;
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public static void removeChannel(String voice){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM maxIdee WHERE voiceChannel = '" + voice + "'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeChannelCat(String cat){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM maxIdee WHERE cat = '" + cat + "'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
