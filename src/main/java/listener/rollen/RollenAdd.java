package listener.rollen;

import main.Start;
import mysql.webpanel.Infos;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RollenAdd extends ListenerAdapter {

    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        if(Start.INSTANCE.getStatus()) {
            if (TokenErstellen.isRegister(event.getUser().getId())) {
                Infos.updateRole(event.getMember());
            }
        }
    }

}
