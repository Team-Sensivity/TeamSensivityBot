package listener;

import main.Start;
import mysql.BotInfos;
import mysql.webpanel.CreateAccount;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;

public class PlayerJoin extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        if(Start.INSTANCE.getStatus()) {
            Role user = Start.INSTANCE.getApi().getGuildById("773995277840941067").getRoleById(BotInfos.getStandardRole());
            event.getGuild().addRoleToMember(event.getMember(), user).queue();
            User.Profile p = event.getUser().retrieveProfile().complete();
            CreateAccount.create(event.getUser().getName(), event.getUser().getId(), event.getUser().getEffectiveAvatarUrl(), CreateAccount.getRole(event.getUser().getId()), p.getBannerUrl());
        }
    }
}
