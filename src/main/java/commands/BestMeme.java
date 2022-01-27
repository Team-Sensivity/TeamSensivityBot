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
        String[] args = message.getContentDisplay().split(" ");

        if(args.length == 0) {
            String meme = Memes.bestMeme();
            int i = 0;
            boolean exist = false;
            String messsageid = channel.getLatestMessageId();

            while (!exist) {
                MessageHistory history = MessageHistory.getHistoryBefore(channel, messsageid).limit(100).complete();
                List<Message> mess = history.getRetrievedHistory();
                for (Message message1 : mess) {
                    i++;
                    if (message1.getId().equals(meme)) {
                        exist = true;
                        String url = message1.getAttachments().get(0).getUrl();
                        int likes = Memes.getLikes(message1.getId());
                        String user = message1.getAuthor().getName();

                        EmbedBuilder builder = new EmbedBuilder();
                        builder.setTitle("**Bestes Meme**");
                        builder.setAuthor("Team Sensivity");
                        builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
                        builder.setDescription("Das Meme ist das beliebteste Meme mit **" + likes + "** UP-Votes");
                        builder.setColor(Color.orange);
                        builder.setFooter("Meme von " + user);
                        builder.setImage(url);

                        channel.sendMessageEmbeds(builder.build()).queue();
                    } else {
                        if (i == 100) {
                            messsageid = message1.getId();
                            i = 0;
                        }
                    }
                }
            }
        }else {
            if(args[1].equalsIgnoreCase("month")){
                String meme = Memes.MemeOfTheMonth();
                int i = 0;
                boolean exist = false;
                String messsageid = channel.getLatestMessageId();

                while (!exist) {
                    MessageHistory history = MessageHistory.getHistoryBefore(channel, messsageid).limit(100).complete();
                    List<Message> mess = history.getRetrievedHistory();
                    for (Message message1 : mess) {
                        i++;
                        if (message1.getId().equals(meme)) {
                            exist = true;
                            String url = message1.getAttachments().get(0).getUrl();
                            int likes = Memes.getLikes(message1.getId());
                            String user = message1.getAuthor().getName();

                            EmbedBuilder builder = new EmbedBuilder();
                            builder.setTitle("**Best Meme of the Month**");
                            builder.setAuthor("Team Sensivity");
                            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
                            builder.setDescription("Das beliebteste Meme des Monats hat **" + likes + "** UP-Votes");
                            builder.setColor(Color.orange);
                            builder.setFooter("Meme von " + user);
                            builder.setImage(url);

                            channel.sendMessageEmbeds(builder.build()).queue();
                        } else {
                            if (i == 100) {
                                messsageid = message1.getId();
                                i = 0;
                            }
                        }
                    }
                }
            }
        }
    }
}
