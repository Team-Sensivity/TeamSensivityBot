package listener.punktesystem;

import main.Start;
import mysql.BotInfos;
import mysql.TemporereChannel;
import mysql.webpanel.PunkteSystem;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SprachchatConnect extends ListenerAdapter {

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        if(Start.INSTANCE.getStatus()) {
            String id = event.getMember().getId();
            if (TokenErstellen.isRegister(id)) {
                if (!event.getMember().getId().equals("828791712259768352") || !event.getMember().getId().equals("917069851191816262")) {
                    PunkteSystem.uploadConnect(id);
                }
            }

            if(event.getChannelJoined().getId().equals(BotInfos.getChannel())){
                final String[] ids = new String[3];
                Start.INSTANCE.getApi().getGuildById("773995277840941067").createCategory("--- " + event.getMember().getEffectiveName() + "s Channel ---").queue((category) -> {
                    ids[0] = category.getId();

                    category.createPermissionOverride(event.getMember()).setAllow(Permission.VIEW_CHANNEL, Permission.MANAGE_CHANNEL).queue();

                    Start.INSTANCE.getApi().getGuildById("773995277840941067").getCategoryById(ids[0]).createTextChannel(event.getMember().getEffectiveName() + "s TextChannel").queue((channel) -> {
                        ids[1] = channel.getId();

                       /* EmbedBuilder builder = new EmbedBuilder();
                        builder.setAuthor("Team Sensivity");
                        builder.setTitle("PermissionSystem");
                        builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");
                        builder.setColor(0x6DE194);
                        builder.setDescription("Du möchtest den Channel Privat machen, dann erwähne einfach einen oder mehrere User bzw. Rollen die Zugang erhalten sollen. (Bsp.: @TeamSensivity).");

                        channel.sendMessageEmbeds(builder.build()).queue();

                        */
                    });

                    Start.INSTANCE.getApi().getGuildById("773995277840941067").getCategoryById(ids[0]).createVoiceChannel(event.getMember().getEffectiveName() + "s Channel").queue((channel) -> {
                        Start.INSTANCE.getApi().getGuildById("773995277840941067").moveVoiceMember(event.getMember(), channel).queue();
                        ids[2] = channel.getId();
                        TemporereChannel.createChannel(ids[2], ids[1], ids[0]);
                        Start.INSTANCE.getApi().getGuildById("773995277840941067").modifyCategoryPositions().selectPosition(Start.INSTANCE.getApi().getCategoryById(ids[0]).getPosition()).moveUp(9).queue();
                    });
                });
            }
        }
    }
}
