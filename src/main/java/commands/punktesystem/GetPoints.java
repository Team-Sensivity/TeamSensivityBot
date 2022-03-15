package commands.punktesystem;

import commands.types.ServerCommand;
import mysql.Log;
import mysql.webpanel.PunkteSystem;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class GetPoints implements ServerCommand {
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        String[] args = message.getContentDisplay().split(" ");
            if(TokenErstellen.isRegister(m.getId())) {
                int punkte = PunkteSystem.getPoints(m.getId());

                EmbedBuilder builder = new EmbedBuilder();
                builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
                if(punkte == 1){
                    builder.setDescription("Du hast einen Punkt.");
                }else {
                    builder.setDescription("Du hast **" + punkte + " Punkte.**");
                }
                builder.setColor(0x6DE194);
                builder.setTitle("**Deine Punkte**");
                builder.setAuthor("Team Sensivity");

                channel.sendMessageEmbeds(builder.build()).queue();

                Log.updateLog("Der User hat seine Punkte angefragt", m.getEffectiveName());
            }else {
                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("**Fehler beim ermitteln der Punkte**");
                builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
                builder.setColor(Color.RED);
                builder.setDescription("Du musst dich erst registrieren! Benutze dafür **/register**.");

                channel.sendMessageEmbeds(builder.build()).queue();

                Log.updateLog("Es gab einen Fehler bei der Punkte ermittlung", m.getEffectiveName());
            }
    }
}
