package commands.punktesystem;

import commands.types.ServerCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class SetPoints implements ServerCommand {
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        String[] args = message.getContentDisplay().split(" ");

        if (m.hasPermission(Permission.ADMINISTRATOR)){
        //TODO SETPOINTS
        }else {
            channel.sendMessage("Du hast keine Berechtigungen für den Befehl!").queue();
        }
    }
}
