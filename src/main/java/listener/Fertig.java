package listener;

import main.Start;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Fertig extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        Start.startTimer();
        Start.checkChannel();
    }
}
