package listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateTimeOutEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Timeout extends ListenerAdapter {
    @Override
    public void onGuildMemberUpdateTimeOut(GuildMemberUpdateTimeOutEvent event) {
        PrivateChannel p = event.getUser().openPrivateChannel().complete();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.RED);
        builder.setTitle("Timeout!!");
        builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
        builder.setAuthor("Team Sensivity");
        builder.setDescription("Du hast einen Timeout bekommen du kannst ab dem **" + event.getNewTimeOutEnd() + "** wieder reden und schreiben...");

        p.sendMessageEmbeds(builder.build()).queue();
    }
}
