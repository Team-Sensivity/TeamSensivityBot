package commands.punktesystem;

import commands.types.PrivateCommand;
import mysql.Log;
import mysql.webpanel.Infos;
import mysql.webpanel.LevelUpload;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class CreateLevel implements PrivateCommand {
    @Override
    public void performCommand(User m, PrivateChannel channel, Message message) {

        String[] args = message.getContentDisplay().split(" ");
        int level = 0, xp = 0;

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("**Command Fehler**");
        builder.setColor(Color.RED);
        builder.setAuthor("Team Sensivity");
        builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");

        if(Infos.getUserHaveRole(m.getId(), "799744335939239976")){
            if(args.length >= 2){
                try {
                    level = Integer.parseInt(args[1]);
                    xp = Integer.parseInt(args[2]);
                }catch (Exception e){
                    builder.setDescription("Du musst einen **Integer** eingeben!!");
                    channel.sendMessageEmbeds(builder.build()).queue();
                }

                if(level != 0 && xp != 0){
                    if(!LevelUpload.levelExist(level)){
                        if (LevelUpload.xpHigh(xp, level)){
                            LevelUpload.createLevel(level, xp);
                            channel.sendMessage("Du hast ein Level erstellt!!").queue();

                            Log.updateLog("Ein Level wurde erstellt", m.getName());

                        }else {
                            builder.setDescription("Du musst den XP Wert höher stellen!!");
                            channel.sendMessageEmbeds(builder.build()).queue();

                            Log.updateLog("Level erstellen fehlgeschlagen", m.getName());
                        }
                    }else {
                        builder.setDescription("Dieses Level existiert bereits!!");
                        channel.sendMessageEmbeds(builder.build()).queue();

                        Log.updateLog("Level erstellen fehlgeschlagen", m.getName());
                    }
                }
            }else {
                builder.setDescription("Benutze &level <level> <xp>");
                channel.sendMessageEmbeds(builder.build()).queue();

                Log.updateLog("Falsche benutzung des Commands Level", m.getName());
            }
        }else {
            builder.setDescription("Du hast keine Berechtigung dafür!!");
            channel.sendMessageEmbeds(builder.build()).queue();

            Log.updateLog("User hat keine Berechtigungen für den Command Level", m.getName());
        }
    }
}
