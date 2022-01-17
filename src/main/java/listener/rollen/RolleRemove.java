package listener.rollen;

import main.Start;
import mysql.webpanel.Rang;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RolleRemove extends ListenerAdapter {
    @Override
    public void onRoleDelete(RoleDeleteEvent event) {
        if(Start.INSTANCE.getStatus()) {
            String id = event.getRole().getId();
            if (Rang.roleExist(id)) {
                Rang.removeRang(id);
            }
        }
    }
}
