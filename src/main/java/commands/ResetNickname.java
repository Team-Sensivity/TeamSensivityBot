package commands;

import commands.types.ServerCommand;
import main.Start;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class ResetNickname implements ServerCommand {
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        if(m.hasPermission(Permission.ADMINISTRATOR)){
            List<Member> member = Start.INSTANCE.getApi().getGuildById("773995277840941067").getMembers();

            for (Member member1: member) {
                if(member1.getNickname() != null && !member1.isOwner()) {
                    member1.modifyNickname(member1.getUser().getName()).queue();
                }
            }

            channel.sendMessage("Nicknames wurden zurückgesetzt").queue();
        }
    }
}
