/*
 * Copyright (c) 2019. Dan Motpan
 * MIT Licence
 */

package GUI;

 import java.io.File;
        import java.io.FileReader;
        import java.io.FileWriter;
        import java.util.ArrayList;
        import java.util.Iterator;

 import org.json.JSONObject;
        import org.json.JSONTokener;

        import GUI.GUI_Main;
 import utils.STATICS;

public class Config {

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

    private static File APP_DIR = null;
    private static File DEFAULT_CONFIG = null;

    private static JSONObject defaults;

    private static File file;
    private static JSONObject json;

    private static boolean initialized;

    public static boolean isInitialized() {
        return initialized;
    }

    public static boolean init(File configFile, boolean fromGUI) { // don't crash after generation if fromGUI
        initialized = false;

        file = configFile;

        defaults = new JSONObject();
        defaults.put(BOT_TOKEN, "#uncomment this (remove \"#\") and put bot token here. You can create your app / bot and get your token here: " + STATICS.DISCORD_GET_TOKEN);
        defaults.put(ADMINS_ROLE, "Bot-Admin #comment out (add \"#\") or delete everything behind \"=\" to disable");
        defaults.put(CONTROL_CHANNEL, "bot");
        defaults.put(COMMAND_PREFIX, "!");
        defaults.put(VOICE_CHANNEL, "Music");
        defaults.put(DISPLAY_SONG_AS_GAME, "true");
        defaults.put(UPDATE_CHECK_INTERVAL_HOURS, "24 #set to 0 to disable");
        defaults.put(ALLOW_CUSTOM_VOLUME, "false");
        defaults.put(STARTING_VOLUME, "100");
        defaults.put(ENABLE_MEDIA_CONTROL_KEYS, "false");
        defaults.put(AUTO_RECONNECT, "true");
       // defaults.put(USE_NATIVE_AUDIO_SYSTEM, Utils.isUnknownOS() ? "false" : "true"); // enabled by default, but not for unknown OS

        JSONObject read;
        try {
            read = new JSONObject(new JSONTokener(new FileReader(file)));
        } catch (Exception e) {
            // Failed to load config. Maybe it doesn't exist (already).
            // Create an empty one
            read = new JSONObject();
        }

        json = read;

        ArrayList<String> toAdd = null;
        Iterator<String> keys = defaults.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            if(!read.has(key)) {
                if(toAdd == null) {
                    toAdd = new ArrayList<>();
                }
                toAdd.add(key);
            }
        }

        if(toAdd == null) {
            initialized = true;
        } else {
            if(generate(toAdd)) {
                String gotGeneratedOrUpdatedMSG = "Config file got generated or updated.";
                if(fromGUI) {
                    GUI_Main.showMsgBox(gotGeneratedOrUpdatedMSG);
                    initialized = true;
                } else {
                    Utils.errExit(gotGeneratedOrUpdatedMSG + " Please edit it now.");
                }
            } else {
                Utils.handleErrorNCG("Failed to generate config. (Do you have write access here?)");
            }
        }

        return initialized;
    }

    public static String get(String key) {
        Log.debug("Reading config key: {}", key);
        return toValue(json.getString(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public static void set(String key, String value) {
        setRaw(key, toValue(value));
    }

    public static void set(String key, int value) {
        set(key, String.valueOf(value));
    }

    private static void setRaw(String key, String value) {
        json.put(key, value);
        Log.debug("Config key set: {}", key);
    }

    private static boolean generate(ArrayList<String> toAdd) {
        for(String key : toAdd) {
            setRaw(key, defaults.getString(key));
        }

        return save();
    }

    public static boolean save() {
        try {
            json.write(new FileWriter(file), 2, 0).close();
            Log.debug("Config saved");
            return true;
        } catch (Exception e) {
            if(a.isDebug()) {
                e.printStackTrace();
                Log.debug("Failed to save config file");
            }
            return false;
        }
    }

    private static String toValue(String v) {
        try {
            return v.split(STATICS.CONFIG_COMMENT)[0].trim();
        } catch (Exception e) {
            Utils.printException(e);
            //Log.debug("Invalid config value: {}", v);
            Log.debug("Invalid config value"); // Do not print 'v' because it could contain sensitive data like the token
        }
        return "";
    }

    public static File getAppDir() {
        if(APP_DIR != null) {
            return APP_DIR;
        }

        switch(Utils.getOS()) {
            case STATICS.OS_LINUX:
                APP_DIR = new File(System.getProperty("user.home"), ("." + STATICS.BOT_NAME.toLowerCase()));
                break;
            case STATICS.OS_WINDOWS:
                APP_DIR = new File(System.getenv("APPDATA"), STATICS.BOT_NAME);
                break;
            default:
                APP_DIR = new File(System.getProperty("user.dir"), STATICS.BOT_NAME.toLowerCase());
                break;
        }

        if(!APP_DIR.exists()) {
            if(!APP_DIR.mkdir()) {
                Utils.errExit("Failed to create config directory:" + System.lineSeparator() + APP_DIR.getAbsolutePath());
            }
        }

        return APP_DIR;
    }

    public static File getDefaultConfig() {
        if(DEFAULT_CONFIG != null) {
            return DEFAULT_CONFIG;
        }

        DEFAULT_CONFIG = new File(getAppDir(), STATICS.DEFAULT_CONFIG_FILENAME);

        return DEFAULT_CONFIG;
    }

}