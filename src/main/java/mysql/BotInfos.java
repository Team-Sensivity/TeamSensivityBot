package mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BotInfos {
    public static String getLogo(){
        String url = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM bot";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                url = rs.getString("logo_url");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return url;
    }
}
