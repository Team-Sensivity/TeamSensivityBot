package reaction.type;

import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.Member;

public interface RemoveReaction {
    public void performReaction(String message, Member member, Channel channel);
}