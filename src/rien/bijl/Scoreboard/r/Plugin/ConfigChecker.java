package rien.bijl.Scoreboard.r.Plugin;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.*;
import java.util.Random;

public class ConfigChecker {

    public static void check(String config, int version) {
        FileConfiguration fc = ConfigControl.get().gc(config);

        if (fc.getInt("config_version") < version) {
            Session.getSession().alertBrokenConfig = true;
            replaceConfig(config);
        }
    }

    public static void replaceConfig(String config) {
        File oldFile = new File(Session.getSession().plugin.getDataFolder(), config + ".yml");
        File newFile = new File(Session.getSession().plugin.getDataFolder(), config + "." + new Random().nextInt(999999999) + ".backup.yml");

        try {
            copyFileUsingStream(oldFile, newFile);
        } catch (IOException io) {
            Session.getSession().plugin.getLogger().warning("Could not backup configuration");
        }

        oldFile.delete();

        ConfigControl.get().reloadConfigs();
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

}
