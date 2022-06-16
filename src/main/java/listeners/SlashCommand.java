package listeners;

import mysql.BotInfos;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class SlashCommand extends ListenerAdapter {
    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getChannel().getType().equals(ChannelType.PRIVATE) || event.getChannel().getType().equals(ChannelType.TEXT) || event.getChannel().getType().equals(ChannelType.VOICE)) {
            if (event.getName().equals("login")) {

                EmbedBuilder builder = new EmbedBuilder();
                builder.setAuthor("Team Sensivity");
                builder.setFooter("https://team-sensivity.de");
                builder.setImage(BotInfos.getLogo());
                builder.setColor(Color.BLACK);
                builder.setTitle("Login Link");
                builder.setDescription("");

                event.replyEmbeds(builder.build()).queue();
            }
        }
    }
}