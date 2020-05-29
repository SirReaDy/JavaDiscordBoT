package commands.guildAdministration;

import commands.Command;
import core.SSSS;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.MSGS;
import utils.STATICS;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Blacklist implements Command {


    public static boolean check(User user, Guild guild) {
        List<String> blackList = SSSS.getBLACKLIST(guild);
        if (blackList.contains(user.getId())) {
            user.openPrivateChannel().complete().sendMessage(MSGS.error().setDescription("Sorry, but you are currently not allowed to use this bot's commands!\nMessage a support or the server owner to remove you from blacklist.").build()).queue();
            return false;
        }
        return true;
    }


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public Game action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {

        if (core.Perms.check(permission(), event)) return null;

        ArrayList<String> blackList = new ArrayList<>(SSSS.getBLACKLIST(event.getGuild()));
        User victim;


        if (args.length > 0) {
            if (event.getMessage().getMentionedUsers().size() > 0)
                victim = event.getMessage().getMentionedUsers().get(0);
            else {
                event.getTextChannel().sendMessage(MSGS.error().setDescription("Please mention a valid user to blacklist.").build()).queue();
                return null;
            }
            if (blackList.contains(victim.getId())) {
                blackList.remove(victim.getId());
                event.getTextChannel().sendMessage(MSGS.success().setDescription("Successfully removed " + victim.getAsMention() + " from blacklist.").build()).queue();
            } else {
                blackList.add(victim.getId());
                event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.orange).setDescription(event.getAuthor().getAsMention() + " added " + victim.getAsMention() + " to blacklist.").build()).queue();
            }
            System.out.println(blackList.size());
            SSSS.setBLACKLIST(blackList, event.getGuild());
        } else {
            String out = blackList.stream().map(id -> ":white_small_square:   " + event.getGuild().getMemberById(id).getEffectiveName()).collect(Collectors.joining("\n"));
            event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("**BLACKLISTED MEMBERS**\n\n" + out).build()).queue();
        }
        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "**USAGE: **\n`-blacklist <@menrion>`";
    }

    @Override
    public String description() {
        return "Disallow users to use the bots commands.";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.guildadmin;
    }

    @Override
    public int permission() {
        return 2;
    }
}
