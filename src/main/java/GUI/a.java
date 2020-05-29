/*
 * Copyright (c) 2019. Dan Motpan
 * MIT Licence
 */

package GUI;


        import java.awt.GraphicsEnvironment;
        import java.io.IOException;

        import javax.swing.UIManager;

        import GUI.Utils.ArgumentUtils;
      //  import com.github.bleuzen.blizcord.bot.Bot;
        import GUI.GUI_Main;
        import commands.etc.BotStats;
        import core.MySql;
        import core.SettingsCore;
        import core.startArgumentHandler;
        import net.dv8tion.jda.core.AccountType;
        import net.dv8tion.jda.core.JDABuilder;
        import utils.STATICS;





public class a {

    private static MySql mySql;

    private static boolean gui;
    private static boolean useNimbusTheme;
    private static boolean debug;

    /*
     * > Mainly for AUR users
     * - disable checkbox in GUI_Config
     * - not check for updates
     * */
    private static boolean disableUpdateChecker;

    public static boolean isGui() {
        return gui;
    }

    public static boolean isDebug() {
        return debug;
    }

    public static boolean isDisableUpdateChecker() {
        return disableUpdateChecker;
    }

    public static MySql getMySql() {
        return mySql;
    }
    static JDABuilder builder;
    public static void main(String[] args) throws IOException{



        gui = ArgumentUtils.containsArg(args, "--gui");
        useNimbusTheme = ArgumentUtils.containsArg(args, "--use-nimbus-theme");
        disableUpdateChecker = ArgumentUtils.containsArg(args, "--disable-update-checker");


        if(gui) {

            if(GraphicsEnvironment.isHeadless()) {
                // no gui supported
                gui = false; // disable gui mode
                Utils.errExit("GUI is not supported on your system.");
            }

            try {

                setLookAndFeel();

                Log.debug("Launching GUI ...");

                // Launch GUI
                GUI_Main frame = new GUI_Main();
                frame.setVisible(true);
            } catch (Exception e) {
                Utils.errExit("Failed to start GUI: " + e.getMessage());
            }

        } else {
            // Start in console
            Bot.launch(args);
        }

    }

    public static void setLookAndFeel() {
        try {
            // Check if we want to use Nimbus
            if(useNimbusTheme) {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                return;
            }

            // Use the systems theme
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Linux Fixes
            if(Utils.getOS().equals(STATICS.OS_LINUX)) {
                // Linux Font fix
                // https://wiki.archlinux.org/index.php/Java_Runtime_Environment_fonts
                System.setProperty("awt.useSystemAAFontSettings", "gasp");

                // KDE theme fix
                if(System.getenv("XDG_CURRENT_DESKTOP").toLowerCase().equals("kde")) {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                }
            }
        } catch (Exception e) {
            Log.debug("Failed to set look and feel.");
        }
    }





}
