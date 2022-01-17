package games;

import funktionen.Bilder;
import funktionen.GenerateToken;
import funktionen.game.EditMessages;
import main.Start;
import mysql.Reaction;
import mysql.games.KartenUpload;
import mysql.games.Lobby;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Emote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MauMau {

    public static void startMauMAu(String lobbyid){
        Karten karten = new Karten();
        List<String> users = Lobby.getUserId(lobbyid);
        List<String> deck = new ArrayList<>();

        int r = (int) (Math.random() * 31);

        deck.add(karten.maumau.get(r));
        karten.maumau.remove(r);

        KartenUpload.uploadDeck(deck, lobbyid);

        try {
            Bilder.createImage(deck, lobbyid, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String u: users) {
            List<String> cards = new ArrayList<>();


            for (int i = 0; i < 6; i++){
                r = (int) (Math.random() * (karten.maumau.size() - 1));

                cards.add(karten.maumau.get(r));
                karten.maumau.remove(r);
            }

            try {
                Bilder.createImage(cards, u, 1);
                KartenUpload.uploadDeck(cards, u);
            } catch (IOException e) {
                e.printStackTrace();
            }

            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Offene Karte");
            builder.setColor(0x6DE194);
            builder.setAuthor("GameManager");
            builder.setImage(KartenUpload.getLastCard(lobbyid, 1));
            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/karten/Spielmodis/maumau.png");

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setColor(0x6DE194);
            embedBuilder.setImage(KartenUpload.getLastCard(u, 1));
            embedBuilder.setTitle("**Deine Karten**");
            embedBuilder.setFooter("Warte auf anderen Spieler!");

            for(int x = 0; x < Start.INSTANCE.getApi().getPrivateChannels().size(); x++){
                String id = Start.INSTANCE.getApi().getPrivateChannels().get(x).getId();

                if(u.equals(Start.INSTANCE.getApi().getPrivateChannels().get(x).getUser().getId())){
                    Start.INSTANCE.getApi().getPrivateChannelById(id).sendMessageEmbeds(builder.build()).queue((message) -> {
                        Lobby.updateDeckId(u, message.getId());
                    });
                    Start.INSTANCE.getApi().getPrivateChannelById(id).sendMessageEmbeds(embedBuilder.build()).queue((message) -> {
                        Lobby.updateMessageId(u, message.getId());
                    });
                }
            }
        }

        Lobby.reinfolge(lobbyid);
        playMauMau(lobbyid, 1);
    }

    public static void playMauMau(String lobbyid, int number){
        String userid = Lobby.getUserID(lobbyid, number);
        String karten = Lobby.getMessage(userid);

        int anzahl = KartenUpload.getCards(userid);

        if(anzahl != 0){

            for(int x = 0; x < Start.INSTANCE.getApi().getPrivateChannels().size(); x++){
                String id = Start.INSTANCE.getApi().getPrivateChannels().get(x).getId();

                if(userid.equals(Start.INSTANCE.getApi().getPrivateChannels().get(x).getUser().getId())){
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setFooter("Du bist dran! Wähle eine Karte");
                    embedBuilder.setImage(KartenUpload.getLastCard(userid, 1));
                    embedBuilder.setTitle("**Deine Karten**");
                    embedBuilder.setColor(0x6DE194);

                    Start.INSTANCE.getApi().getPrivateChannelById(id).editMessageEmbedsById(karten, embedBuilder.build()).queue();

                    reactionA(id, karten, userid, 0, lobbyid);

                }
            }

        }else {

        }
    }

    public static void reactionA(String id, String karten, String user, int aktive, String lobbyid){
        List<String> cardArt = KartenUpload.getCardArt(user);
        List<String> deck = KartenUpload.getCardArt(lobbyid);

        Lobby.updateAktiv(user, aktive);

        try {
            Bilder.createAktivImage(KartenUpload.getKarts(user), user, 1, aktive);
        } catch (IOException e) {
            e.printStackTrace();
        }

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setFooter("Du bist dran! Wähle eine Karte");
        embedBuilder.setImage(KartenUpload.getLastCard(user, 1));
        embedBuilder.setTitle("**Deine Karten**");
        embedBuilder.setColor(0x6DE194);

        Start.INSTANCE.getApi().getPrivateChannelById(id).editMessageEmbedsById(karten, embedBuilder.build()).queue();

        System.out.println(cardArt.get(aktive));
        System.out.println(deck.get(0) + " DECK");

        Start.INSTANCE.getApi().getPrivateChannelById(id).addReactionById(karten, "U+2B05").queue();

        if(cardArt.get(aktive).charAt(0) == deck.get(0).charAt(0) || cardArt.get(aktive).charAt(1) == deck.get(0).charAt(1) || cardArt.get(aktive).charAt(1) == 'J') {
            Start.INSTANCE.getApi().getPrivateChannelById(id).addReactionById(karten, "U+2B06").queue();
        }
        Start.INSTANCE.getApi().getPrivateChannelById(id).addReactionById(karten, "U+27A1").queue();

        Reaction.upload(user, karten, lobbyid);
    }
}
