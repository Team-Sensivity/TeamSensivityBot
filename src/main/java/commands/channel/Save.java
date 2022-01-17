package commands.channel;

import com.mysql.cj.Messages;
import commands.types.ServerCommand;
import mysql.TemporereChannel;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.utils.MiscUtil;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class Save implements ServerCommand {
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        if(TemporereChannel.isExistText(channel.getId())){

            int nummer = TemporereChannel.getChatNummer(m.getId()) + 1;

            MessageHistory history = MessageHistory.getHistoryFromBeginning(channel).complete();

            List<Message> mess = history.getRetrievedHistory();

                for (Message me : mess) {
                    if(!TemporereChannel.textExist(me.getId())) {
                        TemporereChannel.saveChat(me.getAuthor().getId(), me.getContentDisplay(), m.getId(), nummer, me.getId());
                    }
                }

            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("**Erfolgreich gespeichert!**");
            builder.setDescription("Du hast erfolgreich den Chat gespeichert... Mit /load <nummer> kannst du ihn wieder aufrufen.");
            builder.setAuthor("Team Sensivity");
            builder.setColor(0x6DE194);

            channel.sendMessageEmbeds(builder.build()).queue();
        }else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("**Fehler bei der Benutzung des Commands**");
            builder.setDescription("Der Command kann nur in Temporären TextChanneln benutzt werden");
            builder.setAuthor("Team Sensivity");
            builder.setColor(Color.RED);

            channel.sendMessageEmbeds(builder.build()).queue();
        }
    }
}
