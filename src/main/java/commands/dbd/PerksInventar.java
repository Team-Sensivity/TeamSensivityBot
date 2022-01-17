package commands.dbd;

import commands.types.ServerCommand;
import mysql.PerksList;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.List;

public class PerksInventar implements ServerCommand {
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        String[] args = message.getContentDisplay().split(" ");

        if(args.length == 1) {

            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor("PerkManager");
            builder.setTitle("Alle Perks");

            int Anzahl = PerksList.getAnzahl();
            List<String> perks = PerksList.getSurvPerksInfos();

            builder.addField("Survivor Perks:", perks.get(0), false);
            builder.addField("Killer Perks:", perks.get(1), false);

            builder.setColor(new Color(0x6DE194));

            channel.sendMessageEmbeds(builder.build()).queue();

        }else {
            String Name = " ";
            int Seite;

            try {
               Seite =  Integer.parseInt(args[1]);
            }catch (Exception e){
                Seite = 0;
            }

            if(Seite == 0){
                for (int i = 1; i < args.length; i++){
                    if(i != args.length - 1) {
                        Name = Name + args[i] + " ";
                    }else {
                        Name = Name + args[i];
                    }
                }

                List<String> infos = PerksList.getPerkInfos(args[1]);

                if (infos.size() != 0){
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setAuthor("PerkManager");
                    builder.setColor(new Color(0x6DE194));
                    builder.setTitle(infos.get(2) + " - " + infos.get(0));
                    builder.addField("Beschreibung", infos.get(3), false);
                    builder.setThumbnail(infos.get(1));

                    channel.sendMessageEmbeds(builder.build()).queue();

                }else {
                    channel.sendMessage("Kein Passendes Perk wurde zu diesem Namen ("+ Name + ") gefunden!!").queue();
                }
            }
        }
    }
}
