package listener;

import mysql.BotInfos;
import mysql.Memes;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionRemoveListener extends ListenerAdapter {
    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
        if (event.getChannelType() == ChannelType.TEXT){
            if(!event.getUser().isBot()) {
                if (event.getChannel().getId().equals(BotInfos.getMemeChannel())) {
                    if (Memes.isMeme(event.getMessageId())) {
                        Emoji Emotes = Emoji.fromUnicode(event.getReactionEmote().getEmoji());

                        if(Emotes.equals(Emoji.fromUnicode("U+1F44D"))){
                            Memes.removelike(event.getMessageId());
                        }else if(Emotes.equals(Emoji.fromUnicode("U+1F44E"))){
                            Memes.removedislike(event.getMessageId());
                        }
                    }
                }
            }
        }
    }
}
