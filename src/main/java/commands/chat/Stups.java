package commands.chat;

import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.STATICS;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;


public class Stups implements Command {


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public Game action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {

        String message = String.join(" ", args).split("@")[0];
        User memb = event.getMessage().getMentionedUsers().size() > 0 ?  event.getMessage().getMentionedUsers().get(0) : null;
        User author = event.getAuthor();
        TextChannel chan = event.getTextChannel();

        if (args.length < 2 || memb == null) {
            chan.sendMessage(new EmbedBuilder().setColor(Color.red).setDescription(help()).build()).queue();
            return null;
        }

        memb.openPrivateChannel().queue(pc -> pc.sendMessage(
                new EmbedBuilder()
                .setColor(Color.ORANGE)
                .setAuthor(author.getName(), null, author.getAvatarUrl())
                .setDescription(":love_letter:  Nachicht: \"" + message.substring(0, message.length() - 1) + "\"")
                .build()
        ).queue());

        /*
        if (args.length > 0) {

            if (args[0].startsWith("@")) {

                if (!event.getGuild().isMember(event.getMessage().getMentionedUsers().get(0))) {
                    event.getTextChannel().sendMessage(
                            ":warning:   This user is not a member of this guild!"
                    ).queue();
                }

                if (args.length > 1) {
                    message += "\n\n:love_letter:  Nachicht: \"";
                    for (String s : Arrays.asList(args).subList(1, args.length)) {
                        message += s + " ";
                    }
                    message += "\"";
                }

                PrivateChannel pc = event.getMessage().getMentionedUsers().get(0).openPrivateChannel().complete();
                pc.sendMessage(
                        ":point_right:  **Stups!** (von " + event.getAuthor().getAsMention() + ")" + message
                ).queue();

                event.getMessage().delete().queue();
            }
        } else
            event.getTextChannel().sendMessage(
                    ":warning:   Please mention a user from this guild you want to send a nudge!"
            ).queue();
        */


        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "**USAGE**:\n `-stups <Message> <@user>`";
    }

    @Override
    public String description() {
        return "Give someone a nudge";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.chatutils;
    }

    @Override
    public int permission() {
        return 0;
    }
}
