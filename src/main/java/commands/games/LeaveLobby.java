package commands.games;

import commands.types.PrivateCommand;
import funktionen.game.EditMessages;
import mysql.Reaction;
import mysql.games.Lobby;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.List;

public class LeaveLobby implements PrivateCommand {
    @Override
    public void performCommand(User m, PrivateChannel channel, Message message) {
        if(Lobby.userExist(m.getId())){
            List<String> infos = Lobby.leaveLobby(m.getId());
            channel.sendMessage("Du hast die Lobby verlassen!").queue();
            EditMessages.editJoinMessage(infos.get(0), infos.get(1), "https://sensivity.michel929.de/webpanel/assets/images/karten/Spielmodis/"+ infos.get(1).toLowerCase() +".png");
            Reaction.delete(m.getId());
        }else {
            channel.sendMessage("Du bist in keiner Lobby!!").queue();
        }
    }
}
