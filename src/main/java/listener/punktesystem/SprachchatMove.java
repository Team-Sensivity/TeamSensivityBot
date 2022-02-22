package listener.punktesystem;

import main.Start;
import mysql.BotInfos;
import mysql.TemporereChannel;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SprachchatMove extends ListenerAdapter {

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
        if (Start.INSTANCE.getStatus()) {
            if (event.getChannelJoined().getId().equals(BotInfos.getChannel())) {
                final String[] ids = new String[3];
                Start.INSTANCE.getApi().getGuildById("773995277840941067").createCategory("--- " + event.getMember().getEffectiveName() + "s Channel ---").queue((category) -> {
                    ids[0] = category.getId();
                    Start.INSTANCE.getApi().getGuildById("773995277840941067").getCategoryById(ids[0]).createTextChannel(event.getMember().getEffectiveName() + "s TextChannel").queue((channel) -> {
                        ids[1] = channel.getId();
                    });
                    Start.INSTANCE.getApi().getGuildById("773995277840941067").getCategoryById(ids[0]).createVoiceChannel(event.getMember().getEffectiveName() + "s Channel").queue((channel) -> {
                        Start.INSTANCE.getApi().getGuildById("773995277840941067").moveVoiceMember(event.getMember(), channel).queue();
                        ids[2] = channel.getId();
                        TemporereChannel.createChannel(ids[2], ids[1], ids[0]);
                        Start.INSTANCE.getApi().getGuildById("773995277840941067").modifyCategoryPositions().selectPosition(Start.INSTANCE.getApi().getCategoryById(ids[0]).getPosition()).moveUp(9).queue();
                    });
                });
            }

            if (TemporereChannel.isExist(event.getChannelLeft().getId())) {
                if (event.getChannelLeft().getMembers().size() == 0) {
                    Start.INSTANCE.getApi().getGuildById("773995277840941067").getCategoryById(TemporereChannel.getCat(event.getChannelLeft().getId())).delete().queue();
                    Start.INSTANCE.getApi().getGuildById("773995277840941067").getVoiceChannelById(event.getChannelLeft().getId()).delete().queue();
                    Start.INSTANCE.getApi().getGuildById("773995277840941067").getTextChannelById(TemporereChannel.getTextChannel(event.getChannelLeft().getId())).delete().queue();
                    TemporereChannel.removeChannel(event.getChannelLeft().getId());
                }
            }
        }
    }
}
