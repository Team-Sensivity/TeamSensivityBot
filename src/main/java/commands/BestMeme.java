package commands;

import commands.types.ServerCommand;
import mysql.Memes;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.List;

public class BestMeme implements ServerCommand {
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        MessageHistory history = MessageHistory.getHistoryFromBeginning(channel).complete();
        List<Message> mess = history.getRetrievedHistory();
        String meme = Memes.bestMeme();

        for (Message message1: mess) {
            System.out.println(message1.getContentDisplay());
           if(message1.getId().equals(meme)){
             String url = message1.getAttachments().get(0).getUrl();
             String user = message1.getAuthor().getName();

               EmbedBuilder builder = new EmbedBuilder();
               builder.setTitle("**Bestes Meme**");
               builder.setAuthor("Team Sensivity");
               builder.setColor(Color.orange);
               builder.setFooter("Meme von **" + user + "**");
               builder.setImage(url);

               channel.sendMessageEmbeds(builder.build()).queue();
           }
        }
    }
}
