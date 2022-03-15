package commands.channel;

import commands.types.ServerCommand;
import mysql.BotInfos;
import mysql.Log;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class ServerStatus implements ServerCommand {

    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        if(m.hasPermission(Permission.ADMINISTRATOR)){
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

            channel.sendMessageEmbeds(builder.build()).queue();

            Log.updateLog("Neue Status Nachricht wurde erstellt", m.getEffectiveName());
        }else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Color.RED);
            builder.setAuthor("Team Sensivity");
            builder.setDescription("Du hast keine Berechtigungen für diesen Command");
            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");

            channel.sendMessageEmbeds(builder.build()).queue();

            Log.updateLog("Keine Berechtigung für den Command", m.getEffectiveName());
        }
    }
}
