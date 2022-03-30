package main;

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
import listener.webpanel.StatusUpdate;
import mitteilungen.Mitteilungen;
import mysql.BotInfos;
import mysql.Log;
import mysql.TemporereChannel;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
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
import java.util.Timer;

public class Start {

    public static Start INSTANCE;
    public static  Timer timer;

    private JDA api;
    private String version = "1.3.3";

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
        status = true;
        api = JDABuilder.create(Passwort.getToken(), GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS)).build();
        api.getPresence().setStatus(OnlineStatus.ONLINE);
        api.getPresence().setActivity(Activity.competing("Version " + version));
        api.setAutoReconnect(true);

        Log.updateLog("DiscordBot wurde gestartet");

        this.cmdMan = new CommandManager();
        this.react = new ReactionManager();

        addListener(api);

        shutdown();

        timer = new Timer();

    }

    public static void startTimer(){
        timer.schedule(new Mitteilungen(), 0, 10000);
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
                            Log.updateLog("Bot wurde in den OfflineModus gesetzt", "Console");
                        }
                    }else if (line.equalsIgnoreCase("run")){
                        if (api != null){
                            api.getPresence().setStatus(OnlineStatus.ONLINE);
                            api.getPresence().setActivity(Activity.competing("Version " + version));
                            setStatus(true);
                            checkChannel();
                            System.out.println("Bot ist online!");
                            Log.updateLog("Bot wurde in den OnlineModus gesetzt.", "Console");
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
        builder.addEventListener(new Fertig());
        builder.addEventListener(new PlayerJoin());
        builder.addEventListener(new PlayerLeave());
        builder.addEventListener(new Timeout());
        builder.addEventListener(new ReactionRemoveListener());
        builder.addEventListener(new MessageRemove());
        builder.addEventListener(new StatusUpdate());
    }

    public static void checkChannel(){
        for (Category c : Start.INSTANCE.getApi().getGuildById("773995277840941067").getCategories()) {
            if(TemporereChannel.isExistCat(c.getId())){
                if(c.getMembers().size() == 0){
                    Start.INSTANCE.getApi().getGuildById("773995277840941067").getCategoryById(c.getId()).delete().queue();
                    Start.INSTANCE.getApi().getGuildById("773995277840941067").getVoiceChannelById(TemporereChannel.getVoiceChannel(c.getId())).delete().queue();
                    Start.INSTANCE.getApi().getGuildById("773995277840941067").getTextChannelById(TemporereChannel.getTextChannel(TemporereChannel.getTextChannel(c.getId()))).delete().queue();
                    TemporereChannel.removeChannelCat(c.getId());
                }
            }
        }

        boolean i = false;
        String id = BotInfos.getChannel();

        for (VoiceChannel c : Start.INSTANCE.getApi().getGuildById("773995277840941067").getVoiceChannels()) {
            if(c.getId().equals(id)){
                i = true;
            }
        }

        if(!i){
            Start.INSTANCE.getApi().getGuildById("773995277840941067").getCategoryById("774354036920418324").createVoiceChannel("Create Channel").queue((channel) -> {
                BotInfos.updateChannel(channel.getId(), id);
                Start.INSTANCE.getApi().getGuildById("773995277840941067").getCategoryById("774354036920418324").modifyVoiceChannelPositions().selectPosition(channel.getPosition() - (Start.INSTANCE.getApi().getGuildById("773995277840941067").getVoiceChannels().size() - channel.getParentCategory().getVoiceChannels().size())).moveTo(0).queue();
            });
        }

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
