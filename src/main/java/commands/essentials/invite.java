/*
 * Copyright (c) 2019. Dan Motpan
 * MIT Licence
 */

package commands.essentials;

import commands.Command;


import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;



public class invite implements Command {



    @Override
    public boolean called(String[] args, MessageReceivedEvent event){
        return false;
    }

    @Override
    public Game action(String[] args, MessageReceivedEvent event) throws ParseException, IOException{

        Member memb;
        if (args.length > 0) {
            memb = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
        } else {
            memb = event.getMember();
        }

        String ROLES = "";
        String AVATAR = memb.getUser().getAvatarUrl();
        String BOTLINK = "[Invite S!r.ReaDy Bot](https://discordapp.com/oauth2/authorize?client_id=535480113454645249&permissions=1898982486&scope=bot)";
        String link  = event.getTextChannel ().createInvite ().complete ().getURL ();
        int time = 24;


        for ( Role r : memb.getRoles() ) {
            ROLES += r.getName() + ", ";
        }
        if (ROLES.length() > 0)
            ROLES = ROLES.substring(0, ROLES.length()-2);
        else
            ROLES = "No roles on this server.";


        PrivateChannel pc = event.getAuthor().openPrivateChannel().complete();


        pc.sendMessage(

                new EmbedBuilder().setColor(new Color(134, 255, 0))
                        .setDescription("✉ **Generatet invite link " + memb.getUser().getName() + ":**")

                        .addField ("Roles",ROLES,false)
                        .addField("Guild Permission Level", core.Perms.getLvl(memb) + "", false)
                        .addField("BoT Link", BOTLINK, false)
                        .addField("Invite Link", link, false)
                        .addField("Expiration Time", time + "h", true)
                        .addField("Avatar-URL", AVATAR, false).build ()).queue ();


        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event){

    }

    @Override
    public String help(){
        return null;
    }

    @Override
    public String description(){
        return null;
    }

    @Override
    public String commandType(){
        return null;
    }

    @Override
    public int permission(){
        return 0;
    }
}