/*
 * Copyright (c) 2019. Dan Motpan
 * MIT Licence
 */

package commands.administration;

import commands.Command;
import core.Perms;
import core.UpdateClient;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.text.ParseException;

import utils.STATICS;




public class Update implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public Game action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {

        if (!Perms.isOwner(event.getAuthor(), event.getTextChannel())) return null;

        UpdateClient.manualCheck(event.getMessage().getTextChannel());

        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "USAGE: -UpdateClient";
    }

    @Override
    public String description() {
        return "UpdateClient discord bot.";
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
