package listeners;

import main.Start;
import mysql.dashboard.PlayerInfos;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PlayerJoin extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Member m = event.getMember();
        Role r = Start.INSTANCE.getApi().getGuildById(Start.GUILD_ID).getRoleById(Start.STANDART_ROLE);

        m.getRoles().add(r);
        User.Profile p = m.getUser().retrieveProfile().complete();
        String banner = "";

        if(p.getBannerUrl() != null) {
            banner = p.getBannerUrl();
        }else {
            banner = p.getAccentColor().toString();
        }

        if(!PlayerInfos.accountExist(m.getId())) {
            PlayerInfos.createAccount(m.getId(), m.getEffectiveName(), m.getAvatarUrl(), banner);
        }
    }
}
