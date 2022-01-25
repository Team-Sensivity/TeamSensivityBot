package main;

import funktionen.ResetOnlineTime;
import geheim.Passwort;
import listener.*;
import listener.punktesystem.ChannelRemove;
import listener.punktesystem.SprachchatConnect;
import listener.punktesystem.SprachchatDisconnect;
import listener.punktesystem.SprachchatMove;
import listener.rollen.*;
import listener.webpanel.AvatarUpdate;
import listener.webpanel.NameUpdate;
import listener.webpanel.PlayerLeave;
import mysql.BotInfos;
import mysql.games.KartenUpload;
import mysql.games.Lobby;
import mysql.TemporereChannel;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import reaction.ReactionAddListener;
import reaction.ReactionManager;
import reaction.ReactionRemoveListener;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Timer;

public class Start {

    public static Start INSTANCE;

    private JDA api;

    private CommandManager cmdMan;
    private ReactionManager react;
    private boolean status;

    public static void main(String[] args) {
        try {
            new Start();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public Start() throws LoginException, IllegalArgumentException {

        INSTANCE = this;
        status = false;
        api = JDABuilder.create(Passwort.getToken(), GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS)).build();
        api.getPresence().setStatus(OnlineStatus.OFFLINE);
        this.cmdMan = new CommandManager();
        this.react = new ReactionManager();

        addListener(api);

        System.out.println("Bot ist an muss aber noch mit &start gestartet werden...");

        shutdown();

        Lobby.clearTable();
        KartenUpload.clearTable();
        KartenUpload.clearTable2();
        isSo();
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
                            setStatus(false);
                            System.out.println("Bot ist offline!");
                        }
                    }else if (line.equalsIgnoreCase("run")){
                        if (api != null){
                            api.getPresence().setStatus(OnlineStatus.ONLINE);
                            setStatus(true);
                            checkChannel();
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

    public void addListener(JDA builder){
        builder.addEventListener(new CommandListener());
        builder.addEventListener(new ReactionAddListener());
        builder.addEventListener(new RolleErstellen());
        builder.addEventListener(new RolleRemove());
        builder.addEventListener(new RolleColor());
        builder.addEventListener(new RollePriority());
        builder.addEventListener(new AvatarUpdate());
        builder.addEventListener(new NameUpdate());
        builder.addEventListener(new RollenAddMember());
        builder.addEventListener(new SprachchatConnect());
        builder.addEventListener(new SprachchatDisconnect());
        builder.addEventListener(new RollenRemoveFromMember());
        builder.addEventListener(new SprachchatMove());
        builder.addEventListener(new ChannelRemove());
        builder.addEventListener(new PlayerJoin());
        builder.addEventListener(new PlayerLeave());
        builder.addEventListener(new Timeout());
        builder.addEventListener(new ReactionRemoveListener());
        builder.addEventListener(new MessageRemove());
    }

    public static void checkChannel(){
        for (Category c : Start.INSTANCE.getApi().getGuilds().get(0).getCategories()) {
            if(TemporereChannel.isExistCat(c.getId())){
                if(c.getMembers().size() == 0){
                    Start.INSTANCE.getApi().getGuilds().get(0).getCategoryById(c.getId()).delete().queue();
                    Start.INSTANCE.getApi().getGuilds().get(0).getVoiceChannelById(TemporereChannel.getVoiceChannel(c.getId())).delete().queue();
                    Start.INSTANCE.getApi().getGuilds().get(0).getTextChannelById(TemporereChannel.getTextChannel(TemporereChannel.getTextChannel(c.getId()))).delete().queue();
                    TemporereChannel.removeChannelCat(c.getId());
                }
            }
        }

        boolean i = false;
        String id = BotInfos.getChannel();

        for (VoiceChannel c : Start.INSTANCE.getApi().getGuilds().get(0).getVoiceChannels()) {
            if(c.getId().equals(id)){
                i = true;
            }
        }

        if(!i){
            Start.INSTANCE.getApi().getGuilds().get(0).getCategoryById("774354036920418324").createVoiceChannel("Create Channel").queue((channel) -> {
                BotInfos.updateChannel(channel.getId(), id);
                Start.INSTANCE.getApi().getGuilds().get(0).getCategoryById("774354036920418324").modifyVoiceChannelPositions().selectPosition(channel.getPosition() - (Start.INSTANCE.getApi().getGuilds().get(0).getVoiceChannels().size() - channel.getParentCategory().getVoiceChannels().size())).moveTo(0).queue();
            });
        }

    }

    public void isSo(){
        Timer timer = new Timer();
        Calendar date = Calendar.getInstance();
        date.set(
                Calendar.DAY_OF_WEEK,
                Calendar.SUNDAY
        );
        date.set(Calendar.HOUR, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        //Schedule to run every Sunday in midnight
        timer.schedule(
                new ResetOnlineTime(),
                date.getTime(),
                1000 * 60 * 60 * 24 * 7
        );
    }

    public CommandManager getCmdMan() {
        return cmdMan;
    }

    public ReactionManager getReact() {
        return react;
    }

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public JDA getApi() {
        return api;
    }
}
