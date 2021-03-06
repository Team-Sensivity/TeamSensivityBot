package main;

import geheim.BotToken;
import listeners.PlayerJoin;
import listeners.SlashCommand;
import listeners.dashboard.AvatarChange;
import listeners.dashboard.NameChange;
import listeners.dashboard.RoleCreate;
import listeners.dashboard.StatusChange;
import mysql.BotInfos;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apache.commons.mail.EmailException;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start {

    public static Start INSTANCE;
    public static String GUILD_ID = BotInfos.getBotInfos("guild_id"), STANDART_ROLE = BotInfos.getBotInfos("discord_role");

    private JDA api;

    public static void main(String[] args) {
        try {
            new Start();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public Start() throws LoginException, IllegalArgumentException {

        INSTANCE = this;

        api = JDABuilder.create(BotToken.token, GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS) ).build();

        listeners();
        commands();

        System.out.println("Bot ist online!");

        shutdown();
    }

    public void shutdown(){
        new Thread(() -> {

            String line = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try{
                while ((line = reader.readLine()) != null){

                    if(line.equalsIgnoreCase("exit")){
                        if(api != null){
                            api.getPresence().setStatus(OnlineStatus.OFFLINE);
                            System.out.println("Bot ist offline!");
                        }
                    }else if (line.equalsIgnoreCase("start")){
                        if (api != null){
                            api.getPresence().setStatus(OnlineStatus.ONLINE);
                            System.out.println("Bot ist online!");
                        }
                    }else if(line.equalsIgnoreCase("shutdown")){
                        if (api != null){
                            api.shutdown();
                            System.out.println("Bot ist aus!");
                        }
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }

    private void listeners(){
        api.addEventListener(new SlashCommand());
        api.addEventListener(new PlayerJoin());

        api.addEventListener(new NameChange());
        api.addEventListener(new AvatarChange());
        api.addEventListener(new StatusChange());
        api.addEventListener(new RoleCreate());

    }

    private void commands(){
        api.upsertCommand("connect", "Hiermit verbindest du deinen DiscordAccount mit dem Dashboard.").queue();
        api.upsertCommand("login", "Hiermit kannst du dich im Dashboard anmelden.").queue();
    }

    public JDA getApi() {
        return api;
    }
}
