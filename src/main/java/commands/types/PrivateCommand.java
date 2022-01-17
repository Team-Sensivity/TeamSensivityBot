package commands.types;

import net.dv8tion.jda.api.entities.*;

public interface PrivateCommand {
    public void performCommand(User m, PrivateChannel channel, Message message);
}
