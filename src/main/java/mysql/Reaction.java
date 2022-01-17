package mysql;

import net.dv8tion.jda.api.entities.Message;

import java.sql.*;

public class Reaction {

    public static void upload(String id, Message message){

        look(id);

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO reaction (message, userid, messageid) VALUES ('"+ message.getContentDisplay() + "', '"+ id +"' , '"+ message.getId() +"')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void upload(String id, String messageid, String lobbyid){

        look(id);

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO reaction (message, userid, messageid) VALUES ('"+ lobbyid +"' ,'"+ id +"' , '"+ messageid +"')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void look(String id){
        int exist = 0;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM reaction";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("userid"))){
                    exist = 1;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(exist != 0){
            delete(id);
        }
    }

    public static void delete(String id) {
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM reaction WHERE userid = '" + id +"'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String[] download(String id){
        String message = null;
        String messageid = null;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM reaction";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("userid"))){
                    message = rs.getString("message");
                    messageid = rs.getString("messageid");
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] erg = new String[2];
        erg[0] = message;
        erg[1] = messageid;

        return erg;
    }
}
