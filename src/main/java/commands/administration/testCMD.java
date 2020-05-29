/*
 * Copyright (c) 2019. Dan Motpan
 * MIT Licence
 */

package commands.administration;

import commands.Command;
import core.Perms;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.STATICS;


public class testCMD implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public Game action(String[] args, MessageReceivedEvent event) {

        if (!Perms.isOwner(event.getAuthor(), event.getTextChannel())) return null;

        System.out.println("TEST");

        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.administration;
    }

    @Override
    public int permission() {
        return 3;
    }
}
