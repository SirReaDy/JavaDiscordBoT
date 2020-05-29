/*
 * Copyright (c) 2019. Dan Motpan
 * MIT Licence
 */

package commands.chat;

import commands.Command;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.text.ParseException;
import java.util.Random;

public class Roll implements Command


{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event){
        return false;
    }

    @Override
    public Game action(String[] args, MessageReceivedEvent event) throws ParseException, IOException{

        Random rand = new Random ();
        int roll = rand.nextInt (6) + 1;
        event.getTextChannel ().sendMessage ("Your roll is s" +roll).queue ( sentMessage ->
                {
                    if ( roll < 3)
                    {
                        event.getTextChannel ().sendMessage ("Roll of " + event.getAuthor ().getAsMention () + " not so good 😒 try again!").queue ();
                    }
                }

        );
        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event){

    }

    @Override
    public String help(){
        return "Usage !roll";
    }

    @Override
    public String description(){
        return null;
    }

    @Override
    public String commandType(){
        return null;
    }

    @Override
    public int permission(){
        return 0;
    }
}
