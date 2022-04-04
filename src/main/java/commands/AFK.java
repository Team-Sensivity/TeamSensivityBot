package commands;

import commands.types.PrivateCommand;
import main.Start;
import mysql.Log;
import mysql.webpanel.CreateAccount;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class AFK implements PrivateCommand {
    @Override
    public void performCommand(User m, PrivateChannel channel, Message message) {
        if(TokenErstellen.isRegister(m.getId())) {
            if (CreateAccount.getRole(m.getId()).equalsIgnoreCase("799744335939239976")) {
                if(Start.INSTANCE.getAFK()) {
                    Start.INSTANCE.setAFK(false);
                    System.out.println("AFK Funktion ist ausgeschaltet!");

                    channel.sendMessage("AFK Funktion ist ausgeschaltet!").queue();
                }else {
                    Start.INSTANCE.setAFK(true);
                    System.out.println("AFK Funktion ist eingeschaltet!");

                    channel.sendMessage("AFK Funktion ist eingeschaltet!").queue();
                }

                Log.updateLog("Bpot wurde in den Online Modus gesetzt", m.getName());
            }else {
                EmbedBuilder builder = new EmbedBuilder();
                builder.setAuthor("Team Sensivity");
                builder.setColor(Color.RED);
                builder.setTitle("**Keine Berechtigung**");
                builder.setDescription("Du hast keine Berechtigung für diesen Command");
                builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");

                channel.sendMessageEmbeds(builder.build()).queue();

                Log.updateLog("User hat keine Berechtigungen für den Command AFK", m.getName());
            }
        }else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor("Team Sensivity");
            builder.setColor(Color.RED);
            builder.setTitle("**Keine Berechtigung**");
            builder.setDescription("Du hast keine Berechtigung für diesen Command");
            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");

            channel.sendMessageEmbeds(builder.build()).queue();

            Log.updateLog("User hat keine Berechtigungen für den Command AFK", m.getName());
        }
    }
}
