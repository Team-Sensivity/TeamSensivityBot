package listener.webpanel;

import main.Start;
import mysql.webpanel.Infos;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.events.user.update.UserUpdateAvatarEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AvatarUpdate extends ListenerAdapter {

    @Override
    public void onUserUpdateAvatar(UserUpdateAvatarEvent event) {
        if(Start.INSTANCE.getStatus()) {
            String id = event.getUser().getId();
            if (TokenErstellen.isRegister(id)) {
                String url = event.getNewAvatarUrl();

                Infos.updateAvatar(url, id);
            }
        }
    }
}
