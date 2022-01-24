package commands.webpanel;

import commands.types.ServerCommand;
import main.Start;
import mysql.webpanel.CreateAccount;
import mysql.webpanel.Infos;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import threads.UserUpdateThread;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateUsers implements ServerCommand {
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {

        if (m.getId().equals("422148236875137059")) {
            Guild g = Start.INSTANCE.getApi().getGuilds().get(0);
            List<Member> members = g.getMembers();

            ArrayList<Member> [] memberLists = new ArrayList[4];

            for (int i=0;i<memberLists.length;i++){
                memberLists[i] = new ArrayList<>();
            }

            if(members.size() >= 4){
                int max = members.size() / 4;
                for(int i=0; i < 4; i++){
                    if (i < 3){
                        for (int j = i * max; j < (i +1) * max; j++){
                            memberLists[i].add(members.get(j));
                        }
                    }else {
                        for (int j = i * max; j < members.size(); j++){
                            memberLists[i].add(members.get(j));
                        }
                    }
                }

                Thread t1 = new Thread(new UserUpdateThread(memberLists[0]));
                Thread t2 = new Thread(new UserUpdateThread(memberLists[1]));
                Thread t3 = new Thread(new UserUpdateThread(memberLists[2]));
                Thread t4 = new Thread(new UserUpdateThread(memberLists[3]));

                t1.start();
                t2.start();
                t3.start();
                t4.start();
            }
        }else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor("Team Sensivity");
            builder.setColor(Color.RED);
            builder.setTitle("**Keine Berechtigung**");
            builder.setDescription("Du hast keine Berechtigung für diesen Command");
            builder.setThumbnail("https://sensivity.michel929.de/webpanel/assets/images/logo.png");

            channel.sendMessageEmbeds(builder.build()).queue();
        }
    }
}
