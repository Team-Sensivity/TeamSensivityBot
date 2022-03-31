package listener.webpanel;

import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class NickNameUpdate extends ListenerAdapter {
    @Override
    public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {
        int length = event.getNewNickname().length();

        if(length >= 27){
            if(event.getOldNickname() != null) {
                event.getMember().modifyNickname(event.getOldNickname()).queue();
            }else {
                event.getMember().modifyNickname(event.getUser().getName()).queue();
            }
        }
    }
}
