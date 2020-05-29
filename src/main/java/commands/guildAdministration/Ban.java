/*
 * Copyright (c) 2019. Dan Motpan
 * MIT Licence
 */

package commands.guildAdministration;


import java.util.List;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;

import commands.Command;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.Logger;


import java.io.IOException;
import java.text.ParseException;



public class Ban implements Command {
    private final int deldays = 7;
    @Override
    public boolean called(String[] args, MessageReceivedEvent event){
        return false;
    }

    @Override
    public Game action(String[] args, MessageReceivedEvent event) throws ParseException, IOException{

        if(args.length == 0) {
            event.getTextChannel().sendMessage("💥 +  You need to mention 1 or more members to ban!").queue ();
        }

        else
        {
            Guild guild = event.getGuild();
            Member selfMember = guild.getSelfMember();

            //Check if the bot have permission to kick.
            if (!selfMember.hasPermission(Permission.BAN_MEMBERS)) {
                event.getTextChannel().sendMessage("💥 I need to have **Ban Members** Permission to ban members.").queue();
                return null;
            } else if(!event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                event.getTextChannel().sendMessage(" 💥  You need to have **Ban Members** Permission to ban members.").queue();
                return null;
            }

            List<User> mentionedUsers = event.getMessage().getMentionedUsers();
            System.out.println ( "BanCommand" +  "Called to ban " + mentionedUsers.size() + " users.");

            for (User user : mentionedUsers)
            {
                Member member = guild.getMember(user);
                if(!selfMember.canInteract(member))
                {
                    event.getTextChannel().sendMessage( "💥  Cannot ban member: " + member.getEffectiveName()
                            + ", they are in a higher role than I am!").queue();
                    return null;
                }

                guild.getController().ban(member, deldays).queue(
                        success -> event.getTextChannel().sendMessage("✔ Banned " + member.getEffectiveName() + "! Don't come back!\n").queue(),
                        error ->
                        {
                            if (error instanceof PermissionException)
                            {
                                PermissionException pe = (PermissionException) error;
                                Permission missingPermission = pe.getPermission();

                                event.getTextChannel().sendMessage("✔ I do not have the permission to ban " + member.getEffectiveName()
                                        + "\nRequired permission: `" + missingPermission.getName() +"`").queue();
                            }
                            else
                            {
                                event.getTextChannel().sendMessage("✔ Unknown error while banning " + member.getEffectiveName()
                                        + ": <" + error.getClass().getSimpleName() + ">: " + error.getMessage()).queue();
                            }
                        });
            }
        }
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
