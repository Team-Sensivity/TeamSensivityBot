package mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Beleidigen {
    public static boolean userExist(String id){
        boolean exist = false;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM sauer";
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

    public static String rage(){
        String beleidigung = "";

        return beleidigung;
    }
}
