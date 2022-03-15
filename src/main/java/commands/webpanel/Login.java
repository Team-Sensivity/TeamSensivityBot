package commands.webpanel;

import commands.types.PrivateCommand;
import funktionen.GenerateToken;
import mysql.Log;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class Login implements PrivateCommand {
    @Override
    public void performCommand(User m, PrivateChannel channel, Message message) {
        if(TokenErstellen.isRegister(m.getId())) {
            String token = GenerateToken.createToken();

            TokenErstellen.setToken(token, m.getId(), 1);

            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("**Link für den Login:**");
            builder.setDescription("Benutze den Link um dich im Dashboard anzumelden...");
            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
            builder.setColor(0x6DE194);

            String link = "https://sensivity.michel929.de/webpanel/login.php?id=" + token;
            builder.addField("Link:" , ">> " + link, false);

            channel.sendMessageEmbeds(builder.build()).queue();

            Log.updateLog("Der User hat einen LoginLink angefordert", m.getName());
        }else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("**Fehler beim Login**");
            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
            builder.setColor(Color.RED);
            builder.setDescription("Du musst dich erst registrieren! Benutze dafür **/register**.");

            channel.sendMessageEmbeds(builder.build()).queue();

            Log.updateLog("Es gab einen Fehler bei dem LoginLink anforderung (User nicht Registriert", m.getName());
        }
    }
}
