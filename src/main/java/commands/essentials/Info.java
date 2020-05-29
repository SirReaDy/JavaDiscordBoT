package commands.essentials;

import commands.Command;
import core.UpdateClient;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.STATICS;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

public class Info implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public Game action(String[] args, MessageReceivedEvent event) throws ParseException, IOException{
        return null;
    }


    @Override
    public void executed(boolean success, MessageReceivedEvent event) {


        event.getTextChannel().sendMessage(
                new EmbedBuilder()
                        .setColor(Color.MAGENTA)
                        .setThumbnail("https://www.shareicon.net/data/2017/06/21/887435_logo_512x512.png")
                        .setDescription(":robot:   __**S!r.ReaDy** JDA Discord Bot__")
                        .addField("Current Version", STATICS.VERSION, true)
                        .addField("Latest Version", STATICS.VERSION, true)
                        .addField("Copyright",
                                "Coded by S!r.ReaDy.\n" +
                                        "© 2019  JDA.", false)
                        .addField("Information and Links",
                                "GitHub Repository: \n*#*\n\n" +
                                        "Readme/Changelogs: \n*#*\n\n" +
                                        "Webpage: \n*#*\n\n" +
                                        "Github Profile: \n*#*", false)
                        .addField("Libraries and Dependencies",
                                " -  JDA  *(https://github.com/DV8FromTheWorld/JDA)*\n" +
                                        " -  Toml4J  *(https://github.com/mwanji/toml4j)*\n" +
                                        " -  lavaplayer  *(https://github.com/sedmelluq/lavaplayer)*\n" +
                                        " -  Steam-Condenser  *(https://github.com/koraktor/steam-condenser-java)*", false)
                        .addField("Bug Reporting / Idea Suggestion",
                                "If you got some bugs, please contact us here:\n" +
                                        " - **Please use this document to report a Bug or suggest an idea: !bug**\n" +
                                        " -  E-Mail:  power199832@gmail.com\n" +
                                        " -  Discord:  S!r.ReaDy<3#3310", false)


                        //.setThumbnail("https://dl.dropboxusercontent.com/s/clxgmgaon7o6pkh/official_avatar.jpg")
                        .build()



        ).queue();


        final Runtime runtime = Runtime.getRuntime();
        long memoryLimit = runtime.maxMemory();
        long memoryInUse = runtime.totalMemory() - runtime.freeMemory();

        StringBuilder sb = new StringBuilder();

        sb.append("**System information: \n\n");
        event.getJDA().getGuilds().stream()
                .forEach(g -> sb.append(
                        ("Memory")+
                                ("\n")+
                                (getProgressbar(memoryInUse, memoryLimit))+
                                (" [ ") +
                                (numberInMb(memoryInUse))+
                                (" / ")+
                                (numberInMb(memoryLimit)) +
                                (" ]")+ ("\n")
                ));
        if (sb.toString().length() > 2000)
            event.getTextChannel().sendMessage("#\n\n" + sb.toString()).queue();
      else
            event.getTextChannel().sendMessage(new EmbedBuilder ().setColor (Color.MAGENTA).setTitle("System information", null).setDescription(sb.toString()).build()).queue();

      //  return null;



    }
    private String numberInMb(long number) {
        return "" + (number / (1048576L)) + " mb";
    }

    private String getProgressbar(long current, long max) {
        StringBuilder bar = new StringBuilder();
        final String BLOCK_INACTIVE = "▬";
        final String BLOCK_ACTIVE = ":large_blue_circle:";
        final int BLOCK_PARTS = 12;
        int activeBLock = (int) (((float) current / (float) max) * (float) BLOCK_PARTS);
        for (int i = 0; i < BLOCK_PARTS; i++) {
            if (i == activeBLock) {
                bar.append(BLOCK_ACTIVE);
            } else {
                bar.append(BLOCK_INACTIVE);
            }
        }
        return bar.toString();
    }


    @Override
    public String help() {
        return "USAGE: -info";
    }

    @Override
    public String description() {
        return "Get info about that bot";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.essentials;
    }

    @Override
    public int permission() {
        return 0;
    }
}
