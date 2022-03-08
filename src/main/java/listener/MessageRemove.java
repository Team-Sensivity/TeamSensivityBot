package listener;

import mysql.BotInfos;
import mysql.Memes;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageRemove extends ListenerAdapter {
    @Override
    public void onMessageDelete(MessageDeleteEvent event) {
        if(Memes.isMeme(event.getMessageId())){
            Memes.deleteMeme(event.getMessageId());
        }

        if(event.getMessageId().equals(BotInfos.getStatusMessage())){
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(0x6DE194);
            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
            builder.setAuthor("Team Sensivity");
            builder.setDescription("Hier findest du eine Übersicht welche Team Sensivity Server gerade Online sind und welche nicht.");
            builder.addField("**MinecraftServer**", "Status: " + BotInfos.getMinecraft(), false);
            builder.addField("**SpaceEngineersServer**", "Status: " + BotInfos.getSpace(), false);
            builder.addField("**Dashboard**", "Status: " + BotInfos.getDash(), false);
            builder.addBlankField(false);
            builder.addField("**Genauere Infos findest du unter:**", ">> https://monitor.michel929.de/status", false);

            event.getChannel().sendMessageEmbeds(builder.build()).queue(message -> {
                BotInfos.updateStatusMessage(message.getId(), event.getMessageId());
            });
        }
    }
}
