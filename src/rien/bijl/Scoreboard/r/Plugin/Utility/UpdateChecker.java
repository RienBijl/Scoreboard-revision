package rien.bijl.Scoreboard.r.Plugin.Utility;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class UpdateChecker {

    private final JavaPlugin plugin;
    private final int resourceId;

    public UpdateChecker(JavaPlugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public String getValueFromWebsite(String website) throws IOException {
        URL url = new URL(website);
        Scanner sc = new Scanner(url.openStream());
        StringBuffer sb = new StringBuffer();
        while(sc.hasNext()) {
            sb.append(sc.next());
        }

        return sb.toString();
    }

    public String getVersion() {
        try {
            return getValueFromWebsite("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId);
        } catch (IOException exception) {
            this.plugin.getLogger().info("Cannot look for updates: " + exception.getMessage());
        }
        return null;
    }
}