package listener.rollen;

import main.Start;
import mysql.webpanel.Rang;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RolleErstellen extends ListenerAdapter {

    @Override
    public void onRoleCreate(RoleCreateEvent event) {
        if(Start.INSTANCE.getStatus()) {
            String name = event.getRole().getName();
            String id = event.getRole().getId();
            int priority = event.getRole().getPositionRaw();


            int cor = event.getRole().getColorRaw();
            String color = String.format("#%06X", (0xFFFFFF & cor));


            Rang.createRang(name, color, id, priority);
        }
    }
}
