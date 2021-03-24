package rien.bijl.Scoreboard.r.Plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import rien.bijl.Scoreboard.r.Board.ConfigBoard;

import java.util.ArrayList;

public class Session {

    public JavaPlugin plugin;

    private static Session session;

    public String[] dependencies = {"PlaceholderAPI"};
    public ArrayList<String> enabled_dependencies = new ArrayList<>();
    public boolean alertOutOfDate = false;
    public boolean alertBrokenConfig = false;
    public ConfigBoard defaultBoard;
    public WorldManager worldManager;

    /**
     * Make a new session
     * @param plugin The JavaPlugin
     */
    public static void makeSession(JavaPlugin plugin) {
        session = new Session(plugin);
    }

    /**
     * Get the active session
     * @return Session
     */
    public static Session getSession() {
        return session;
    }

    /**
     * Session constructor
     * @param plugin the JavaPlugin
     */
    private Session(JavaPlugin plugin) {
        this.plugin = plugin;

        for(String dependency : this.dependencies)
            if(Bukkit.getPluginManager().isPluginEnabled(dependency))
                this.enabled_dependencies.add(dependency);
    }

}
