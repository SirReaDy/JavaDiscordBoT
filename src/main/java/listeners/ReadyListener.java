package listeners;

import commands.chat.WarframeAlerts;
import core.startArgumentHandler;
import core.UpdateClient;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import utils.Logger;
import utils.STATICS;

import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class ReadyListener extends ListenerAdapter {

    static ReadyEvent readyEvent;

    private static void handleStartArgs() {

        String[] args = startArgumentHandler.args;

        if (args.length > 0) {
            switch (args[0]) {

                case "-restart":
                    for (Guild g : readyEvent.getJDA().getGuilds()) {
                        g.getDefaultChannel ().sendMessage (
                                ":ok_hand:  Bot successfully restarted!"
                        ).queue();
                    }
                    break;

                case "-update":
                    for (Guild g : readyEvent.getJDA().getGuilds()) {
                        g.getDefaultChannel ().sendMessage (
                                ":ok_hand:  Bot successfully updated to version v." + STATICS.VERSION + "!\n\n" +
                                        "**Changelogs:** ####\n" +
                                        "Github Repository: ##"
                        ).queue();
                    }
                    break;

            }
        }

    }

    @Override
    public void onReady(ReadyEvent event) {

        StringBuilder sb = new StringBuilder();
        event.getJDA().getGuilds().forEach(guild -> sb.append("|  - \"" + guild.getName() + "\" - {@" + guild.getOwner().getUser().getName() + "#" + guild.getOwner().getUser().getDiscriminator() + "} - [" + guild.getId() + "] \n"));


        System.out.println (Logger.Red + String.format (
             "#==================================================#" +
                     "\n"+
      "#     ____  _      ____            ____            #\n" +
              "#    / ___|| |_ __|  _ \\ ___  __ _|  _ \\ _   _     #\n" +
              "#    \\___ \\| | '__| |_) / _ \\/ _` | | | | | | |    #\n" +
              "#     ___) |_| |_ |  _ <  __/ (_| | |_| | |_| |    #\n" +
              "#    |____/(_)_(_)|_| \\_\\___|\\__,_|____/ \\__, |    #\n" +
              "#                                        |___/     #\n" +
              "#==================================================#" +
              "\n"
        ));
        System.out.println (Logger.Black + String.format (
                "\n\n" +
                "#------------------------------------------------------------------------- - - -  -  -  -   -\n" +
                "| %s - v.%s (JDA: v.%s)\n" +
                "#------------------------------------------------------------------------- - - -  -  -  -   -\n" +
                "| Running on %s guilds: \n" +
                "%s" +
                "#------------------------------------------------------------------------- - - -  -  -  -   -\n" +
                "| Latest run: \n" +
                "|  %s    \n"+
                "#------------------------------------------------------------------------- - - -  -  -  -   -\n\n",
        Logger.Cyan + Logger.Bold + "S!r.ReaDy BoT" + Logger.Reset, STATICS.VERSION, "3.3.1_276", event.getJDA().getGuilds().size(), sb.toString(),
        Logger.Green + Logger.Bold + STATICS.DATE + Logger.Reset,sb.toString()
        ));

        if (STATICS.BOT_OWNER_ID == 0) {
            Logger.ERROR(
                    "#######################################################\n" +
                    "# PLEASE INSERT YOUR DISCORD USER ID IN SETTINGS.json  #\n" +
                    "# AS ENTRY 'BOT_OWNER_ID' TO SPECIFY THAT YOU ARE THE #\n" +
                    "# BOTS OWNER!                                         #\n" +
                    "#######################################################"
            );
        }

        if (STATICS.TOKEN == " ") {
            Logger.ERROR(
                    "#######################################################\n" +
                            "# PLEASE INSERT YOUR DISCORD TOEKN ID IN SETTINGS.json  #\n" +
                            "# AS ENTRY 'TOEKN' TO SPECIFY THE BOT USER #\n" +
                            "#######################################################"
            );
        }
        commands.settings.Botmessage.setSupplyingMessage(event.getJDA());

        WarframeAlerts.startTimer(event.getJDA());

        readyEvent = event;

        STATICS.lastRestart = new Date();


        handleStartArgs();


        if (STATICS.autoUpdate)
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    UpdateClient.checkIfUpdate(event.getJDA());
                }
            }, 0, 60000);



        commands.chat.Vote3.loadPolls(event.getJDA());
        commands.chat.Counter.loadAll(event.getJDA());
        commands.guildAdministration.Autochannel.load(event.getJDA());
        commands.guildAdministration.Mute.load();
        // commands.chat.Vote2.loadPolls(event.getJDA());

    }
}
