package listener.webpanel;

import main.Start;
import mysql.webpanel.Infos;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.events.user.update.UserUpdateNameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class NameUpdate extends ListenerAdapter {
    @Override
    public void onUserUpdateName(UserUpdateNameEvent event) {
        if(Start.INSTANCE.getStatus()) {
            String id = event.getUser().getId();
            if (TokenErstellen.isRegister(id)) {
                String username = event.getNewName();

                Infos.updateName(username, id);
            }
        }
    }
}
