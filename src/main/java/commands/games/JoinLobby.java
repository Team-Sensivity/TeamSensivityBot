package commands.games;

import commands.types.PrivateCommand;
import funktionen.GenerateToken;
import funktionen.game.EditMessages;
import mysql.games.Lobby;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;

public class JoinLobby implements PrivateCommand {
    @Override
    public void performCommand(User m, PrivateChannel channel, Message message1) {
        String[] args = message1.getContentDisplay().split(" ");

        if(args.length == 2){
            if(!Lobby.userExist(m.getId())) {
                if (args[1].equalsIgnoreCase("maumau")) {
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setTitle("Spielmodus MAUMAU");
                    builder.setAuthor("GameManager");
                    builder.addField("Erfolgreich beigetreten!!", "Warten auf andere Spieler", false);
                    builder.setColor(0x6DE194);
                    builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/karten/Spielmodis/maumau.png");

                    channel.sendMessageEmbeds(builder.build()).queue((message) -> {
                        if (Lobby.lobbyExist(1)) {
                            Lobby.createLobby(1, m.getId(), Lobby.getLobbyId(1), message.getId());
                            EditMessages.editJoinMessage(Lobby.getLobbyId(1), "MAUMAU", "https://sensivity.michel929.de/webpanel/assets/images/karten/Spielmodis/maumau.png");
                        } else {
                            Lobby.createLobby(1, m.getId(), GenerateToken.createToken(), message.getId());
                        }
                    });

                }else if (args[1].equalsIgnoreCase("Poker")) {
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setTitle("Spielmodus POKER");
                    builder.setAuthor("GameManager");
                    builder.addField("Erfolgreich beigetreten!!", "Warte auf andere Spieler", false);
                    builder.setColor(0x6DE194);
                    builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/karten/Spielmodis/poker.png");

                    channel.sendMessageEmbeds(builder.build()).queue((message) -> {
                        if (Lobby.lobbyExist(2)) {
                            Lobby.createLobby(2, m.getId(), Lobby.getLobbyId(2), message.getId());
                            EditMessages.editJoinMessage(Lobby.getLobbyId(2), "POKER", "https://sensivity.michel929.de/webpanel/assets/images/karten/Spielmodis/poker.png");
                        } else {
                            Lobby.createLobby(2, m.getId(), GenerateToken.createToken(), message.getId());
                        }
                    });

                }else if (args[1].equalsIgnoreCase("blackjack")) {

                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setTitle("Spielmodus BLACKJACK");
                    builder.setAuthor("GameManager");
                    builder.addField("Erfolgreich beigetreten!!", "Warte auf andere Spieler", false);
                    builder.setColor(0x6DE194);
                    builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/karten/Spielmodis/blackjack.png");

                    channel.sendMessageEmbeds(builder.build()).queue((message) -> {
                        if (Lobby.lobbyExist(3)) {
                            Lobby.createLobby(3, m.getId(), Lobby.getLobbyId(3), message.getId());
                            EditMessages.editJoinMessage(Lobby.getLobbyId(3), "BLACKJACK", "https://sensivity.michel929.de/webpanel/assets/images/karten/Spielmodis/blackjack.png");
                        } else {
                            Lobby.createLobby(3, m.getId(), GenerateToken.createToken(), message.getId());
                        }
                    });

                } if (args[1].equalsIgnoreCase("roulette")) {

                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setTitle("Spielmodus ROULETTE");
                    builder.setAuthor("GameManager");
                    builder.addField("Erfolgreich beigetreten!!", "Warte auf andere Spieler", false);
                    builder.setColor(0x6DE194);
                    builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/karten/Spielmodis/roulette.png");

                    channel.sendMessageEmbeds(builder.build()).queue((message) -> {
                        if (Lobby.lobbyExist(4)) {
                            Lobby.createLobby(4, m.getId(), Lobby.getLobbyId(4), message.getId());
                            EditMessages.editJoinMessage(Lobby.getLobbyId(4), "ROULETTE", "https://sensivity.michel929.de/webpanel/assets/images/karten/Spielmodis/roulette.png");
                        } else {
                            Lobby.createLobby(4, m.getId(), GenerateToken.createToken(), message.getId());
                        }
                    });
                }
            }else {
                channel.sendMessage("Du kannst nur in einem Game gleichzeitig sein!").queue();
            }
        }else {
            channel.sendMessage("Du musst noch einen Spielmodus auswählen! (&join <Spielmodus>)").queue();
        }
    }
}
