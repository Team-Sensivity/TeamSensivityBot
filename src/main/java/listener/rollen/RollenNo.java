package listener.rollen;

import main.Start;
import mysql.webpanel.Infos;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RollenNo extends ListenerAdapter {

    @Override
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
        if(Start.INSTANCE.getStatus()) {
            if (TokenErstellen.isRegister(event.getUser().getId())) {
                Infos.updateRole(event.getMember());
            }
        }
    }
}
