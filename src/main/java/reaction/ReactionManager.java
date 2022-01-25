package reaction;

import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Member;
import reaction.add.DaumenHoch;
import reaction.add.DaumenRunter;
import reaction.remove.DaumenHochR;
import reaction.remove.DaumenRunterR;
import reaction.type.AddPrivateReaction;
import reaction.type.AddReaction;
import reaction.type.RemovePrivateReaction;
import reaction.type.RemoveReaction;

import java.util.concurrent.ConcurrentHashMap;

public class ReactionManager {
    public ConcurrentHashMap<Emoji, AddReaction> addReaction;
    public ConcurrentHashMap<Emoji, RemoveReaction> removeReaction;
    public ConcurrentHashMap<Emoji, AddPrivateReaction> addPrivateReaction;
    public ConcurrentHashMap<Emoji, RemovePrivateReaction> removePrivateReaction;

    public ReactionManager(){
        this.addReaction = new ConcurrentHashMap<>();
        this.addPrivateReaction = new ConcurrentHashMap<>();
        this.removeReaction = new ConcurrentHashMap<>();
        this.removePrivateReaction = new ConcurrentHashMap<>();

        this.addReaction.put(Emoji.fromUnicode("U+1F44D"), new DaumenHoch());
        this.addReaction.put(Emoji.fromUnicode("U+1F44E"), new DaumenRunter());

        this.removeReaction.put(Emoji.fromUnicode("U+1F44E"), new DaumenRunterR());
        this.removeReaction.put(Emoji.fromUnicode("U+1F44D"), new DaumenHochR());
    }

    public void perform(String message, Member member, Channel channel, Emoji emoji){
        AddReaction reaction;
        if((reaction = this.addReaction.get(emoji)) != null){
            reaction.performReaction(message, member, channel);
        }
    }

    public void performR(String message, Member member, Channel channel, Emoji emoji){
        RemoveReaction reaction;
        if((reaction = this.removeReaction.get(emoji)) != null){
            reaction.performReaction(message, member, channel);
        }
    }
}