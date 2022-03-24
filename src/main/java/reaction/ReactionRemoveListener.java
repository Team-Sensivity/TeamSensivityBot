package reaction;

import main.Start;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionRemoveListener extends ListenerAdapter {
    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
        if(Start.INSTANCE.getStatus()) {
            if (event.getChannelType() == ChannelType.TEXT) {
                if (!event.getUser().isBot()) {
                    if (Start.INSTANCE.getReact().removeReaction.containsKey(event.getReactionEmote().getEmoji()))
                        Start.INSTANCE.getReact().performR(event.getMessageId(), event.getMember(), event.getChannel(), event.getReactionEmote().getEmoji());
                }
            }
        }
    }
}
