package listener.punktesystem;

import main.Start;
import mysql.TemporereChannel;
import mysql.webpanel.PunkteSystem;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SprachchatDisconnect extends ListenerAdapter {

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        if(Start.INSTANCE.getStatus()) {
            String id = event.getMember().getId();
            if (TokenErstellen.isRegister(id) && PunkteSystem.userJoined(id)) {
                if (!event.getMember().getId().equals("828791712259768352") || !event.getMember().getId().equals("917069851191816262")) {
                    PunkteSystem.time(id);
                }
            }
        }

        if(TemporereChannel.isExist(event.getChannelLeft().getId())){
            if(event.getChannelLeft().getMembers().size() == 0){

                event.getGuild().getVoiceChannelById(event.getChannelLeft().getId()).delete().queue();
                event.getGuild().getTextChannelById(TemporereChannel.getTextChannel(event.getChannelLeft().getId())).delete().queue();
                event.getGuild().getCategoryById(TemporereChannel.getCat(event.getChannelLeft().getId())).delete().queue();
                TemporereChannel.removeChannel(event.getChannelLeft().getId());

            }else {
                //if (event.getMember().getId().equals(TemporereChannel.getAdmin(event.getChannelLeft().getId()))) {
                //    event.getChannelLeft().getMembers().get(0).
               // }
            }
        }
    }
}
