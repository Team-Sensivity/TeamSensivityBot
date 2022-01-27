package listener;

import main.Start;
import mysql.BotInfos;
import mysql.Memes;
import mysql.webpanel.PunkteSystem;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.List;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentDisplay();

        if (Start.INSTANCE.getStatus() || message.contains("start")) {

            if (event.isFromType(ChannelType.TEXT)) {

                if (TokenErstellen.isRegister(event.getAuthor().getId())) {
                    PunkteSystem.chatPoints(event.getAuthor().getId());
                }

                TextChannel channel = event.getTextChannel();

                if (message.startsWith("&")) {
                    String[] args = message.substring(1).split(" ");

                    if (args.length > 0) {
                        if (!Start.INSTANCE.getCmdMan().perform(args[0], event.getMember(), channel, event.getMessage())) {
                            channel.sendMessage("Unbekannter Command").queue();
                        }
                    }
                }

                if(channel.getId().equals(BotInfos.getMemeChannel())){
                    List<Message.Attachment> attachments = event.getMessage().getAttachments();
                    if(attachments.size() == 1 && attachments.get(0).isImage()){
                        channel.addReactionById(event.getMessageId(),"U+1F44D").queue();
                        channel.addReactionById(event.getMessageId(), "U+1F44E").queue();

                        Memes.uploadMeme(event.getMessage());
                    }else if(attachments.size() > 1){
                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED);
                    }
                }
            } else if (event.isFromType(ChannelType.PRIVATE)) {
                PrivateChannel channel = event.getPrivateChannel();

                if (message.startsWith("&")) {
                    String[] args = message.substring(1).split(" ");

                    if (args.length > 0) {
                        if (!Start.INSTANCE.getCmdMan().perform(args[0], event.getAuthor(), channel, event.getMessage())) {
                            channel.sendMessage("Unbekannter Command").queue();
                        }
                    }
                }
            } else if (event.isFromType(ChannelType.NEWS)) {

                if (TokenErstellen.isRegister(event.getAuthor().getId())) {
                    PunkteSystem.chatPoints(event.getAuthor().getId());
                }

                if (message.startsWith("&")) {
                    event.getChannel().sendMessage("Befehle können nur in normalen Text Kanälen verwendet werden!").queue();
                }
            }

        }
    }
}
