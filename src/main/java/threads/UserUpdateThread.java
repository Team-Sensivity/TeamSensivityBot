package threads;

import mysql.webpanel.CreateAccount;
import mysql.webpanel.Infos;
import mysql.webpanel.TokenErstellen;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.util.List;

public class UserUpdateThread implements Runnable{
    List<Member> memebers;

    public UserUpdateThread(List<Member> members){
        this.memebers = members;
    }

    @Override
    public void run(){
        System.out.println("Thread wurde gestrtet");
        for (Member member : memebers) {
            User.Profile p = member.getUser().retrieveProfile().complete();

            if (TokenErstellen.isRegister(member.getId())) {
                Infos.updateAll(member.getEffectiveAvatarUrl(), member.getUser().getName(), p.getBannerUrl(), member.getId());
                Infos.updateRole(member);
                CreateAccount.uploadRollen(member.getId());
            } else {
                CreateAccount.create(member.getEffectiveName(), member.getId(), member.getAvatarUrl(), CreateAccount.getRole(member.getId()), p.getBannerUrl());
            }
        }
        System.out.println("Thread ist fertig");
    }
}
