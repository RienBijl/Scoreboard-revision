package rien.bijl.Scoreboard.r;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import rien.bijl.Scoreboard.r.Board.BoardPlayer;
import rien.bijl.Scoreboard.r.Board.ConfigBoard;
import rien.bijl.Scoreboard.r.Plugin.Commands.Manager;
import rien.bijl.Scoreboard.r.Plugin.ConfigChecker;
import rien.bijl.Scoreboard.r.Plugin.ConfigControl;
import rien.bijl.Scoreboard.r.Plugin.Listeners.JoinListener;
import rien.bijl.Scoreboard.r.Plugin.Listeners.LeaveListener;
import rien.bijl.Scoreboard.r.Plugin.Session;
import rien.bijl.Scoreboard.r.Plugin.Utility.UpdateChecker;
import rien.bijl.Scoreboard.r.Plugin.WorldManager;

import java.util.Objects;

public class ScoreboardRevision {

    private final JavaPlugin plugin;
    private final String defaultBoard;

    /**
     *
     * @param plugin JavaPlugin hooked to
     * @param defaultBoard What board should we default to
     */
    public ScoreboardRevision(JavaPlugin plugin, String defaultBoard)
    {
        this.plugin = plugin;
        this.defaultBoard = defaultBoard;
    }

    public void enable()
    {
        Session.makeSession(this.plugin);

        this.setupBasics();
        this.registerEvents();
        this.checkForUpdates();
    }

    private void checkForUpdates()
    {
        if (!Objects.requireNonNull(ConfigControl.get().gc("settings").getConfigurationSection("settings")).getBoolean("check-updates")) {
            return;
        }

        String version = new UpdateChecker(this.plugin, 14754).getVersion();

        if (!plugin.getDescription().getVersion().replaceAll("\\s", "").equalsIgnoreCase(version))
        {
            Session.getSession().alertOutOfDate = true;
            Session.getSession().plugin.getLogger().warning("Scoreboard-revision is out of date! Check the Spigot page for new updates");
        }
    }

    private void setupBasics()
    {
        ConfigControl.get().createDataFiles();
        ConfigChecker.check("settings", 1);
        ConfigChecker.check("language", 1);

        Session.getSession().defaultBoard = new ConfigBoard(this.defaultBoard);
        for (Player player : Bukkit.getOnlinePlayers()) {
            BoardPlayer.getBoardPlayer(player).attachConfigBoard(Session.getSession().defaultBoard);
        }
        Session.getSession().defaultBoard.enable();
        Session.getSession().defaultBoard.runTaskTimerAsynchronously(this.plugin, 1L, 1L);

        Session.getSession().worldManager = new WorldManager();
        Session.getSession().worldManager.runTaskTimer(this.plugin, 1L, 20L);

        Objects.requireNonNull(this.plugin.getCommand("sb")).setExecutor(new Manager());
    }

    private void registerEvents()
    {
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this.plugin);
        Bukkit.getPluginManager().registerEvents(new LeaveListener(), this.plugin);
    }

}
