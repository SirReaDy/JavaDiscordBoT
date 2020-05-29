package commands.chat;

import com.fasterxml.jackson.core.JsonParser;
import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import utils.STATICS;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.ParseException;



public class JokeV2 implements Command {

    private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = rd.readLine();

            int i = jsonText.indexOf("{");
            jsonText = jsonText.substring(i);
            JSONObject json = new JSONObject(jsonText);

            return json;
        }
              finally {
            is.close ();
        }

    }


    private String get() {

        String out = "";

        try {
            out = readJsonFromUrl("http://api.yomomma.info").getString("joke");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return out;
    }


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public Game action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {

        event.getMessage().delete().queue();
        event.getTextChannel ().sendMessage ( get ());
        event.getTextChannel().sendMessage(new EmbedBuilder().setColor(new Color(0xE318B3)).setDescription("tst").setFooter("Jokes by yomomma.info",  null).build()).queue();

        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public String description() {
        return "Throw around with some yo mama jokes";
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
