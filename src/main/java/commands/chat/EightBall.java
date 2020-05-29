package commands.chat;

import commands.Command;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.STATICS;

import java.util.Random;

public class EightBall implements Command {

    public static String HELP = ":warning:  USAGE: ` ~8ball <question> to ask the magic 8ball a special question!`";

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    public Game action(String[] args, MessageReceivedEvent event) {

        String[] answers = {
                "It is certain.",
                "It is decidedly so.",
                "Without a doubt.",
                "Yes - definitely.",
                "You may rely on it.",
                "As I see it, yes",
                "Most likely.",
                "Outlook good.",
                "Yes.",
                "Signs point to yes.",
                "Reply hazy, try again.",
                "Ask again later.",
                "Better not tell you now.",
                "Cannot predict now.",
                "Concentrate and ask again.",
                "Don't count on it.",
                "My reply is no.",
                "My sources say no.",
                "Outlook not so good.",
                "Very doubtful."
        };

        Integer randInt = new Random().nextInt(6);

        String msg = "";
        for (String a : args) {
            msg += a + " ";
        }

        event.getTextChannel().sendMessage(
                " 🎱 Question: " + msg + "\n" +
                "Answer: " + answers[randInt]
        ).queue();


        return null;
    }

    public void executed(boolean success, MessageReceivedEvent event) {

    }

    public String help() {
        return HELP;
    }

    @Override
    public String description() {
        return "Ask the bot a question with yes/no answer";
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
