package commands.webpanel;

import commands.types.PrivateCommand;
import mysql.webpanel.CreateAccount;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;

public class Register implements PrivateCommand {

    @Override
    public void performCommand(User m, PrivateChannel channel, Message message) {
        if(TokenErstellen.isRegister(m.getId())){
            EmbedBuilder builder = new EmbedBuilder();
            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
            builder.setTitle("**Fehler beim Registrieren**");
            builder.setDescription("Du bist bereits registriert benutze **/login** um dich zu Registrieren...");
            builder.setColor(Color.RED);
            builder.setAuthor("Team Sensivity");

            channel.sendMessageEmbeds(builder.build()).queue();
        }else {
            User.Profile p = m.retrieveProfile().complete();

            CreateAccount.create(m.getName(), m.getId(), m.getEffectiveAvatarUrl(), CreateAccount.getRole(m.getId()), p.getBannerUrl());

            EmbedBuilder builder = new EmbedBuilder();
            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
            builder.setTitle("Erfolgreich Registriert");
            builder.setDescription("Benutze jetzt **/login** um dich einzuloggen...");
            builder.setColor(0x6DE194);
            builder.setAuthor("Team Sensivity");

            channel.sendMessageEmbeds(builder.build()).queue();
        }
    }
}
