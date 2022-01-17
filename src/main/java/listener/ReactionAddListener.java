package listener;

import games.MauMau;
import main.Start;
import mysql.games.KartenUpload;
import mysql.games.Lobby;
import mysql.webpanel.CreateAccount;
import mysql.Reaction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionAddListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (Start.INSTANCE.getStatus()) {
            if (event.getChannelType() == ChannelType.PRIVATE) {
                if (!event.getUser().isBot()) {
                    Emoji Emotes = Emoji.fromUnicode(event.getReactionEmote().getEmoji());

                    String[] mess = Reaction.download(event.getUserId());
                    if (mess[0] != null) {
                        if (mess[1].equalsIgnoreCase(event.getMessageId())) {

                            if (Emotes.equals(Emoji.fromUnicode("U+1F44D"))) {

                                Lobby.updateVoll(event.getUserId());
                                Reaction.delete(event.getUserId());

                                if (Lobby.isReady(mess[0])) {
                                    MauMau.startMauMAu(mess[0]);
                                }
                                //KARTE RECHTS MAUMAU
                            } else if (Emotes.equals(Emoji.fromUnicode("U+27A1"))) {
                                event.getChannel().deleteMessageById(event.getMessageId()).queue();

                                EmbedBuilder embedBuilder = new EmbedBuilder();
                                embedBuilder.setFooter("Du bist dran! Wähle eine Karte...");
                                embedBuilder.setImage(KartenUpload.getLastCard(event.getUserId(), 1));
                                embedBuilder.setTitle("**Deine Karten**");
                                embedBuilder.setColor(0x6DE194);


                                event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue((message) -> {
                                    Lobby.updateMessageId(event.getUserId(), message.getId());

                                    int aktiv = Lobby.getAktiv(event.getUserId());

                                    if (aktiv == KartenUpload.getCards(event.getUserId()) - 1) {

                                        aktiv = 0;
                                    } else {
                                        aktiv++;
                                    }

                                    Reaction.delete(event.getUserId());
                                    MauMau.reactionA(event.getChannel().getId(), Lobby.getMessage(event.getUserId()), event.getUserId(), aktiv, Lobby.getLobbyId(event.getUserId()));
                                });
                                //KARTE LINKS MAUMAU
                            } else if (Emotes.equals(Emoji.fromUnicode("U+2B05"))) {
                                event.getChannel().deleteMessageById(event.getMessageId()).queue();

                                EmbedBuilder embedBuilder = new EmbedBuilder();
                                embedBuilder.setFooter("Du bist dran! Wähle eine Karte...");
                                embedBuilder.setImage(KartenUpload.getLastCard(event.getUserId(), 1));
                                embedBuilder.setTitle("**Deine Karten**");
                                embedBuilder.setColor(0x6DE194);


                                event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue((message) -> {
                                    Lobby.updateMessageId(event.getUserId(), message.getId());

                                    int aktiv = Lobby.getAktiv(event.getUserId());

                                    if (aktiv == 0) {

                                        aktiv = KartenUpload.getCards(event.getUserId()) - 1;
                                    } else {
                                        aktiv--;
                                    }

                                    Reaction.delete(event.getUserId());
                                    MauMau.reactionA(event.getChannel().getId(), Lobby.getMessage(event.getUserId()), event.getUserId(), aktiv, Lobby.getLobbyId(event.getUserId()));
                                });
                            }
                        }
                    }
                }
            }
        }
    }
}
