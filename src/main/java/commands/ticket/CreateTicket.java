package commands.ticket;

import commands.types.ServerCommand;
import mysql.Connect;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTicket implements ServerCommand {

    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        String[] args = message.getContentDisplay().split(" ");
        String text = "";

        for(int i = 2; i < args.length; i++){
            text = text + " " + args[i];
        }

        try {
            Connection con = Connect.getConnection();
            PreparedStatement posted = con.prepareStatement("INSERT INTO TicketSystem (Betreff, Nachricht, userid) VALUES ('"+ args[1] + "', '"+ text +"', '"+ m.getId() +"')");

            posted.executeUpdate();
            con.close();
            channel.purgeMessagesById(channel.getLatestMessageId());
            channel.sendMessage("Ticket wurde erstellt!").queue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
