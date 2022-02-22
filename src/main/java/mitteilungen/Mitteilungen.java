package mitteilungen;

import main.Start;
import mysql.BotInfos;
import mysql.Connect;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.sql.*;
import java.util.TimerTask;

public class Mitteilungen extends TimerTask {

    @Override
    public void run() {
        String newminecraft = "", minecraft = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM Mitteilungen";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                sendMitteilung(rs.getString("discordID"), rs.getString("text"), rs.getString("color"), rs.getString("title"));
            }

            sql = "SELECT * FROM botInfos";
            stmt  = con.createStatement();
            rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                minecraft = rs.getString("minecraft");
            }

            sql = "SELECT * FROM botInfos";
            stmt  = con.createStatement();
            rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                newminecraft = rs.getString("newminecraft");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(!newminecraft.equals(minecraft)){
            BotInfos.updateMinecraft(newminecraft);

            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(0x6DE194);
            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
            builder.setAuthor("Team Sensivity");
            builder.setDescription("Hier findest du eine Übersicht welche Team Sensivity Server gerade Online sind und welche nicht.");
            builder.addField("**MinecraftServer**", "Status: " + BotInfos.getMinecraft(), false);

            Start.INSTANCE.getApi().getGuildById("773995277840941067").getTextChannelById(BotInfos.getStatusChannel()).editMessageEmbedsById(BotInfos.getStatusMessage(), builder.build()).queue();
        }
    }

    public static void sendMitteilung(String id, String text, String color, String titel){

        Start.INSTANCE.getApi().openPrivateChannelById(id).queue((c) -> {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle(titel);
            builder.setColor(Color.decode(color));
            builder.setThumbnail("https://sensivity.michel929.de/assets/img/logo.png");
            builder.setAuthor("Team Sensivity");
            builder.appendDescription(text);

            c.sendMessageEmbeds(builder.build()).queue();

            deleteMitteilung(id);
        });
    }

    public static void deleteMitteilung(String id){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM Mitteilungen WHERE discordID = '" + id +"'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
