/*
 * Copyright (c) 2019. Dan Motpan
 * MIT Licence
 */

package commands.administration;

import commands.Command;
import core.Perms;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.MSGS;
import utils.STATICS;

import java.io.IOException;
import java.text.ParseException;


public class Leaveserver implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public Game action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        if (Perms.check(permission(), event)) return null;

        if (args.length < 1) {
            event.getTextChannel().sendMessage(MSGS.error().setDescription(help()).build());
            return null;
        }

        try {
            Guild leaveguild = event.getJDA().getGuildById(args[0]);
            leaveguild.leave().queue(t -> event.getTextChannel().sendMessage(MSGS.success().setDescription(String.format(
                    "Successfully left guild `%s` (`%s`).", leaveguild.getName(), leaveguild.getId()
            )).build()).queue());
        } catch (Exception e) {
            event.getTextChannel().sendMessage(MSGS.error().setDescription(String.format("Could not parse ID `%s`.", args[0])).build()).queue();
        }
        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "**USAGE:**\n`-leaveserver <guildID>`";
    }

    @Override
    public String description() {
        return "Let the bot leave a specific guild by client side";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.administration;
    }

    @Override
    public int permission() {
        return 4;
    }
}
