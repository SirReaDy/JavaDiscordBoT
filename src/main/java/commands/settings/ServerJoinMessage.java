package commands.settings;

import commands.Command;
import core.SSSS;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.MSGS;
import utils.STATICS;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;


public class ServerJoinMessage implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public Game action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {

        if (core.Perms.check(permission(), event)) return null;

        if (args.length < 1) {
            event.getTextChannel().sendMessage(MSGS.error().setDescription(help()).build()).queue();
            return null;
        }

        StringBuilder sb = new StringBuilder();
        Arrays.stream(args).forEach(s -> sb.append(s + " "));

        SSSS.setSERVERJOINMESSAGE(sb.toString().substring(0, sb.toString().length() - 1), event.getGuild());
        event.getTextChannel().sendMessage(MSGS.success().setDescription("Server join message successfully changed to `" + sb.toString().substring(0, sb.toString().length() - 1) + "`.").build()).queue();
        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "USAGE: -joinmsg <message>\n" +
                "Set message to `OFF` to disable server join message.\n" +
                "Use `[USER]` to mention joined user and `[GUILD]` to enter guild name in message.";
    }

    @Override
    public String description() {
        return "Set the guild join message.";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.settings;
    }

    @Override
    public int permission() {
        return 3;
    }
}
