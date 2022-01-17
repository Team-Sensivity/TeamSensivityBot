package listener.rollen;

import main.Start;
import mysql.webpanel.Rang;
import net.dv8tion.jda.api.events.role.update.RoleUpdateColorEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RolleColor extends ListenerAdapter {

    @Override
    public void onRoleUpdateColor(RoleUpdateColorEvent event) {
        if(Start.INSTANCE.getStatus()) {
            String id = event.getRole().getId();

            if (Rang.roleExist(id)) {
                int cor = event.getRole().getColorRaw();
                String color = String.format("#%06X", (0xFFFFFF & cor));

                Rang.changeColor(color, id);
            } else {
                int cor = event.getRole().getColorRaw();
                String color = String.format("#%06X", (0xFFFFFF & cor));
                Rang.createRang(event.getRole().getName(), color, event.getRole().getId(), event.getRole().getPositionRaw());
            }
        }
    }
}
