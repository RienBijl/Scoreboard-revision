package rien.bijl.Scoreboard.r.Board;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import rien.bijl.Scoreboard.r.Board.Animations.Row;
import rien.bijl.Scoreboard.r.Board.Implementations.WrapperBoard;
import rien.bijl.Scoreboard.r.Plugin.ConfigControl;
import rien.bijl.Scoreboard.r.Plugin.Utility.ScoreboardStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ConfigBoard extends BukkitRunnable {

    public String board;
    private Row title;
    private ArrayList<Row> rows = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private HashMap<Player, WrapperBoard> playerToBoard = new HashMap<>();
    private boolean enabled;

    public ConfigBoard(String board)
    {
        this.board = board;
        this.initTitle();
        this.initRows();
    }

    private void initTitle()
    {
        List<String> lines = Objects.requireNonNull(ConfigControl.get().gc("settings").getConfigurationSection(this.board + ".title")).getStringList("lines");
        int interval = ConfigControl.get().gc("settings").getInt(this.board + ".title.interval");
        this.title = new Row(ScoreboardStrings.makeColoredStringList(lines), interval);
    }

    private void initRows()
    {
        for (int i = 1; i < 200; i++) {
            ConfigurationSection section = ConfigControl.get().gc("settings").getConfigurationSection(this.board + ".rows." + i);
            if (section != null) {
                Row row = new Row(ScoreboardStrings.makeColoredStringList(section.getStringList("lines")), section.getInt("interval"));
                rows.add(row);
            }
        }
    }

    public void hookPlayer(Player player) {
        players.add(player);

        try {
            WrapperBoard wrapperBoard = new WrapperBoard("SCOREBOARD_DRIVER_V1");
            wrapperBoard.setLineCount(rows.size());
            wrapperBoard.setPlayer(player);
            playerToBoard.put(player, wrapperBoard);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void unhookPlayer(Player player) {
        playerToBoard.remove(player);
        players.remove(player);
        player.setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());
    }

    @Override
    public void run() {
        if (!this.enabled) return;

        this.title.update();

        for(Row row: rows) {
            row.update();
        }

        for (Player player: playerToBoard.keySet()) {
            WrapperBoard wrapperBoard = playerToBoard.get(player);
            wrapperBoard.setTitle(ScoreboardStrings.placeholders(player, this.title.getLine()));

            int count = 0;
            for (Row row: rows) {
                wrapperBoard.setLine(count, ScoreboardStrings.placeholders(player, row.getLine()));
                count++;
            }
        }
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }
}
