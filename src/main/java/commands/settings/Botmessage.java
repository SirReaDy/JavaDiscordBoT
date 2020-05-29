package commands.settings;

import commands.Command;
import core.Perms;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.WebSocketCode;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.commons.net.imap.IMAPClient;
import org.json.JSONObject;
import utils.STATICS;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Botmessage implements Command {


    private static boolean custom = false;
    private static int members = 0;

    private static void count() {
        members++;
    }

    private static Timer timer = new Timer();


    public static void setSupplyingMessage(JDA jda){

        if (!custom) {
            jda.getGuilds ().forEach (g -> g.getMembers ().forEach (m -> count ()));


            new Timer().schedule(new TimerTask() {
                @Override
                public void run(){
                    jda.getPresence ().setGame (Game.of (Game.GameType.WATCHING, "People type  " + STATICS.PREFIX + "help"));
                }
            }, 0, 60000);//60000


            new Timer().schedule(new TimerTask() {
                @Override
                public void run(){
                    jda.getPresence ().setGame (Game.of (Game.GameType.WATCHING, "Version " + STATICS.VERSION));
                }
            }, 0, 60000);


            new Timer().schedule(new TimerTask() {
                @Override
                public void run(){
                    jda.getPresence ().setGame (Game.of (Game.GameType.LISTENING, "to " +members + " members"));
                }
            }, 0, 60000);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run(){
                    jda.getPresence ().setGame (Game.of (Game.GameType.DEFAULT, "on " +jda.getGuilds().size () + " servers"));
                }
            }, 0, 60000);





        }}




    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public Game action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {

        if (!Perms.isOwner(event.getAuthor(), event.getTextChannel())) return null;

        custom = true;

        String messageString = String.join(" ", args);

        if (messageString.equals("off")) {
            custom = false;
            event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN).setDescription("Successfully set botmsg to standard setting!").build()).queue();
            return null;
        }

        event.getJDA ().getPresence ().setGame (Game.watching (messageString + "| !help | v." + STATICS.VERSION));
       // event.getJDA().getPresence().setGame(Game.of(messageString + " | -help | v." + STATICS.VERSION));
        event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN).setDescription("Successfully set bot message to `" + messageString + "`!").build()).queue();

        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "USAGE: !botmsg <message>";
    }

    @Override
    public String description() {
        return "Set the \"Playing ...\" message of the bot.";
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
