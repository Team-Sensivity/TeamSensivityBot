package reaction;

import main.Start;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionAddListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (Start.INSTANCE.getStatus()) {
            if (event.getChannelType() == ChannelType.TEXT){
                if(!event.getUser().isBot()) {
                    if(Start.INSTANCE.getReact().addReaction.containsKey(event.getReactionEmote().getEmoji())) {
                        Start.INSTANCE.getReact().perform(event.getMessageId(), event.getMember(), event.getChannel(), event.getReactionEmote().getEmoji());
                    }
                }
            }
        }
    }
}
