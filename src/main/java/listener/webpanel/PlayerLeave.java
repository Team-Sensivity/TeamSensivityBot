package listener.webpanel;

import main.Start;
import mysql.webpanel.CreateAccount;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class PlayerLeave extends ListenerAdapter {
    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        if(TokenErstellen.isRegister(event.getUser().getId())) {
            CreateAccount.delete(event.getUser().getId());
            CreateAccount.deleteRollen(event.getUser().getId());
        }

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.RED);
        builder.setTitle("User left the Server");
        builder.setAuthor("Team Sensivity");
        builder.setDescription("Der User **" + event.getUser().getName() + "** hat den Server verlassen...");

        Start.INSTANCE.getApi().getGuildById("773995277840941067").getTextChannelById("774266159658041384").sendMessageEmbeds(builder.build()).queue();
    }
}
