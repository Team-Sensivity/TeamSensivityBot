package listener.rollen;

import main.Start;
import mysql.webpanel.Rang;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePositionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RollePriority extends ListenerAdapter {
    @Override
    public void onRoleUpdatePosition(RoleUpdatePositionEvent event) {
        if(Start.INSTANCE.getStatus()) {
            String id = event.getRole().getId();

            if (Rang.roleExist(id)) {
                int priority = event.getRole().getPositionRaw();

                Rang.changePriority(id, priority);
            } else {
                int cor = event.getRole().getColorRaw();
                String color = String.format("#%06X", (0xFFFFFF & cor));
                Rang.createRang(event.getRole().getName(), color, event.getRole().getId(), event.getRole().getPositionRaw());
            }
        }
    }
}
