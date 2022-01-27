package listener.webpanel;

import mysql.webpanel.Infos;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class StatusUpdate extends ListenerAdapter {

    @Override
    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {
        if(event.getNewOnlineStatus().name().equals("IDLE")){
            Infos.updateStatus(event.getUser().getId(), "ABWESEND");
        }else if(event.getNewOnlineStatus().name().equals("DO_NOT_DISTURB")){
            Infos.updateStatus(event.getUser().getId(), "BITTE NICHT STÖREN");
        }else {
            Infos.updateStatus(event.getUser().getId(), event.getNewOnlineStatus().name());
        }
    }
}
