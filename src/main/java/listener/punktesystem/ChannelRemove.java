package listener.punktesystem;

import main.Start;
import mysql.BotInfos;
import net.dv8tion.jda.api.events.channel.ChannelDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChannelRemove extends ListenerAdapter {

    @Override
    public void onChannelDelete(ChannelDeleteEvent event) {
        if(event.getChannel().getId().equals(BotInfos.getChannel())){
            Start.INSTANCE.getApi().getGuilds().get(0).getCategoryById("774354036920418324").createVoiceChannel("Create Channel").queue((channel) -> {
                BotInfos.updateChannel(channel.getId(), event.getChannel().getId());
                Start.INSTANCE.getApi().getGuilds().get(0).getCategoryById("774354036920418324").modifyVoiceChannelPositions().selectPosition(channel.getPosition() - (Start.INSTANCE.getApi().getGuilds().get(0).getVoiceChannels().size() - channel.getParentCategory().getVoiceChannels().size())).moveTo(0).queue();
            });
        }
    }
}
