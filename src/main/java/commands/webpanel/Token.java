package commands.webpanel;

import commands.types.PrivateCommand;
import funktionen.GenerateToken;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class Token implements PrivateCommand {
    @Override
    public void performCommand(User m, PrivateChannel channel, Message message) {
        if(TokenErstellen.isRegister(m.getId())) {
            String token = GenerateToken.createToken();

            TokenErstellen.setToken(token, m.getId(), 1);

            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor("Team Sensivity");
            builder.setColor(0x6DE194);
            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
            builder.setTitle("**Token für den Login:**");
            builder.addField("Link:", ">> " + token, false);

            channel.sendMessageEmbeds(builder.build()).queue();
        }else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor("Team Sensivity");
            builder.setColor(Color.RED);
            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
            builder.setTitle("**Fehler beim Login**");
            builder.setDescription("Du musst dich erst Registrieren. Benutze dafür **&register <password>**.");

            channel.sendMessageEmbeds(builder.build()).queue();
        }
    }
}
