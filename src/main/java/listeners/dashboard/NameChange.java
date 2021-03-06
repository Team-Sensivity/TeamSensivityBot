package listeners.dashboard;

import mysql.dashboard.PlayerInfos;
import net.dv8tion.jda.api.events.user.update.UserUpdateNameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class NameChange extends ListenerAdapter {
    @Override
    public void onUserUpdateName(UserUpdateNameEvent event) {
        String name = event.getNewName();

        PlayerInfos.updatePlayerInfos(event.getUser().getId(), "discord_username", name);
    }
}
