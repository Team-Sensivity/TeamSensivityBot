package listeners;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommand extends ListenerAdapter {
    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getChannel().getType().equals(ChannelType.PRIVATE)) {
            if (event.getName().equals("login")) {

            }
        }
    }
}