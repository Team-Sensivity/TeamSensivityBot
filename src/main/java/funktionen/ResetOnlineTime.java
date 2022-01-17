package funktionen;

import mysql.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.TimerTask;

public class ResetOnlineTime extends TimerTask {
    @Override
    public void run() {

            try {
                Connection con = Connect.getConnection();

                PreparedStatement posted = con.prepareStatement("DELETE FROM Online");

                posted.executeUpdate();
                con.close();
            } catch (SQLException e ) {
                e.printStackTrace();
            }
    }
}
