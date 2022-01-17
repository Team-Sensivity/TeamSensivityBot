package commands;

import commands.types.PrivateCommand;
import main.Start;
import mysql.webpanel.CreateAccount;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class Exit implements PrivateCommand {
    @Override
    public void performCommand(User m, PrivateChannel channel, Message message) {
        if(TokenErstellen.isRegister(m.getId())) {
            if (CreateAccount.getRole(m.getId()).equalsIgnoreCase("799744335939239976")) {
                Start.INSTANCE.getApi().getPresence().setStatus(OnlineStatus.OFFLINE);
                Start.INSTANCE.setStatus(false);
                System.out.println("Bot ist offline!");
                channel.sendMessage("Bot ist Offline").queue();
            }else {
                EmbedBuilder builder = new EmbedBuilder();
                builder.setAuthor("Team Sensivity");
                builder.setColor(Color.RED);
                builder.setTitle("**Keine Berechtigung**");
                builder.setDescription("Du hast keine Berechtigung für diesen Command");
                builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");

                channel.sendMessageEmbeds(builder.build()).queue();
            }
        }else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor("Team Sensivity");
            builder.setColor(Color.RED);
            builder.setTitle("**Keine Berechtigung**");
            builder.setDescription("Du hast keine Berechtigung für diesen Command");
            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");

            channel.sendMessageEmbeds(builder.build()).queue();
        }
    }
}