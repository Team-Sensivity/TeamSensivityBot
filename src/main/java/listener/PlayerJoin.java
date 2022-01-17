package listener;

import main.Start;
import mysql.BotInfos;
import mysql.webpanel.CreateAccount;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PlayerJoin extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        if(Start.INSTANCE.getStatus()) {
            Role user = Start.INSTANCE.getApi().getGuilds().get(0).getRoleById(BotInfos.getStandardRole());
            event.getGuild().addRoleToMember(event.getMember(), user).queue();
            CreateAccount.create(event.getUser().getName(), event.getUser().getId(), event.getUser().getAvatarUrl(), CreateAccount.getRole(event.getUser().getId()));
        }
    }
}