package listener;

import mysql.Memes;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageRemove extends ListenerAdapter {
    @Override
    public void onMessageDelete(MessageDeleteEvent event) {
        if(Memes.isMeme(event.getMessageId())){
            Memes.deleteMeme(event.getMessageId());
        }
    }
}
