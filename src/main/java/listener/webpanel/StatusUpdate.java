package listener.webpanel;

import main.Start;
import mysql.webpanel.Infos;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class StatusUpdate extends ListenerAdapter {

    @Override
    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {
        if(Start.INSTANCE.getStatus()) {
            if (event.getNewOnlineStatus().name().equals("IDLE")) {
                Infos.updateStatus(event.getUser().getId(), "ABWESEND");

                if(event.getMember().getVoiceState().inAudioChannel() && !event.getMember().isOwner()){
                    if(event.getMember().getNickname() != null) {
                        event.getMember().modifyNickname("[AFK] " + event.getMember().getNickname()).queue();
                    }else {
                        event.getMember().modifyNickname("[AFK] " + event.getMember().getEffectiveName()).queue();
                    }
                }

            } else if (event.getNewOnlineStatus().name().equals("DO_NOT_DISTURB")) {
                Infos.updateStatus(event.getUser().getId(), "BITTE NICHT STÖREN");

                if(event.getMember().getVoiceState().inAudioChannel() && !event.getMember().isOwner()){

                    if(event.getMember().getNickname() != null) {
                        String username = event.getMember().getNickname();
                        username = username.replace("[AFK]", "");
                        event.getMember().modifyNickname(username).queue();
                    }
                }
            } else {
                Infos.updateStatus(event.getUser().getId(), event.getNewOnlineStatus().name());

                if(event.getMember().getVoiceState().inAudioChannel() && !event.getMember().isOwner()){

                    if(event.getMember().getNickname() != null) {
                        String username = event.getMember().getNickname();
                        username = username.replace("[AFK]", "");
                        event.getMember().modifyNickname(username).queue();
                    }
                }
            }
        }
    }
}
