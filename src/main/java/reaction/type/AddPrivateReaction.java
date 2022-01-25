package reaction.type;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;

public interface AddPrivateReaction {
    public void performReaction(String message, User user, PrivateChannel channel);
}
