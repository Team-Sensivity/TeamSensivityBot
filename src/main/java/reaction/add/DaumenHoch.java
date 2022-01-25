package reaction.add;

import mysql.BotInfos;
import mysql.Memes;
import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.Member;
import reaction.type.AddReaction;

public class DaumenHoch implements AddReaction {
    @Override
    public void performReaction(String message, Member member, Channel channel) {
        if (channel.getId().equals(BotInfos.getMemeChannel())) {
            if (Memes.isMeme(message)) {
                Memes.like(message);
            }
        }
    }
}
