package listener.punktesystem;

import main.Start;
import mysql.BotInfos;
import net.dv8tion.jda.api.events.channel.ChannelDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChannelRemove extends ListenerAdapter {

    @Override
    public void onChannelDelete(ChannelDeleteEvent event) {
        //remove schutz CreateChannel
        if(event.getChannel().getId().equals(BotInfos.getChannel())){
            Start.INSTANCE.getApi().getGuildById("773995277840941067").getCategoryById("774354036920418324").createVoiceChannel("Create Channel").queue((channel) -> {
                BotInfos.updateChannel(channel.getId(), event.getChannel().getId());
                Start.INSTANCE.getApi().getGuildById("773995277840941067").getCategoryById("774354036920418324").modifyVoiceChannelPositions().selectPosition(channel.getPosition() - (Start.INSTANCE.getApi().getGuildById("773995277840941067").getVoiceChannels().size() - channel.getParentCategory().getVoiceChannels().size())).moveTo(0).queue();
            });
        //Remove schutz server-status
        }else if(event.getChannel().getId().equals(BotInfos.getStatusChannel())){
            Start.INSTANCE.getApi().getGuildById("773995277840941067").getCategoryById("774352663554424852").createTextChannel("server-status").queue((channel) -> {
                BotInfos.updateStatusChannel(channel.getId(), event.getChannel().getId());
            });
        }
    }
}
