package commands.webpanel;

import commands.types.ServerCommand;
import main.Start;
import mysql.webpanel.CreateAccount;
import mysql.webpanel.Infos;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;
import java.util.List;

public class UpdateUsers implements ServerCommand {
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {

        if (m.getId().equals("422148236875137059")) {
            Guild g = Start.INSTANCE.getApi().getGuilds().get(0);
            List<Member> members = g.getMembers();

            for (Member member : members) {
                User.Profile p = member.getUser().retrieveProfile().complete();

                if (TokenErstellen.isRegister(member.getId())) {
                    Infos.updateAll(member.getEffectiveAvatarUrl(), member.getEffectiveName(), p.getBannerUrl(), member.getId());
                    Infos.updateRole(member);
                } else {
                    CreateAccount.create(member.getEffectiveName(), member.getId(), member.getAvatarUrl(), CreateAccount.getRole(member.getId()), p.getBannerUrl());
                }
            }

            channel.sendMessage("https://tenor.com/view/never-update-chicken-dancing-spinning-gif-16266040").queue();
        }else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor("Team Sensivity");
            builder.setColor(Color.RED);
            builder.setTitle("**Keine Berechtigung**");
            builder.setDescription("Du hast keine Berechtigung für diesen Command");
            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");

            channel.sendMessageEmbeds(builder.build()).queue();
        }
    }
}
