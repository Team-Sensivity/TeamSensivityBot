package main;

import commands.BestMeme;
import commands.Exit;
import commands.ResetNickname;
import commands.Start;
import commands.channel.Save;
import commands.channel.ServerStatus;
import commands.webpanel.*;
import commands.dbd.PerksInventar;
import commands.punktesystem.CreateLevel;
import commands.punktesystem.GetPoints;
import commands.types.PrivateCommand;
import commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.*;

import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {
    public ConcurrentHashMap<String, ServerCommand> commands;
    public ConcurrentHashMap<String, PrivateCommand> commandsp;

    public CommandManager(){
        this.commands = new ConcurrentHashMap<>();
        this.commandsp = new ConcurrentHashMap<>();

        this.commandsp.put("register", new Register());
        this.commandsp.put("login", new Login());
        this.commandsp.put("token", new Token());
        this.commands.put("perks", new PerksInventar());
        this.commands.put("points", new GetPoints());
        this.commandsp.put("level", new CreateLevel());
        this.commandsp.put("exit", new Exit());
        this.commandsp.put("start", new Start());
        this.commands.put("save", new Save());
        this.commands.put("update", new UpdateUsers());
        this.commands.put("best", new BestMeme());
        this.commands.put("status", new ServerStatus());
        this.commands.put("login", new LoginS());
        this.commands.put("token", new TokenS());
        this.commands.put("nickname", new ResetNickname());
    }

    public boolean perform(String command, Member m, TextChannel channel, Message message){

        ServerCommand cmd;
        if((cmd = this.commands.get(command.toLowerCase())) != null){
            cmd.performCommand(m, channel, message);
            return true;
        }
        return false;
    }

    public boolean perform(String command, User m, PrivateChannel channel, Message message){

        PrivateCommand cmd;
        if((cmd = this.commandsp.get(command.toLowerCase())) != null){
            cmd.performCommand(m, channel, message);
            return true;
        }
        return false;
    }
}
