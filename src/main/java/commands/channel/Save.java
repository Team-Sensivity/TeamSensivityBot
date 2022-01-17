package commands.channel;

import commands.types.ServerCommand;
import mysql.TemporereChannel;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class Save implements ServerCommand {
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        if(TemporereChannel.isExistText(channel.getId())){

            int nummer = TemporereChannel.getChatNummer(m.getId()) + 1;

            channel.getHistory().retrievePast(100).map(messages -> messages.get(1)).queue(message1 -> {
                    TemporereChannel.saveChat(message1.getAuthor().getId(), message1.getContentDisplay(), m.getId(), nummer, channel.getId());
            });

            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("**Erfolgreich gespeichert!**");
            builder.setDescription("Du hast erfolgreich den Chat gespeichert... Mit /load <nummer> kannst du ihn wieder aufrufen.");
            builder.setAuthor("Team Sensivity");
            builder.setColor(Color.RED);
            builder.setThumbnail("");
        }else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("**Fehler bei der Benutzung des Commands**");
            builder.setDescription("Der Command kann nur in Temporären TextChanneln benutzt werden");
            builder.setAuthor("Team Sensivity");
            builder.setColor(Color.RED);
            builder.setThumbnail("");
        }
    }
}
