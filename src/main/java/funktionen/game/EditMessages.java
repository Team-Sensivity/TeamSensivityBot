package funktionen.game;

import main.Start;
import mysql.Reaction;
import mysql.games.Lobby;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.List;

public class EditMessages {

    public static void editJoinMessage(String lobbyid, String game, String url){
        List<String> messagesid = Lobby.getMessageId(lobbyid);
        List<String> userid = Lobby.getUserId(lobbyid);

        int spieler = Lobby.getSpieler(lobbyid);

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Spielmodus " + game);
        builder.setAuthor("GameManager");
        builder.addField("Erfolgreich beigetreten!!", "Warte auf andere Spieler", false);
        builder.addField("Spieler in der Lobby:", "Es sind **" + spieler + " Spieler** in der Lobby", false);
        builder.setColor(0x6DE194);
        builder.setThumbnail(url);

        for (int i = 0; i < messagesid.size(); i++) {

            for(int x = 0; x < Start.INSTANCE.getApi().getPrivateChannels().size(); x++){
                String id = Start.INSTANCE.getApi().getPrivateChannels().get(x).getId();

                if(userid.get(i).equals(Start.INSTANCE.getApi().getPrivateChannels().get(x).getUser().getId())){
                    Start.INSTANCE.getApi().getPrivateChannelById(id).editMessageEmbedsById(messagesid.get(i), builder.build()).queue();

                    Reaction.upload(userid.get(i), messagesid.get(i), lobbyid);
                    Start.INSTANCE.getApi().getPrivateChannelById(id).addReactionById(messagesid.get(i), "U+1F44D").queue();
                }
            }
        }
    }

}
