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

public class LoginS implements ServerCommand {
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {

        Start.INSTANCE.getApi().openPrivateChannelById(m.getId()).queue((channel1) ->{
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

                channel1.sendMessageEmbeds(builder.build()).queue();

                Log.updateLog("Der User hat einen LoginLink angefordert", m.getUser().getName());
            }else {
                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("**Fehler beim Login**");
                builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
                builder.setColor(Color.RED);
                builder.setDescription("Du musst dich erst registrieren! Benutze dafür **/register**.");

                channel1.sendMessageEmbeds(builder.build()).queue();

                Log.updateLog("Es gab einen Fehler bei dem LoginLink anforderung (User nicht Registriert", m.getUser().getName());
            }
        });
    }
}
