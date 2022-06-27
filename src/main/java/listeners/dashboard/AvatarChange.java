package listeners.dashboard;

import mysql.dashboard.PlayerInfos;
import net.dv8tion.jda.api.events.user.update.UserUpdateAvatarEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AvatarChange extends ListenerAdapter {
    @Override
    public void onUserUpdateAvatar(UserUpdateAvatarEvent event) {
        String url = event.getNewAvatarUrl();

        PlayerInfos.updatePlayerInfos(event.getUser().getId(), "discord_bild", url);
    }
}
