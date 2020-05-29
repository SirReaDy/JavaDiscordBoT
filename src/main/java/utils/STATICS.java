package utils;

import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;

import java.util.*;

public class STATICS {
    public static  java.util.Date DATE = new java.util.Date();


    public static String TOKEN = "";

    //######### GENERAL BOT SETTINGS #########//


    public static String VERSION = "1.1-Beta";
    public static String BOT_NAME = "S!r.ReaDy BoT";

    public static String PREFIX = "!";

    public static OnlineStatus STATUS = OnlineStatus.ONLINE;

    public static String CUSTOM_MESSAGE = "ゼクロ";


 //   public static Game Game = Game.of (Game.GameType.WATCHING + " | !help | v." + VERSION )


    //######### WARFRAME ALERTS SETTINGS #########//

    public static int refreshTime = 10;

    public static String warframeAlertsChannel = "warframealerts";

    public static boolean enableWarframeAlerts = true;



    //######### PERMISSION SETTINGS #########//

    public static String[] PERMS = {"Bot Commander", "Moderator", "Admin", "Owner"};
    public static String[] FULLPERMS = {"Admin", "Owner"};

    public static String guildJoinRole = "";


    //########## GOOGLE DOCS ID'S ##########//

    public static String DOCID_warframeAlertsFilter = "13O2lZ_UemLDkCV8425XHOPSZ3aVoeYmV5cF_vLQAyEY";

    public static String DOCID_jokes = "1iAC6xYwkTdJgC12eVdC8EmPDFPLWfcSkScy5w0zk32I";

    //########### OTHER SETTINGS ###########//

    public static String voiceLogChannel = "voicelog";

    public static String musicChannel = "mucke";

    public static boolean commandConsoleOutout = true;

    public static String KICK_VOICE_CHANNEL = "";


    public static boolean autoUpdate = true;

    public static boolean musicCommandsOnlyInMusicChannel = true;

    public static String input;

    public static int music_volume = 0;

    public static String discordJoinMessage = ":heart: Hey, [USER]! Welcome on the [GUILD]! :heart:";

    public static String getSqlHost(){
        return SQL_HOST;
    }

    public static void setSqlHost(String sqlHost){
        SQL_HOST = sqlHost;
    }

    public static String getSqlPort(){
        return SQL_PORT;
    }

    public static void setSqlPort(String sqlPort){
        SQL_PORT = sqlPort;
    }

    public static String getSqlUser(){
        return SQL_USER;
    }

    public static void setSqlUser(String sqlUser){
        SQL_USER = sqlUser;
    }

    public static String getSqlPass(){
        return SQL_PASS;
    }

    public static void setSqlPass(String sqlPass){
        SQL_PASS = sqlPass;
    }

    public static String getSqlDb(){
        return SQL_DB;
    }


    public static void setSqlDb(String sqlDb){
        SQL_DB = sqlDb;
    }

    public class CMDTYPE {
        public static final String administration = "Administration";
        public static final String chatutils = "Chat Utilities";
        public static final String essentials = "Essentials";
        public static final String etc = "Etc";
        public static final String music = "Music";
        public static final String guildadmin = "Guild Administration";
        public static final String settings = "SettingsCore";
    }

    public static Date lastRestart;

    public static int reconnectCount = 0;

    public static ArrayList<ArrayList<String>> cmdLog = new ArrayList<>();

    public static long BOT_OWNER_ID = 0;

    public static int MUSIC_BUFFER = 1000;

    public static int SERVER_LIMIT = 250;

    private static String SQL_HOST = "";
    private static String SQL_PORT = "";
    private static String SQL_USER = "";
    private static String SQL_PASS = "";
    private static String SQL_DB = "";

}
