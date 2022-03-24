package commands.webpanel;

import commands.types.ServerCommand;
import funktionen.GenerateToken;
import main.Start;
import mysql.Log;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class TokenS implements ServerCommand {
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        Start.INSTANCE.getApi().openPrivateChannelById(m.getId()).queue((channel1) -> {
            if(TokenErstellen.isRegister(m.getId())) {
                String token = GenerateToken.createToken();

                TokenErstellen.setToken(token, m.getId(), 1);

                EmbedBuilder builder = new EmbedBuilder();
                builder.setAuthor("Team Sensivity");
                builder.setColor(0x6DE194);
                builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
                builder.setTitle("**Token für den Login:**");
                builder.addField("Link:", ">> " + token, false);

                channel1.sendMessageEmbeds(builder.build()).queue();
            }else {
                EmbedBuilder builder = new EmbedBuilder();
                builder.setAuthor("Team Sensivity");
                builder.setColor(Color.RED);
                builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
                builder.setTitle("**Fehler beim Login**");
                builder.setDescription("Du musst dich erst Registrieren. Benutze dafür **&register <password>**.");

                channel1.sendMessageEmbeds(builder.build()).queue();

                Log.updateLog("Fehler bei der Token erstellung.");
            }
        });
    }
}
