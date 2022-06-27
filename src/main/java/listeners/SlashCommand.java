package listeners;

import mysql.BotInfos;
import mysql.Token;
import mysql.dashboard.PlayerInfos;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class SlashCommand extends ListenerAdapter {
    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getChannel().getType().equals(ChannelType.PRIVATE) || event.getChannel().getType().equals(ChannelType.TEXT) || event.getChannel().getType().equals(ChannelType.VOICE)) {
            if (event.getName().equals("login")) {

                String link = Token.uploadToken(event.getMember().getId());

                EmbedBuilder builder = new EmbedBuilder();
                builder.setAuthor("Team Sensivity");
                builder.setFooter("https://team-sensivity.de");
                //builder.setImage(BotInfos.getLogo());
                builder.setColor(Color.BLACK);
                builder.setTitle("Login Link");
                builder.setDescription("");

                event.replyEmbeds(builder.build()).queue();

                System.out.println(event.getMember().getActivities().get(0));
            }else if(event.getName().equals("connect")){
                User.Profile p = event.getUser().retrieveProfile().complete();
                String banner = "";

                if(p.getBannerUrl() != null) {
                    banner = p.getBannerUrl();
                }else {
                    banner = p.getAccentColor().toString();
                }

                if(!PlayerInfos.accountExist(event.getMember().getId())) {
                    PlayerInfos.createAccount(event.getMember().getId(), event.getMember().getEffectiveName(), event.getMember().getAvatarUrl(), banner);
                }
            }
        }
    }
}