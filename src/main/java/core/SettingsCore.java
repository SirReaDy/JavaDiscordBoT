package core;



import org.json.JSONObject;
import org.json.JSONTokener;
import utils.STATICS;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;


public class SettingsCore {


    public static final String CONTROL_CHANNEL = "CONTROL_CHANNEL";
    public static final String VOICE_CHANNEL = "VOICE_CHANNEL";
    public static final String BOT_TOKEN = "BOT_TOKEN";
    public static final String COMMAND_PREFIX = "COMMAND_PREFIX";
    public static final String DISPLAY_SONG_AS_GAME = "DISPLAY_SONG_AS_GAME";
    public static final String UPDATE_CHECK_INTERVAL_HOURS = "UPDATE_CHECK_INTERVAL_HOURS";
    public static final String ADMINS_ROLE = "ADMINS_ROLE";
    public static final String ALLOW_CUSTOM_VOLUME = "ALLOW_CUSTOM_VOLUME";
    public static final String STARTING_VOLUME = "STARTING_VOLUME";
    public static final String ENABLE_MEDIA_CONTROL_KEYS = "ENABLE_MEDIA_CONTROL_KEYS";
    public static final String AUTO_RECONNECT = "AUTO_RECONNECT";
    public static final String USE_NATIVE_AUDIO_SYSTEM = "USE_NATIVE_AUDIO_SYSTEM";
    private static JSONObject defaults;
    private static File file;
    private static JSONObject json;
    private static boolean initialized;
    public static boolean isInitialized() {
        return initialized;
    }



    public static void initialize() {

        JSONObject main = new JSONObject();

        JSONObject mysql = new JSONObject()
                .put("host", "")
                .put("port", "3306")
                .put("username", "")
                .put("password", "")
                .put("database", "discord");

        main    .put("token", "")
                .put("prefix", "!")
                .put("ownerid", "")
                .put("updateinfo", "false")
                .put("musicvolume", 50)
                .put("musicbuffer", 5000)
                .put("mysql", mysql);

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter("SETTINGS.json"));
            br.write(main.toString());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSettings() throws IOException {

        if (!new File("SETTINGS.json").exists()) {
            initialize();
            System.out.println("Please open \"SETTINGS.json\" file and enter your discord token and owner ID there!");
            System.exit(-1);
        } else {

            BufferedReader br = new BufferedReader(new FileReader("SETTINGS.json"));
            String out = br.lines().collect(Collectors.joining("\n"));

            JSONObject obj = new JSONObject(out);

            STATICS.TOKEN           = obj.getString("token");
            STATICS.PREFIX          = obj.getString("prefix");
            STATICS.BOT_OWNER_ID    = Long.parseLong(obj.getString("ownerid"));
            STATICS.autoUpdate      = Boolean.parseBoolean(obj.getString("updateinfo"));
            STATICS.music_volume    = obj.getInt("musicvolume");
            STATICS.MUSIC_BUFFER    = obj.getInt("musicbuffer");

            JSONObject mysql = obj.getJSONObject("mysql");

            STATICS.setSqlHost (mysql.getString("host"));
            STATICS.setSqlPort (mysql.getString("port"));
            STATICS.setSqlUser (mysql.getString("username"));
            STATICS.setSqlPass (mysql.getString("password"));
            STATICS.setSqlDb (mysql.getString("database"));


            }

    }

}
