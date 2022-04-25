package listener;

import main.Start;
import mysql.Beleidigen;
import mysql.BotInfos;
import mysql.Memes;
import mysql.TemporereChannel;
import mysql.webpanel.PunkteSystem;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentDisplay();

        if (Start.INSTANCE.getStatus() || message.contains("start")) {

            if (event.isFromType(ChannelType.TEXT)) {

                if (TokenErstellen.isRegister(event.getAuthor().getId())) {
                    PunkteSystem.chatPoints(event.getAuthor().getId());
                }

                TextChannel channel = event.getTextChannel();

                if (message.startsWith("&")) {
                    String[] args = message.substring(1).split(" ");

                    if (args.length > 0) {
                        if (!Start.INSTANCE.getCmdMan().perform(args[0], event.getMember(), channel, event.getMessage())) {
                            channel.sendMessage("Unbekannter Command").queue();
                        }
                    }
                }

                if(channel.getId().equals(BotInfos.getMemeChannel())){
                    List<Message.Attachment> attachments = event.getMessage().getAttachments();
                    if(attachments.size() == 1 && attachments.get(0).isImage()){
                        if(attachments.get(0).getWidth() > 10 && attachments.get(0).getHeight() > 10) {
                            channel.addReactionById(event.getMessageId(), "U+1F44D").queue();
                            channel.addReactionById(event.getMessageId(), "U+1F44E").queue();

                            Memes.uploadMeme(event.getMessage());
                        }
                    }else if(attachments.size() > 1){
                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED);
                    }
                }
               /* if(TemporereChannel.isExistCat(event.getTextChannel().getParentCategoryId())){
                    List<Member> m = event.getMessage().getMentionedMembers();
                    List<Role> r = event.getMessage().getMentionedRoles();

                    if(!m.isEmpty() || !r.isEmpty()) {
                        event.getTextChannel().getParentCategory().createPermissionOverride(event.getGuild().getRoleById(BotInfos.getStandardRole())).setDeny(Permission.VIEW_CHANNEL, Permission.VOICE_CONNECT).queue();


                        for (Member member : m) {
                            boolean exist = false;
                            List<PermissionOverride> p = event.getTextChannel().getParentCategory().getMemberPermissionOverrides();

                            for (PermissionOverride per: p) {
                                if(per.getMember() == member){
                                    exist = true;
                                }
                            }

                            if(exist) {
                                EnumSet<Permission> permissions = event.getTextChannel().getParentCategory().getPermissionOverride(member).getAllowed();
                                if(permissions.contains(Permission.VIEW_CHANNEL)){
                                    event.getTextChannel().getParentCategory().upsertPermissionOverride(member).setDeny(Permission.VIEW_CHANNEL, Permission.VOICE_CONNECT).queue();
                                }else {
                                    event.getTextChannel().getParentCategory().upsertPermissionOverride(member).setAllow(Permission.VIEW_CHANNEL, Permission.VOICE_CONNECT).queue();
                                }
                            } else {
                                event.getTextChannel().getParentCategory().createPermissionOverride(member).setAllow(Permission.VIEW_CHANNEL, Permission.VOICE_CONNECT).queue();
                            }
                        }
                    }

                    for (Role role : r) {
                        boolean exist = false;
                        List<PermissionOverride> p = event.getTextChannel().getParentCategory().getRolePermissionOverrides();

                        for (PermissionOverride per: p) {
                            if(per.getRole() == role){
                                exist = true;
                            }
                        }

                        if(exist) {
                            EnumSet<Permission> permissions = event.getTextChannel().getParentCategory().getPermissionOverride(role).getAllowed();
                            if(permissions.contains(Permission.VIEW_CHANNEL)){
                                event.getTextChannel().getParentCategory().upsertPermissionOverride(role).setDeny(Permission.VIEW_CHANNEL, Permission.VOICE_CONNECT).queue();
                            }else {
                                event.getTextChannel().getParentCategory().upsertPermissionOverride(role).setAllow(Permission.VIEW_CHANNEL, Permission.VOICE_CONNECT).queue();
                            }
                        }else {
                            event.getTextChannel().getParentCategory().createPermissionOverride(role).setAllow(Permission.VIEW_CHANNEL, Permission.VOICE_CONNECT).queue();
                        }
                    }

                    event.getTextChannel().sendMessage("Du hast die Permission erfolgreich geändert").queue();
                }

                */
            } else if (event.isFromType(ChannelType.PRIVATE)) {
                PrivateChannel channel = event.getPrivateChannel();

                if (message.startsWith("&")) {
                    String[] args = message.substring(1).split(" ");

                    if (args.length > 0) {
                        if (!Start.INSTANCE.getCmdMan().perform(args[0], event.getAuthor(), channel, event.getMessage())) {
                            channel.sendMessage("Unbekannter Command").queue();
                        }
                    }
                }
            } else if (event.isFromType(ChannelType.NEWS)) {

                if (TokenErstellen.isRegister(event.getAuthor().getId())) {
                    PunkteSystem.chatPoints(event.getAuthor().getId());
                }

                if (message.startsWith("&")) {
                    event.getChannel().sendMessage("Befehle können nur in normalen Text Kanälen verwendet werden!").queue();
                }
            }

        }

        if(Beleidigen.userExist(event.getAuthor().getId())){
            event.getChannel().sendMessage(Beleidigen.rage()).queue();
        }
    }
}